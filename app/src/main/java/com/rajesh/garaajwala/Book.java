package com.rajesh.garaajwala;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rajesh.garaajwala.data.MyHandler;
import com.rajesh.garaajwala.data.SessionManager;
import com.rajesh.garaajwala.models.Orders;

import java.util.HashMap;
import java.util.List;

public class Book extends AppCompatActivity {

    ImageView ham;
    TextView app_name;
    RelativeLayout search_bar;
    ImageView serach_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }


        //Hooks
        ham = findViewById(R.id.ham);
        app_name = findViewById(R.id.app_name);
        search_bar = findViewById(R.id.search_bar);
        serach_icon = findViewById(R.id.serach_icon);
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
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Book.this, pairs);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });

        Intent intent = getIntent();
        String user_email = intent.getStringExtra("garage_email");
        Log.d("goon", " email : " + user_email);

        //Session
        SessionManager sessionManager = new SessionManager(this);
        HashMap<String,String> userDetails = sessionManager.getUsersDetailFromSession();
        String garage_email = userDetails.get(SessionManager.KEY_EMAIL);
        Log.d("goon" , "Garage EMial" + garage_email);

        //sign in orders
        MyHandler db = new MyHandler(this);
        Orders raj = new Orders();
        raj.setUser(user_email);
        raj.setGarage(garage_email);
        db.signOrders(raj);

        //Display all Bookings in log
//        List<Orders> orders = db.getOrders();
//        if (!orders.isEmpty()){
//            for(Orders user : orders){
//                Log.d("goon" , "Orders SHops \n" +
//                        "User : " + user.getUser() + "\n"
//                        + "Shop : " + user.getGarage());
//            }
//        }

        // get Orders from shop
//        List<Orders> orders_shop = db.getgarageOrders("testg@gmail.com");
//        if(orders_shop != null){
//            for (Orders user : orders_shop) {
//                Log.d("goon" , "Orders SHops \n" +
//                        "User : " + user.getUser() + "\n"
//                        + "Shop : " + user.getGarage());
//            }
//        }
//        else {
//            Log.d("goon", "Not working");
//        }
    }
}