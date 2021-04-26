package com.rajesh.garaajwala;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.rajesh.garaajwala.data.MyHandler;
import com.rajesh.garaajwala.data.SessionManager;
import com.rajesh.garaajwala.models.FeaturedHelper;
import com.rajesh.garaajwala.models.Garaaj;
import com.rajesh.garaajwala.models.GarageHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GarageDashboard extends AppCompatActivity {

    ImageView ham;
    RecyclerView garage_recycle;
    HashMap<String,String> nearby;
    ArrayList<String> nearbylist;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage_dashboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        //Hooks
        ham = findViewById(R.id.ham);

        ham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),InterfacePage.class));
                finish();
            }
        });

    }

}