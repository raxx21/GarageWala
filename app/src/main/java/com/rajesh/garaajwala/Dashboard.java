package com.rajesh.garaajwala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.rajesh.garaajwala.data.MyHandler;
import com.rajesh.garaajwala.data.SessionManager;
import com.rajesh.garaajwala.models.FeaturedHelper;
import com.rajesh.garaajwala.models.Garaaj;
import com.rajesh.garaajwala.models.MostPopularHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    static final float END_SCALE = 0.7f;
    LinearLayout contentView;
    RecyclerView featured_recycle;
    RecyclerView mostPopular_recycle;
    RecyclerView.Adapter adapter;
    TextView app_name;
    RelativeLayout search_bar, car, bike, cos, service;
    ImageView serach_icon;
    TextView title;
    //Menu Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView ham;
    //HashMap
    HashMap<String,String> nearby;
    ArrayList<String> nearbylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        //Hooks
        featured_recycle = findViewById(R.id.featured_recycle);
        mostPopular_recycle = findViewById(R.id.mostPopular_recycle);
        contentView = findViewById(R.id.content);
        app_name = findViewById(R.id.app_name);
        search_bar = findViewById(R.id.search_bar);
        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        ham = findViewById(R.id.ham);
        serach_icon = findViewById(R.id.serach_icon);
        car = findViewById(R.id.card_car);
        bike = findViewById(R.id.card_bike);
        cos = findViewById(R.id.card_cos);
        service = findViewById(R.id.card_service);

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),car.class));
            }
        });

        navigationDrawer();
        featuredRecycle();
        mostPopularRecycle();

        //Search Bar
        search_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchBar.class);
                //Add Transitions
                Pair[] pairs = new Pair[4];

                pairs[0] = new Pair<View, String>(ham, "search_back");
                pairs[1] = new Pair<View, String>(app_name, "search_title");
                pairs[2] = new Pair<View, String>(search_bar, "search_title");
                pairs[3] = new Pair<View, String>(serach_icon, "serach_icon");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Dashboard.this, pairs);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });


        //Session
        SessionManager sessionManager = new SessionManager(this);
        HashMap<String,String> userDetails = sessionManager.getUsersDetailFromSession();
//        String name = userDetails.get(SessionManager.KEY_NAME);
//        app_name.setText(name);
    }

    //Navigation Functions
    private void navigationDrawer() {

        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        ham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();
    }
    private void animateNavigationDrawer() {
        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                Toast.makeText(getApplicationContext(),"its Home",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_search:
                startActivity(new Intent(getApplicationContext(),SearchBar.class));
                Toast.makeText(getApplicationContext(),"its search",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                Toast.makeText(getApplicationContext(),"You are Logged out",Toast.LENGTH_SHORT).show();
                //Session
                SessionManager sessionManager = new SessionManager(this);
                sessionManager.logoutUserSession();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
                break;
            case R.id.nav_profile:
                startActivity(new Intent(getApplicationContext(),Profile.class));
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //Recycle View Functions
    private void mostPopularRecycle() {

        mostPopular_recycle.setHasFixedSize(true);
        mostPopular_recycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<MostPopularHelper> mostPopularLocations = new ArrayList<>();

        mostPopularLocations.add(new MostPopularHelper(R.drawable.car1, "Shanjaye Motors", "565,area5,street,landmark,state,city"));
        mostPopularLocations.add(new MostPopularHelper(R.drawable.car2, "Rowdy Motors", "610,area3,street,landmark,state,city"));
        mostPopularLocations.add(new MostPopularHelper(R.drawable.car3, "Vikrant Motors", "710,area4,street,landmark,state,city"));

        adapter = new MostPopularAdpater(mostPopularLocations);
        mostPopular_recycle.setAdapter(adapter);

    }
    private void featuredRecycle() {

        featured_recycle.setHasFixedSize(true);
        featured_recycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        MyHandler db = new MyHandler(this);
        ArrayList<FeaturedHelper> featuredLocations = new ArrayList<>();

        nearby = new HashMap<>();
        nearbylist = new ArrayList<>();

        //Sessions
        SessionManager sessionManager = new SessionManager(this);
        HashMap<String,String> userDetails = sessionManager.getUsersDetailFromSession();
        String area = userDetails.get(SessionManager.KEY_AREA);
        Log.d("goon", "Area " +area);

        // Display all nearby garaaj in log
        List<Garaaj> neargaraaj = db.nearGaraaj(area);
        Log.d("goon","No Garages here! " + neargaraaj);
        if(!neargaraaj.isEmpty()) {
            for (Garaaj user : neargaraaj) {
                featuredLocations.add(new FeaturedHelper(R.drawable.car2, user.getOwner_name(),  user.getShop_no() +"," + user.getArea() +"," +user.getStreet() +"," +user.getLandmark() +"," +user.getState() +"," +user.getCity(), "Book ", user.getEmail()));
            }
            Log.d("goon","No Garages here!");
        }
        else {
            featuredLocations.add(new FeaturedHelper(R.drawable.car2, "No Garages Available",  "NON","NON","NON"));

        }

//        featuredLocations.add(new FeaturedHelper(R.drawable.car2, "Garage 2", "Lorem ipsum, or lipsum as is dummy text used in laying out p"));
//        featuredLocations.add(new FeaturedHelper(R.drawable.car3, "Garage 3", "Lorem ipsum, or lipsum as is dummy text used in laying out p"));

        adapter = new FeaturedAdpater(featuredLocations);
        featured_recycle.setAdapter(adapter);

    }


}