package com.rajesh.garaajwala;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class InterfacePage extends AppCompatActivity {

    Button b_user, b_garage, b_already;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface_page);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        b_user = findViewById(R.id.user_btn);
        b_garage = findViewById(R.id.garage_btn);
        b_already = findViewById(R.id.already_btn);

        b_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Signup.class));
                finish();
            }
        });
        b_garage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Signup_garage.class));
                finish();
            }
        });

    }
    public void callLoginScreen(View view){
        Intent intent = new Intent(getApplicationContext(),Login.class);
        Pair[] pairs = new Pair[2];
        pairs[0] = new Pair<View,String>(findViewById(R.id.already_btn),"transition_Login");
        pairs[1] = new Pair<View,String>(findViewById(R.id.user_btn),"transition_costumer");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(InterfacePage.this,pairs);
            startActivity(intent);
            finish();
        }
        else {
            startActivity(intent);
            finish();
        }
    }
}