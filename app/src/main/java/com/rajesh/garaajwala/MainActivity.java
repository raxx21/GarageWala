package com.rajesh.garaajwala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.rajesh.garaajwala.data.MyHandler;
import com.rajesh.garaajwala.models.Garaaj;
import com.rajesh.garaajwala.models.Service2;
import com.rajesh.garaajwala.models.Service4;
import com.rajesh.garaajwala.models.Users;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;

    //Variables
    Animation fadein;
    ImageView logo;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        MyHandler db =new MyHandler(MainActivity.this);

        //Animation
        fadein = AnimationUtils.loadAnimation(this,R.anim.fadein);
        logo = findViewById(R.id.logo);
        title = findViewById(R.id.title);
        logo.setAnimation(fadein);
        title.setAnimation(fadein);

        //Splash screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Intro1.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

        // add user
        Users raj = new Users();
//        raj.setName("Rajesh Chityal");
//        raj.setEmail("rajesh@gmail.com");
//        raj.setPhone("9011842557");
//        raj.setPassword("rajesh2017");
//        db.signUser(raj);

        // add garaaj
//        Garaaj user1 = new Garaaj();
//        user1.setOwner_name("Garaaj2");
//        user1.setEmail("garaaj2@gmail.com");
//        user1.setPhone("1234567890");
//        user1.setShop_no("002");
//        user1.setArea("area2");
//        user1.setStreet("area2 road");
//        user1.setLandmark("landmark2");
//        user1.setState("state2");
//        user1.setCity("city2");
//        user1.setPassword("rajesh2017");
//        db.signGaraaj(user1);

        // add service2
//        Service2 service2 = new Service2();
//        service2.setTyres(1);
//        service2.setLights(0);
//        service2.setHorn(1);
//        service2.setSuspension(1);
//        service2.setBrakes(0);
//        service2.setFuel_system(1);
//        service2.setExhaust(0);
//        service2.setEngine(0);
//        service2.setBattery(1);
//        service2.setFull_body(1);
//        service2.setId(1);
//        db.signService2(service2);

        // add service4
//        Service4 service4 = new Service4();
//        service4.setTyres(1);
//        service4.setLights(0);
//        service4.setHorn(1);
//        service4.setSuspension(1);
//        service4.setBrakes(0);
////        service4.setSeat_belts(0);
//        service4.setFuel_system(1);
//        service4.setExhaust(0);
//        service4.setEngine(0);
//        service4.setBattery(1);
//        service4.setFull_body(1);
////        service4.setAir_condition(1);
//        service4.setId(1);
//        db.signService4(service4);

        // Display all users in log
        List<Users> users =db.getAllUser();
        for(Users user: users){
            Log.d("goon","ID: " + user.getId() + "\n" +
                    "Name: " + user.getName() + "\n" +
                    "Email: " + user.getEmail() + "\n" +
                    "Phone Number : " + user.getPhone() + "\n" +
                    "Password : " + user.getPassword() + "\n" );
        }

        // Display all garaaj in log
        List<Garaaj> garaaj =db.getAllGaraaj();
        for(Garaaj user: garaaj){
            Log.d("goon","ID: " + user.getId() + "\n" +
                    "Name: " + user.getOwner_name() + "\n" +
                    "Email: " + user.getEmail() + "\n" +
                    "Phone Number : " + user.getPhone() + "\n" +
                    "Shop : " + user.getShop_no() + "\n" +
                    "Area : " + user.getArea() + "\n" +
                    "Street : " + user.getStreet() + "\n" +
                    "Landmark : " + user.getLandmark() + "\n" +
                    "State : " + user.getState() + "\n" +
                    "City : " + user.getCity() + "\n" +
                    "Password : " + user.getPassword() + "\n" );
        }

        // Display all service2 in log
        List<Service2> service2s =db.getService2();
        for(Service2 user: service2s){
            Log.d("goon","Service2" + "\n" +
                    "ID: " + user.getId() + "\n" +
                    "tyres: " + user.getTyres() + "\n" +
                    "light: " + user.getLights() + "\n" +
                    "horn: " + user.getHorn() + "\n" +
                    "suspension: " + user.getSuspension() + "\n" +
                    "brakes: " + user.getBrakes() + "\n" +
                    "fuel_system: " + user.getFuel_system() + "\n" +
                    "exhaust: " + user.getExhaust() + "\n" +
                    "engine: " + user.getEngine() + "\n" +
                    "battery: " + user.getBattery() + "\n" +
                    "full_body: " + user.getFull_body() + "\n" +
                    "id: " + user.getId() + "\n"
            );

        }

        // Display all service4 in log
        List<Service4> service4s = db.getService4();
        for(Service4 user: service4s){
            Log.d("goon","Service4" + "\n" +
                    "ID: " + user.getId() + "\n" +
                    "tyres: " + user.getTyres() + "\n" +
                    "light: " + user.getLights() + "\n" +
                    "horn: " + user.getHorn() + "\n" +
                    "suspension: " + user.getSuspension() + "\n" +
                    "brakes: " + user.getBrakes() + "\n" +
                    "seat_belts: " + user.getSeat_belts() + "\n" +
                    "fuel_system: " + user.getFuel_system() + "\n" +
                    "exhaust: " + user.getExhaust() + "\n" +
                    "engine: " + user.getEngine() + "\n" +
                    "battery: " + user.getBattery() + "\n" +
                    "full_body: " + user.getFull_body() + "\n" +
                    "air_condition: " + user.getAir_condition() + "\n" +
                    "id: " + user.getId() + "\n"
            );

        }

        // Display all nearby garaaj in log
        List<Garaaj> neargaraaj =db.nearGaraaj("area1");
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

        // Deleting Service2
//        db.deleteService2(1);
//        // Deleting Service4
//        db.deleteService4(1);

        // User Login system (Returns true or false)
//        db.loginUser("rajesh@gmail.com", "rajesh2017");

    }
}