package com.rajesh.garaajwala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.rajesh.garaajwala.data.MyHandler;
import com.rajesh.garaajwala.data.SessionManager;
import com.rajesh.garaajwala.models.Garaaj;
import com.rajesh.garaajwala.models.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Login extends AppCompatActivity {

    //Variables
    ImageView arrow;
    Button b_create, b_login, b_login_garage;
    TextInputLayout email, password;
    HashMap<String,String> details ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        //Hooks
        arrow = findViewById(R.id.back_arrow);
        b_create = findViewById(R.id.create_btn);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        b_login = findViewById(R.id.login_btn);
        b_login_garage = findViewById(R.id.login_garage_btn);

        //Session
        SessionManager sessionManager = new SessionManager(this);
        details = new HashMap<>();


        b_login_garage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginGarage.class));
            }
        });

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), InterfacePage.class));
            }
        });
        b_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), InterfacePage.class));
            }
        });
        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyHandler db = new MyHandler(Login.this);
                String login_email = email.getEditText().getText().toString();
                String login_password = password.getEditText().getText().toString();
                boolean check = db.loginUser(login_email, login_password);
                if (check){
                    List<Users> users = db.getUser(login_email);
                    if(users != null) {
                        for (Users user : users) {
                            details.put("fullnameS", user.getName());
                            details.put("emailS", user.getEmail());
                            details.put("phoneS", user.getPhone());
                            details.put("passwordS", user.getPassword());
                            details.put("houseS", user.getHouse_no());
                            details.put("areaS", user.getArea());
                            details.put("streetS", user.getStreet());
                            details.put("landmarkS", user.getLandmark());
                            details.put("stateS", user.getState());
                            details.put("cityS", user.getCity());
                        }
                    }
                    else {
                    }
                    //Creating Session
                    sessionManager.createLoginSession(details.get("fullnameS"),details.get("emailS"),details.get("phoneS"),
                            details.get("passwordS"),details.get("houseS"),details.get("areaS"),
                            details.get("streetS"),details.get("landmarkS"),details.get("stateS"),details.get("cityS"));
                    Log.d("goon","" + details.get("fullnameS"));
                    startActivity(new Intent(getApplicationContext(),Dashboard.class));
                    finish();
                    Toast.makeText(getApplicationContext(), "You are successfully Logged In", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getApplicationContext(), "Your Email or Password is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}