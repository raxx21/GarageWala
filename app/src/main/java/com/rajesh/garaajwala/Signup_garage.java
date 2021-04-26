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

public class Signup_garage extends AppCompatActivity {

    //Variables
    ImageView backbtn;
    Button b_next, b_login;
    TextView titleText;

    //Get Data Variables
    TextInputLayout fullname, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_garage);
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

        //Hooks for getting Data
        fullname = findViewById(R.id.signup_fullname);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), InterfacePage.class));
            }
        });
        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginGarage.class));
            }
        });
    }

    public void callNextSignupScreen(View view) {

        if (!validateFullName() | !validateEmail() |  !validatePassword()) {
            Log.d("goon","true");
            return;
        }

        String fullnameS = fullname.getEditText().getText().toString();
        String emailS = email.getEditText().getText().toString();
        String passwordS = password.getEditText().getText().toString();

        Intent intent = new Intent(getApplicationContext(), Signup_garage1.class);
        intent.putExtra("fullname", fullnameS);
        intent.putExtra("email", emailS);
        intent.putExtra("password", passwordS);
        //Add Transitions
        Pair[] pairs = new Pair[4];

        pairs[0] = new Pair<View, String>(backbtn, "transition_back_arrow");
        pairs[1] = new Pair<View, String>(b_next, "transition_next_btn");
        pairs[2] = new Pair<View, String>(b_login, "transition_login_btn");
        pairs[3] = new Pair<View, String>(titleText, "transition_title_text");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Signup_garage.this, pairs);
            startActivity(intent, options.toBundle());
        }
        else {
            startActivity(intent);
        }
        finish();

    }

    //Validations Function
    private boolean validateFullName() {
        String val = fullname.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            fullname.setError("Field cannot be empty");
            return false;
        } else {
            fullname.setError(null);
            fullname.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateEmail() {
        MyHandler db =new MyHandler(Signup_garage.this);
        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        }else if(!val.matches(checkEmail)){
            email.setError("Invalid Email!");
            return false;
        }else if(!db.checkgarageemail(val)){
            email.setError("Email Already Exists!");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePassword() {
        String val = password.getEditText().getText().toString().trim();
        String checkPassword = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        }else if(!val.matches(checkPassword)){
            password.setError("Password should contain 8 characters, at least 1 lower case letter, at least 1 upper case letter, at least 1 special character!");
            return false;
        }
        else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
}