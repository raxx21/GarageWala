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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;
import com.rajesh.garaajwala.data.MyHandler;
import com.rajesh.garaajwala.data.SessionManager;
import com.rajesh.garaajwala.models.Garaaj;
import com.rajesh.garaajwala.models.Users;

import java.util.List;

public class Signup_garage2 extends AppCompatActivity {

    ImageView backbtn;
    Button b_next, b_login;
    TextView titleText;
    TextInputLayout phone;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_garage2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        //Hooks
        phone = findViewById(R.id.signup_phone);
        countryCodePicker = findViewById(R.id.country_code);
        backbtn = findViewById(R.id.back_arrow);
        b_next = findViewById(R.id.signup_next_btn);
        b_login = findViewById(R.id.signup_login_btn);
        titleText = findViewById(R.id.signup_title_text);

        MyHandler db =new MyHandler(Signup_garage2.this);
        Garaaj raj = new Garaaj();
        List<Garaaj> users =db.getAllGaraaj();
        for(Garaaj user: users){
            Log.d("goon","ID: " + user.getId() + "\n" +
                    "Name: " + user.getOwner_name() + "\n" +
                    "Email: " + user.getEmail() + "\n" +
                    "Phone Number : " + user.getPhone() + "\n" +
                    "Password : " + user.getPassword() + "\n" +
                    "House : " + user.getShop_no() + "\n" +
                    "Area : " + user.getArea() + "\n" +
                    "Street : " + user.getStreet() + "\n" +
                    "Landmark : " + user.getLandmark() + "\n" +
                    "State : " + user.getState() + "\n" +
                    "City : " + user.getCity() + "\n" );
            ;
        }
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), InterfacePage.class));
            }
        });
    }
    public void callSignupScreen(View view) {

        if (!validatePhone()) {
            Log.d("goon","true");
            return;
        }

        MyHandler db =new MyHandler(Signup_garage2.this);

        Intent intent1 = getIntent();
        Intent intent = new Intent(getApplicationContext(),GarageDashboard.class);
        String fullnameS = intent1.getStringExtra("fullname");
        String emailS = intent1.getStringExtra("email");
        String passwordS = intent1.getStringExtra("password");
        String phoneS = phone.getEditText().getText().toString().trim();
        String houseS = intent1.getStringExtra("house");
        String areaS = intent1.getStringExtra("area");
        String streetS = intent1.getStringExtra("street");
        String landmarkS = intent1.getStringExtra("landmark");
        String stateS = intent1.getStringExtra("state");
        String cityS = intent1.getStringExtra("city");

        //Add Transitions
        Pair[] pairs = new Pair[4];

        pairs[0] = new Pair<View, String>(backbtn, "transition_back_arrow");
        pairs[1] = new Pair<View, String>(b_next, "transition_next_btn");
        pairs[2] = new Pair<View, String>(b_login, "transition_login_btn");
        pairs[3] = new Pair<View, String>(titleText, "transition_title_text");

        // add user
        Garaaj raj = new Garaaj();
        raj.setOwner_name(fullnameS);
        raj.setEmail(emailS);
        raj.setPhone(phoneS);
        raj.setPassword(passwordS);
        raj.setShop_no(houseS);
        raj.setArea(areaS);
        raj.setStreet(streetS);
        raj.setLandmark(landmarkS);
        raj.setState(stateS);
        raj.setCity(cityS);
        boolean check = db.signGaraaj(raj);

        //Creating Session
        SessionManager sessionManager = new SessionManager(this);
        sessionManager.createLoginSession(fullnameS,emailS,phoneS,passwordS,houseS,areaS,streetS,landmarkS,stateS,cityS);

        if(check){
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Signup_garage2.this, pairs);
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
            finish();
            Toast.makeText(getApplicationContext(),"successfully Signed In",Toast.LENGTH_SHORT).show();
        }else Toast.makeText(getApplicationContext(),"Not Signed In",Toast.LENGTH_SHORT).show();



    }

    private boolean validatePhone(){
        MyHandler db =new MyHandler(Signup_garage2.this);
        String val = phone.getEditText().getText().toString().trim();
        String checkPassword = "(0/91)?[7-9][0-9]{9}";
        if (val.isEmpty()) {
            phone.setError("Field cannot be empty");
            return false;
        }
        else if(!val.matches(checkPassword)){
            phone.setError("Only 10 Numbers!");
            return false;
        }else if(!db.checkgaragePhone(val)){
            phone.setError("Phone No Already Exists!");
            return false;
        }
        else {
            phone.setError(null);
            phone.setErrorEnabled(false);
            return true;
        }
    }

}