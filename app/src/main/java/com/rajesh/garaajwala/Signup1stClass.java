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

import com.google.android.material.textfield.TextInputLayout;
import com.rajesh.garaajwala.data.MyHandler;
import com.rajesh.garaajwala.models.Users;

import java.util.List;

public class Signup1stClass extends AppCompatActivity {

    //Variables
    ImageView backbtn;
    Button b_next, b_login;
    TextView titleText;
    TextInputLayout house,area,street,landmark,state,city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1st_class);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        // Hooks
        backbtn = findViewById(R.id.back_arrow);
        b_next = findViewById(R.id.signup_next_btn);
        b_login = findViewById(R.id.signup_login_btn);
        titleText = findViewById(R.id.signup_title_text);
        house = findViewById(R.id.signup_house);
        area = findViewById(R.id.signup_area);
        street = findViewById(R.id.signup_street);
        landmark = findViewById(R.id.signup_landmark);
        state = findViewById(R.id.signup_state);
        city = findViewById(R.id.signup_city);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), InterfacePage.class));
            }
        });

    }

    public void callNextSignupClass1Screen(View view){

        if (!validateHouse() | !validateArea() |  !validateStreet() |  !validateLandmark() |  !validateState() |  !validateCity()) {
            Log.d("goon","true");
            return;
        }

        Intent intent1 = getIntent();
        String fullnameS = intent1.getStringExtra("fullname");
        String emailS = intent1.getStringExtra("email");
        String passwordS = intent1.getStringExtra("password");
        String houseS = house.getEditText().getText().toString();
        String areaS = area.getEditText().getText().toString();
        String streetS = street.getEditText().getText().toString();
        String landmarkS = landmark.getEditText().getText().toString();
        String stateS = state.getEditText().getText().toString();
        String cityS = city.getEditText().getText().toString();
        Intent intent = new Intent(getApplicationContext(), Signup2ndClass.class);
        intent.putExtra("fullname", fullnameS);
        intent.putExtra("email", emailS);
        intent.putExtra("password", passwordS);
        intent.putExtra("house", houseS);
        intent.putExtra("area", areaS);
        intent.putExtra("street", streetS);
        intent.putExtra("landmark", landmarkS);
        intent.putExtra("state", stateS);
        intent.putExtra("city", cityS);
        MyHandler db =new MyHandler(Signup1stClass.this);
        List<Users> users =db.getAllUser();
        for(Users user: users){
            Log.d("goon","ID: " + user.getId() + "\n" +
                    "Name: " + user.getName() + "\n" +
                    "Email: " + user.getEmail() + "\n" +
                    "Phone Number : " + user.getPhone() + "\n" +
                    "Password : " + user.getPassword() + "\n" +
                    "House : " + user.getHouse_no() + "\n" +
                    "Area : " + user.getArea() + "\n" +
                    "Street : " + user.getStreet() + "\n" +
                    "Landmark : " + user.getLandmark() + "\n" +
                    "State : " + user.getState() + "\n" +
                    "City : " + user.getCity() + "\n" );
            ;
        }
        //Add Transitions
        Pair[] pairs = new Pair[4];

        pairs[0] = new Pair<View, String>(backbtn, "transition_back_arrow");
        pairs[1] = new Pair<View, String>(b_next, "transition_next_btn");
        pairs[2] = new Pair<View, String>(b_login, "transition_login_btn");
        pairs[3] = new Pair<View, String>(titleText, "transition_title_text");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Signup1stClass.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
        finish();
    }

    //Validations Function
    private boolean validateHouse() {
        String val = house.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            house.setError("Field cannot be empty");
            return false;
        } else {
            house.setError(null);
            house.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateArea() {
        String val = area.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            area.setError("Field cannot be empty");
            return false;
        } else {
            area.setError(null);
            area.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateStreet() {
        String val = street.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            street.setError("Field cannot be empty");
            return false;
        } else {
            street.setError(null);
            street.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateLandmark() {
        String val = landmark.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            landmark.setError("Field cannot be empty");
            return false;
        } else {
            landmark.setError(null);
            landmark.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateState() {
        String val = state.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            state.setError("Field cannot be empty");
            return false;
        } else {
            state.setError(null);
            state.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateCity() {
        String val = city.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            city.setError("Field cannot be empty");
            return false;
        } else {
            city.setError(null);
            city.setErrorEnabled(false);
            return true;
        }
    }

}