package com.rajesh.garaajwala.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.Camera;
import android.util.Log;
import android.widget.ListView;

import com.rajesh.garaajwala.models.Garaaj;
import com.rajesh.garaajwala.models.Orders;
import com.rajesh.garaajwala.models.Service2;
import com.rajesh.garaajwala.models.Service4;
import com.rajesh.garaajwala.models.Users;
import com.rajesh.garaajwala.params.Params;
import com.rajesh.garaajwala.params.Params_2service;
import com.rajesh.garaajwala.params.Params_4service;
import com.rajesh.garaajwala.params.Params_Orders;
import com.rajesh.garaajwala.params.Params_garaaj;
import com.rajesh.garaajwala.params.Params_users;

import java.util.ArrayList;
import java.util.List;

public class MyHandler extends SQLiteOpenHelper {

    public MyHandler(Context context){
    super(context, Params.DB_NAME, null ,Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create userTable
        String createUser ="CREATE TABLE " + Params_users.TABLE_NAME + "(" + Params_users.KEY_ID + " INTEGER PRIMARY KEY," + Params_users.KEY_NAME + " TEXT,"
                + Params_users.KEY_EMAIL + " TEXT," + Params_users.KEY_PHONE + " TEXT," + Params_users.KEY_PASSWORD + " TEXT," + Params_users.KEY_HOUSE + " TEXT,"  + Params_users.KEY_STREET + " TEXT," +Params_users.KEY_LANDMARK + " TEXT,"
                +Params_users.KEY_STATE + " TEXT," +Params_users.KEY_CITY + " TEXT," + Params_users.KEY_AREA + " TEXT" + ")";
        db.execSQL(createUser);

        // create garaajTable
        String createGaraaj ="CREATE TABLE " + Params_garaaj.TABLE_NAME + "(" + Params_garaaj.KEY_ID + " INTEGER PRIMARY KEY," + Params_garaaj.KEY_NAME + " TEXT,"
                + Params_garaaj.KEY_EMAIL + " TEXT," + Params_garaaj.KEY_PHONE + " TEXT," + Params_garaaj.KEY_SHOP + " TEXT,"  + Params_garaaj.KEY_STREET + " TEXT," +Params_garaaj.KEY_LANDMARK + " TEXT,"
                +Params_garaaj.KEY_STATE + " TEXT," +Params_garaaj.KEY_CITY + " TEXT," + Params_garaaj.KEY_AREA + " TEXT," + Params_garaaj.KEY_PASSWORD + " TEXT" + ")";
        db.execSQL(createGaraaj);

        // create service2 (1=TRUE , 0=FALSE)
        String createService2 = "CREATE TABLE " + Params_2service.TABLE_NAME + "(" + Params_2service.KEY_TYRES + " INTEGER," + Params_2service.KEY_LIGHT + " INTEGER," + Params_2service.KEY_HORN + " INTEGER," + Params_2service.KEY_SUSPENSION + " INTEGER," + Params_2service.KEY_BRAKES + " INTEGER," + Params_2service.KEY_FUELSYSTEM + " INTEGER,"
                + Params_2service.KEY_EXHAUST + " INTEGER," + Params_2service.KEY_ENGINE + " INTEGER," + Params_2service.KEY_BATTERY + " INTEGER," + Params_2service.KEY_FULLBODY + " INTEGER," + Params_2service.KEY_ID + " INTEGER," + " FOREIGN KEY ("+Params_2service.KEY_ID+") REFERENCES "+Params_garaaj.TABLE_NAME+"("+ Params_garaaj.KEY_ID + "))";
        db.execSQL(createService2);

        // create service4 (1=TRUE , 0=FALSE)
        String createService4 = "CREATE TABLE " + Params_4service.TABLE_NAME + "(" + Params_4service.KEY_TYRES + " INTEGER," + Params_4service.KEY_LIGHT + " INTEGER," + Params_4service.KEY_HORN + " INTEGER," + Params_4service.KEY_SUSPENSION + " INTEGER,"
                + Params_4service.KEY_BRAKES + " INTEGER," + Params_4service.KEY_SEAT + " INTEGER," + Params_4service.KEY_FUELSYSTEM + " INTEGER," + Params_4service.KEY_EXHAUST + " INTEGER," + Params_4service.KEY_ENGINE + " INTEGER," + Params_4service.KEY_BATTERY + " INTEGER," + Params_4service.KEY_FULLBODY + " INTEGER," + Params_4service.KEY_AIRCONDITION + " INTEGER," + Params_4service.KEY_ID + " INTEGER," + " FOREIGN KEY ("+Params_4service.KEY_ID+") REFERENCES "+Params_garaaj.TABLE_NAME+"("+ Params_garaaj.KEY_ID +") )";
        db.execSQL(createService4);

        // create BookingTable
        String createBooking ="CREATE TABLE " + Params_Orders.TABLE_NAME + "(" + Params_Orders.KEY_ID + " INTEGER PRIMARY KEY," + Params_Orders.KEY_USER + " TEXT,"
                + Params_Orders.KEY_GARAGE + " TEXT" +  ")";
        db.execSQL(createBooking);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // SignIN users
    public boolean signUser(Users users){
        SQLiteDatabase db = this.getWritableDatabase();
        
        // creating the inserting values
        ContentValues values = new ContentValues();
        values.put(Params_users.KEY_NAME, users.getName());
        values.put(Params_users.KEY_EMAIL, users.getEmail());
        values.put(Params_users.KEY_PHONE, users.getPhone());
        values.put(Params_users.KEY_PASSWORD, users.getPassword());
        values.put(Params_users.KEY_HOUSE, users.getHouse_no());
        values.put(Params_users.KEY_STREET, users.getStreet());
        values.put(Params_users.KEY_LANDMARK, users.getLandmark());
        values.put(Params_users.KEY_STATE, users.getState());
        values.put(Params_users.KEY_CITY, users.getCity());
        values.put(Params_users.KEY_AREA, users.getArea());
        // inserting values in database
        db.insert(Params_users.TABLE_NAME, null, values);
        Log.d("goon", "Successfully user insert");
        db.close();
        return true;
        }

    //Check Email
    public boolean checkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        // check the email exists
        Cursor cursor = db.rawQuery("SELECT * FROM " + Params_users.TABLE_NAME + " WHERE " + Params_users.KEY_EMAIL + " =?" , new String[]{email});
        int cursorCount = cursor.getCount();
        if(cursorCount > 0){
            Log.d("goon", "Email or phone already exists");
            return false;
        }
        else {
            return true;
        }
    }

    //Check PhoneNo
    public boolean checkPhone(String phone){
        SQLiteDatabase db = this.getReadableDatabase();
        // check the email exists
        Cursor cursor = db.rawQuery("SELECT * FROM " + Params_users.TABLE_NAME + " WHERE " + Params_users.KEY_PHONE + " =?" , new String[]{phone});
        int cursorCount = cursor.getCount();
        if(cursorCount > 0){
            Log.d("goon", "Email or phone already exists");
            return false;
        }
        else {
            return true;
        }
    }

    //Check Garage Email
    public boolean checkgarageemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        // check the email exists
        Cursor cursor = db.rawQuery("SELECT * FROM " + Params_garaaj.TABLE_NAME + " WHERE " + Params_garaaj.KEY_EMAIL + " =?" , new String[]{email});
        int cursorCount = cursor.getCount();
        if(cursorCount > 0){
            Log.d("goon", "Email or phone already exists");
            return false;
        }
        else {
            return true;
        }
    }

    //Check Garage PhoneNo
    public boolean checkgaragePhone(String phone){
        SQLiteDatabase db = this.getReadableDatabase();
        // check the email exists
        Cursor cursor = db.rawQuery("SELECT * FROM " + Params_garaaj.TABLE_NAME + " WHERE " + Params_garaaj.KEY_PHONE + " =?" , new String[]{phone});
        int cursorCount = cursor.getCount();
        if(cursorCount > 0){
            Log.d("goon", "Email or phone already exists");
            return false;
        }
        else {
            return true;
        }
    }

    // Get all users
    public List<Users> getAllUser(){
        List<Users> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

//      Generating the query to show all data from database
        String select = "SELECT * FROM " + Params_users.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        // Looping through the data
        if(cursor.moveToFirst()){
            do{
                Users users = new Users();
                users.setId(Integer.parseInt(cursor.getString(0)));
                users.setName(cursor.getString(1));
                users.setEmail(cursor.getString(2));
                users.setPhone(cursor.getString(3));
                users.setPassword(cursor.getString(4));
                users.setHouse_no(cursor.getString(5));
                users.setStreet(cursor.getString(6));
                users.setLandmark(cursor.getString(7));
                users.setState(cursor.getString(8));
                users.setCity(cursor.getString(9));
                users.setArea(cursor.getString(10));
                userList.add(users);
            }while (cursor.moveToNext());
        }
        return userList;
    }

    //Get Users Details
    public List<Users> getUser(String email){
        List<Users> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

//      Generating the query to show all data from database
        Cursor cursor = db.rawQuery("select * from " + Params_users.TABLE_NAME +" where "+ Params_users.KEY_EMAIL + " =?", new String[]{email});
        // Looping through the data
        if(cursor.moveToFirst()){
            do{
                Users users = new Users();
                users.setId(Integer.parseInt(cursor.getString(0)));
                users.setName(cursor.getString(1));
                users.setEmail(cursor.getString(2));
                users.setPhone(cursor.getString(3));
                users.setPassword(cursor.getString(4));
                users.setHouse_no(cursor.getString(5));
                users.setStreet(cursor.getString(6));
                users.setLandmark(cursor.getString(7));
                users.setState(cursor.getString(8));
                users.setCity(cursor.getString(9));
                users.setArea(cursor.getString(10));
                userList.add(users);
            }while (cursor.moveToNext());
        }
        return userList;
    }

    // SignIN garaaj
    public boolean signGaraaj(Garaaj garaaj){
        SQLiteDatabase db = this.getWritableDatabase();

        // creating the inserting values
        ContentValues values = new ContentValues();
        values.put(Params_garaaj.KEY_NAME, garaaj.getOwner_name());
        values.put(Params_garaaj.KEY_EMAIL, garaaj.getEmail());
        values.put(Params_garaaj.KEY_PHONE, garaaj.getPhone());
        values.put(Params_garaaj.KEY_SHOP, garaaj.getShop_no());
        values.put(Params_garaaj.KEY_STREET, garaaj.getStreet());
        values.put(Params_garaaj.KEY_LANDMARK, garaaj.getLandmark());
        values.put(Params_garaaj.KEY_STATE, garaaj.getState());
        values.put(Params_garaaj.KEY_CITY, garaaj.getCity());
        values.put(Params_garaaj.KEY_AREA, garaaj.getArea());
        values.put(Params_garaaj.KEY_PASSWORD, garaaj.getPassword());

        // inserting values in database
        db.insert(Params_garaaj.TABLE_NAME, null, values);
        Log.d("goon", "Successfully garaaj insert");
        db.close();
        return true;
    }

    // Get all garaaj
    public List<Garaaj> getAllGaraaj(){
        List<Garaaj> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

//      Generating the query to show all data from database
        String select = "SELECT * FROM " + Params_garaaj.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        // Looping through the data
        if(cursor.moveToFirst()){
            do{
                Garaaj users = new Garaaj();
                users.setId(Integer.parseInt(cursor.getString(0)));
                users.setOwner_name(cursor.getString(1));
                users.setEmail(cursor.getString(2));
                users.setPhone(cursor.getString(3));
                users.setShop_no(cursor.getString(4));
                users.setStreet(cursor.getString(5));
                users.setLandmark(cursor.getString(6));
                users.setState(cursor.getString(7));
                users.setCity(cursor.getString(8));
                users.setArea(cursor.getString(9));
                users.setPassword(cursor.getString(10));
                userList.add(users);
            }while (cursor.moveToNext());
        }
        return userList;
    }

    // SignIN service2
    public void signService2(Service2 service2){
        SQLiteDatabase db = this.getWritableDatabase();

        // creating the inserting values
        ContentValues values = new ContentValues();
        values.put(Params_2service.KEY_ID, service2.getId());
        values.put(Params_2service.KEY_TYRES, service2.getTyres());
        values.put(Params_2service.KEY_LIGHT, service2.getLights());
        values.put(Params_2service.KEY_HORN, service2.getHorn());
        values.put(Params_2service.KEY_SUSPENSION, service2.getSuspension());
        values.put(Params_2service.KEY_BRAKES, service2.getBrakes());
        values.put(Params_2service.KEY_FUELSYSTEM, service2.getFuel_system());
        values.put(Params_2service.KEY_EXHAUST, service2.getExhaust());
        values.put(Params_2service.KEY_ENGINE, service2.getEngine());
        values.put(Params_2service.KEY_BATTERY, service2.getBattery());
        values.put(Params_2service.KEY_FULLBODY, service2.getFull_body());

        // inserting values in database
        db.insert(Params_2service.TABLE_NAME, null, values);
        Log.d("goon", "Successfully service2 insert");
        db.close();
    }

    // Get all service2
    public List<Service2> getService2(){
        List<Service2> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

//      Generating the query to show all data from database
        String select = "SELECT * FROM " + Params_2service.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        // Looping through the data
        if(cursor.moveToFirst()){
            do{
                Service2 users = new Service2();
                users.setTyres(Integer.parseInt(cursor.getString(0)));
                users.setLights(Integer.parseInt(cursor.getString(1)));
                users.setHorn(Integer.parseInt(cursor.getString(2)));
                users.setSuspension(Integer.parseInt(cursor.getString(3)));
                users.setBrakes(Integer.parseInt(cursor.getString(4)));
                users.setFuel_system(Integer.parseInt(cursor.getString(5)));
                users.setExhaust(Integer.parseInt(cursor.getString(6)));
                users.setEngine(Integer.parseInt(cursor.getString(7)));
                users.setBattery(Integer.parseInt(cursor.getString(8)));
                users.setFull_body(Integer.parseInt(cursor.getString(9)));
                users.setId(Integer.parseInt(cursor.getString(10)));
                userList.add(users);
            }while (cursor.moveToNext());
        }
        return userList;
    }

    // SignIN Service4
    public void signService4(Service4 service4){
        SQLiteDatabase db = this.getWritableDatabase();

        // creating the inserting values
        ContentValues values = new ContentValues();
        values.put(Params_4service.KEY_ID, service4.getId());
        values.put(Params_4service.KEY_TYRES, service4.getTyres());
        values.put(Params_4service.KEY_LIGHT, service4.getLights());
        values.put(Params_4service.KEY_HORN, service4.getHorn());
        values.put(Params_4service.KEY_SUSPENSION, service4.getSuspension());
        values.put(Params_4service.KEY_BRAKES, service4.getBrakes());
        values.put(Params_4service.KEY_SEAT, service4.getSeat_belts());
        values.put(Params_4service.KEY_FUELSYSTEM, service4.getFuel_system());
        values.put(Params_4service.KEY_EXHAUST, service4.getExhaust());
        values.put(Params_4service.KEY_ENGINE, service4.getEngine());
        values.put(Params_4service.KEY_BATTERY, service4.getBattery());
        values.put(Params_4service.KEY_FULLBODY, service4.getFull_body());
        values.put(Params_4service.KEY_AIRCONDITION, service4.getAir_condition());

        // inserting values in database
        db.insert(Params_4service.TABLE_NAME, null, values);
        Log.d("goon", "Successfully service4 insert");
        db.close();
    }

    // Get all service4
    public List<Service4> getService4(){
        List<Service4> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

//      Generating the query to show all data from database
        String select = "SELECT * FROM " + Params_4service.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        // Looping through the data
        if(cursor.moveToFirst()){
            do{
                Service4 users = new Service4();
                users.setTyres(Integer.parseInt(cursor.getString(0)));
                users.setLights(Integer.parseInt(cursor.getString(1)));
                users.setHorn(Integer.parseInt(cursor.getString(2)));
                users.setSuspension(Integer.parseInt(cursor.getString(3)));
                users.setBrakes(Integer.parseInt(cursor.getString(4)));
//                users.setSeat_belts(Integer.parseInt(cursor.getString(5)));
                users.setFuel_system(Integer.parseInt(cursor.getString(6)));
                users.setExhaust(Integer.parseInt(cursor.getString(7)));
                users.setEngine(Integer.parseInt(cursor.getString(8)));
                users.setBattery(Integer.parseInt(cursor.getString(9)));
                users.setFull_body(Integer.parseInt(cursor.getString(10)));
//                users.setAir_condition(Integer.parseInt(cursor.getString(11)));
                users.setId(Integer.parseInt(cursor.getString(12)));
                userList.add(users);
            }while (cursor.moveToNext());
        }
        return userList;
    }

    // Getting the near by garaaj
    public List<Garaaj> nearGaraaj(String area){
        List<Garaaj> garaajList = new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();

        // Search query
        Cursor cursor = db.rawQuery("select * from " + Params_garaaj.TABLE_NAME +" where "+ Params_garaaj.KEY_AREA + " =?", new String[]{area});
        Log.d("goon", "count "+ cursor.getCount());
        if(cursor.moveToFirst()){
            do{
                Garaaj users = new Garaaj();
                users.setId(Integer.parseInt(cursor.getString(0)));
                users.setOwner_name(cursor.getString(1));
                users.setEmail(cursor.getString(2));
                users.setPhone(cursor.getString(3));
                users.setShop_no(cursor.getString(4));
                users.setStreet(cursor.getString(5));
                users.setLandmark(cursor.getString(6));
                users.setState(cursor.getString(7));
                users.setCity(cursor.getString(8));
                users.setArea(cursor.getString(9));
                users.setPassword(cursor.getString(10));
                garaajList.add(users);
            }while (cursor.moveToNext());
        }else {
            Log.d("goon", "There are no Garaaj's available nearby");
            return garaajList;
        }
        return garaajList;
    }

    // Get specific service2
    public List<Service2> nearService2(int id){
        List<Service2> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

//      Generating the query to show all data from database
        Cursor cursor = db.rawQuery("SELECT * FROM " + Params_2service.TABLE_NAME + " WHERE " + Params_2service.KEY_ID + " =?", new String[]{String.valueOf(id)});

        // Looping through the data
        if(cursor.moveToFirst()){
            do{
                Service2 users = new Service2();
                users.setTyres(Integer.parseInt(cursor.getString(0)));
                users.setLights(Integer.parseInt(cursor.getString(1)));
                users.setHorn(Integer.parseInt(cursor.getString(2)));
                users.setSuspension(Integer.parseInt(cursor.getString(3)));
                users.setBrakes(Integer.parseInt(cursor.getString(4)));
                users.setFuel_system(Integer.parseInt(cursor.getString(5)));
                users.setExhaust(Integer.parseInt(cursor.getString(6)));
                users.setEngine(Integer.parseInt(cursor.getString(7)));
                users.setBattery(Integer.parseInt(cursor.getString(8)));
                users.setFull_body(Integer.parseInt(cursor.getString(9)));
                users.setId(Integer.parseInt(cursor.getString(10)));
                userList.add(users);
            }while (cursor.moveToNext());
        }
        return userList;
    }

    // Get specific service4
    public List<Service4> nearService4(int id){
        List<Service4> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

//      Generating the query to show all data from database
        Cursor cursor = db.rawQuery("SELECT * FROM " + Params_4service.TABLE_NAME + " WHERE " + Params_4service.KEY_ID + " =?", new String[]{String.valueOf(id)});

        // Looping through the data
        if(cursor.moveToFirst()){
            do{
                Service4 users = new Service4();
                users.setTyres(Integer.parseInt(cursor.getString(0)));
                users.setLights(Integer.parseInt(cursor.getString(1)));
                users.setHorn(Integer.parseInt(cursor.getString(2)));
                users.setSuspension(Integer.parseInt(cursor.getString(3)));
                users.setBrakes(Integer.parseInt(cursor.getString(4)));
//                users.setSeat_belts(Integer.parseInt(cursor.getString(5)));
                users.setFuel_system(Integer.parseInt(cursor.getString(6)));
                users.setExhaust(Integer.parseInt(cursor.getString(7)));
                users.setEngine(Integer.parseInt(cursor.getString(8)));
                users.setBattery(Integer.parseInt(cursor.getString(9)));
                users.setFull_body(Integer.parseInt(cursor.getString(10)));
//                users.setAir_condition(Integer.parseInt(cursor.getString(11)));
                users.setId(Integer.parseInt(cursor.getString(12)));
                userList.add(users);
            }while (cursor.moveToNext());
        }
        return userList;
    }

    // deleting any Service2
    public void deleteService2(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params_2service.TABLE_NAME, Params_2service.KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // deleting any Service2
    public void deleteService4(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params_4service.TABLE_NAME, Params_4service.KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // User Login system
    public boolean loginUser(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        boolean check;
        Cursor cursor = db.rawQuery("SELECT * FROM " + Params_users.TABLE_NAME + " WHERE " + Params_users.KEY_EMAIL + " =?" + " AND " + Params_users.KEY_PASSWORD + " =?", new String[]{email,password});
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if(cursorCount > 0){
            Log.d("goon","The email and Password is correct for user login");
            check = true;
        }
        else{
            Log.d("goon","The email and Password is incorrect for user login");
            check = false;
        }
        return check;
    }

    // Garaaj Login system
    public boolean loginGaraaj(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        boolean check;
        Cursor cursor = db.rawQuery("SELECT * FROM " + Params_garaaj.TABLE_NAME + " WHERE " + Params_garaaj.KEY_EMAIL + " =?" + " AND " + Params_garaaj.KEY_PASSWORD + " =?", new String[]{email,password});
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if(cursorCount > 0){
            Log.d("goon","The email and Password is correct for garaaj login");
            check = true;
        }
        else{
            Log.d("goon","The email and Password is incorrect for garaaj login");
            check = false;
        }
        return check;
    }

    //Orders
    public void signOrders(Orders orders){
        SQLiteDatabase db = this.getWritableDatabase();

        // creating the inserting values
        ContentValues values = new ContentValues();
        values.put(Params_Orders.KEY_USER, orders.getUser());
        values.put(Params_Orders.KEY_GARAGE, orders.getGarage());
        // inserting values in database
        db.insert(Params_Orders.TABLE_NAME, null, values);
        Log.d("goon", "Successfully user insert");
        db.close();

    }

    //Get Orders
    public List<Orders> getOrders(){
        List<Orders> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

//      Generating the query to show all data from database
        String select = "SELECT * FROM " + Params_Orders.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        // Looping through the data
        if(cursor.moveToFirst()){
            do{
                Orders users = new Orders();
                users.setId(Integer.parseInt(cursor.getString(0)));
                users.setUser(cursor.getString(1));
                users.setGarage(cursor.getString(2));
                userList.add(users);
            }while (cursor.moveToNext());
        }
        return userList;
    }

    //Get Orders by garage
    public List<Orders> getgarageOrders(String shop_email){
        List<Orders> garaajList = new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();
        Log.d("goon", "Started ");
        // Search query
        Cursor cursor = db.rawQuery("SELECT * FROM " + Params_Orders.TABLE_NAME +" where "+ Params_Orders.KEY_GARAGE + " =?", new String[]{shop_email});
        Log.d("goon", "count "+ cursor);
        if(cursor.moveToFirst()){
            do{
                Orders users = new Orders();
                users.setId(Integer.parseInt(cursor.getString(0)));
                users.setUser(cursor.getString(1));
                users.setGarage(cursor.getString(2));
                garaajList.add(users);
            }while (cursor.moveToNext());
        }else {
            Log.d("goon", "There are no Bookings");
            return garaajList;
        }
        return garaajList;
    }
}
