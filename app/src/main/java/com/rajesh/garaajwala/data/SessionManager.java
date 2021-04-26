package com.rajesh.garaajwala.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    //Variables
    SharedPreferences usersSession;
    SharedPreferences.Editor editor;
    Context context;

    public static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_HOUSE = "house_no";
    public static final String KEY_AREA = "area";
    public static final String KEY_STREET = "street";
    public static final String KEY_LANDMARK = "landmark";
    public static final String KEY_STATE = "state";
    public static final String KEY_CITY = "city";
    ;

    public SessionManager(Context _context) {
        context = _context;
        usersSession = context.getSharedPreferences("userLoginSession", Context.MODE_PRIVATE);
        editor = usersSession.edit();
    }

    public void createLoginSession(String name, String email, String phone, String password, String house_no, String area, String street, String landmark, String state, String city) {
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_HOUSE, house_no);
        editor.putString(KEY_AREA, area);
        editor.putString(KEY_STREET, street);
        editor.putString(KEY_LANDMARK, landmark);
        editor.putString(KEY_STATE, state);
        editor.putString(KEY_CITY, city);
        editor.commit();
    }

    public HashMap<String, String> getUsersDetailFromSession() {

        HashMap<String, String> userData = new HashMap<String, String>();

        userData.put(KEY_NAME, usersSession.getString(KEY_NAME, null));
        userData.put(KEY_EMAIL, usersSession.getString(KEY_EMAIL, null));
        userData.put(KEY_PHONE, usersSession.getString(KEY_PHONE, null));
        userData.put(KEY_PASSWORD, usersSession.getString(KEY_PASSWORD, null));
        userData.put(KEY_HOUSE, usersSession.getString(KEY_HOUSE, null));
        userData.put(KEY_AREA, usersSession.getString(KEY_AREA, null));
        userData.put(KEY_STREET, usersSession.getString(KEY_STREET, null));
        userData.put(KEY_LANDMARK, usersSession.getString(KEY_LANDMARK, null));
        userData.put(KEY_STATE, usersSession.getString(KEY_STATE, null));
        userData.put(KEY_CITY, usersSession.getString(KEY_CITY, null));
        return userData;
    }

    public boolean checkLogin() {
        if (usersSession.getBoolean(IS_LOGIN, false)) {
            return true;
        } else return false;
    }

    public void logoutUserSession() {
        editor.clear();
        editor.commit();
    }
}
