package com.rajesh.garaajwala;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rajesh.garaajwala.data.MyHandler;
import com.rajesh.garaajwala.models.FeaturedHelper;
import com.rajesh.garaajwala.models.Garaaj;
import com.rajesh.garaajwala.models.MostPopularHelper;

import java.util.ArrayList;
import java.util.List;

public class SearchBar extends AppCompatActivity {

    EditText serach_area;
    TextView app_name;
    RelativeLayout search_bar;
    ImageView serach_icon;
    ImageView ham;
    RecyclerView featured_recycle;
    RecyclerView.Adapter adapter;
    Button search_btn;
    RecyclerView mostPopular_recycle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        //Hooks
        app_name = findViewById(R.id.app_name);
        search_bar = findViewById(R.id.search_bar);
        serach_area = findViewById(R.id.serach_area);
        search_btn = findViewById(R.id.search_btn);
        //Menu Hooks
        ham = findViewById(R.id.ham);
        serach_icon = findViewById(R.id.serach_icon);

        //Hooks
        featured_recycle = findViewById(R.id.serach_recycle);


        ham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                //Add Transitions
                Pair[] pairs = new Pair[4];

                pairs[0] = new Pair<View, String>(ham, "search_back");
                pairs[1] = new Pair<View, String>(app_name, "search_title");
                pairs[2] = new Pair<View, String>(search_bar, "search_title");
                pairs[3] = new Pair<View, String>(serach_icon, "serach_icon");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SearchBar.this, pairs);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });

        serach_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyHandler db = new MyHandler(SearchBar.this);
                String area = serach_area.getText().toString();
                List<Garaaj> neargaraaj = db.nearGaraaj(area);
                if(neargaraaj != null) {
                    for (Garaaj user : neargaraaj) {
                        Log.d("goon", "Nearby Garaaj's" + neargaraaj + "\n" +
                                "ID: " + user.getId() + "\n" +
                                "Name: " + user.getOwner_name() + "\n" +
                                "Email: " + user.getEmail() + "\n" +
                                "Phone Number : " + user.getPhone() + "\n" +
                                "Shop : " + user.getShop_no() + "\n" +
                                "Area : " + user.getArea() + "\n" +
                                "Street : " + user.getStreet() + "\n" +
                                "Landmark : " + user.getLandmark() + "\n" +
                                "State : " + user.getState() + "\n" +
                                "City : " + user.getCity() + "\n" +
                                "Password : " + user.getPassword() + "\n");
                    }
                }
            }
        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearbyRecycle();

            }
        });

    }

    private void nearbyRecycle() {

        featured_recycle.setHasFixedSize(true);
        featured_recycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        Intent intent = getIntent();
        String city = serach_area.getText().toString();
        ArrayList<FeaturedHelper> featuredLocations = new ArrayList<>();

        MyHandler db = new MyHandler(this);
        Intent intent1 = getIntent();


        // Display all garaaj in log
        List<Garaaj> garaaj = db.nearGaraaj(city);
        if(!garaaj.isEmpty()) {
            for(Garaaj user: garaaj){
                featuredLocations.add(new FeaturedHelper(R.drawable.car2,user.getOwner_name(), user.getShop_no() +"," + user.getArea() +"," +user.getStreet() +"," +user.getLandmark() +"," +user.getState() +"," +user.getCity(),  user.getPhone(), user.getEmail()));
            }
        }
        else {
            featuredLocations.add(new FeaturedHelper(R.drawable.car2, "No Shops Available",  "NON","NON","NON"));
        }

        adapter = new FeaturedAdpater(featuredLocations);
        featured_recycle.setAdapter(adapter);
    }



}