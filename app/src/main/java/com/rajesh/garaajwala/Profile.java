package com.rajesh.garaajwala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.rajesh.garaajwala.data.MyHandler;
import com.rajesh.garaajwala.data.SessionManager;
import com.rajesh.garaajwala.models.Garaaj;
import com.rajesh.garaajwala.models.Users;

import java.util.HashMap;
import java.util.List;

public class Profile extends AppCompatActivity {

    //Variables
    ImageView b_arrow;
    HashMap<String, String> details;
    EditText name, phone, email_profile, add, add1;
    TextView profile_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        //Hooks
        b_arrow = findViewById(R.id.back_arrow);
        name = findViewById(R.id.full_name);
        email_profile = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        add = findViewById(R.id.add);
        add1 = findViewById(R.id.add1);
        profile_name = findViewById(R.id.profile_name);

        //Session
        SessionManager sessionManager = new SessionManager(this);
        HashMap<String,String> userDetails = sessionManager.getUsersDetailFromSession();
        String email = userDetails.get(SessionManager.KEY_EMAIL);

        //DB
        MyHandler db = new MyHandler(this);

        //Getting user details
        details = new HashMap<>();
        List<Users> users = db.getUser(email);
        for(Users user: users){
            Log.d("goon","ID: " + user.getId() + "\n" +
                    "Name: " + user.getName() + "\n" +
                    "Email: " + user.getEmail() + "\n" +
                    "Phone Number : " + user.getPhone() + "\n" +
                    "Shop : " + user.getHouse_no() + "\n" +
                    "Area : " + user.getArea() + "\n" +
                    "Street : " + user.getStreet() + "\n" +
                    "Landmark : " + user.getLandmark() + "\n" +
                    "State : " + user.getState() + "\n" +
                    "City : " + user.getCity() + "\n" +
                    "Password : " + user.getPassword() + "\n" );

            details.put("Name", user.getName());
            details.put("Email", user.getEmail());
            details.put("Phone", user.getPhone());
            details.put("Home", user.getHouse_no());
            details.put("Area", user.getArea());
            details.put("Street", user.getStreet());
            details.put("Landmark", user.getLandmark());
            details.put("State", user.getState());
            details.put("City", user.getCity());
        }

        name.setText(details.get("Name"));
        profile_name.setText(details.get("Name"));
        email_profile.setText(details.get("Email"));
        phone.setText(details.get("Phone"));
        add.setText(details.get("Home")+ "," + details.get("Area")+ "," +details.get("Street")+ "," +details.get("Landmark"));
        add1.setText(details.get("City")+ "," + details.get("State"));


        b_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Dashboard.class));
            }
        });
    }
}