package com.magnus.farmerportal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperFarmer extends SQLiteOpenHelper {
    public static final String TAG = DBHelper.class.getSimpleName();
    public static final String DB_NAME = "farmer.db";

    public static final String USER_TABLE = "users";
    public static final String ColumnID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_CNFRMPASS = "cnfrmpassword";
    public static final String PHONE_NUMBER = "phone";

    public static final String FARMER_DETAILS = "FarmerDetails";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_CROP = "crop";
    public static final String FARMER_ID = "farmerID";
    public static final String COLUMNID_FARMER = "ID";
    public  SQLiteDatabase db ;

    public DBHelperFarmer(Context context) {
        super(context, DB_NAME, null, 3);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + USER_TABLE +
                "(" + ColumnID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_NAME + " TEXT, " + COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " + COLUMN_CNFRMPASS + " TEXT ," +PHONE_NUMBER +"TEXT"+")");

        db.execSQL("CREATE TABLE "+ FARMER_DETAILS+"("+  COLUMNID_FARMER + " INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_PRICE + " REAL, " +
                COLUMN_QUANTITY + " INTEGER, " + FARMER_ID + " INTEGER, " + "FOREIGN KEY("+FARMER_ID+") REFERENCES "+USER_TABLE+
                "("+ColumnID+")"+")");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FARMER_DETAILS);
        onCreate(db);
    }

    /* Storing User details*/

    public void addUser(String name, String username, String password, String cnfrmpassword,String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_CNFRMPASS, cnfrmpassword);
        values.put(PHONE_NUMBER, phone);
        db.insert(USER_TABLE, null, values);
        db.close();
    }

    public void addCropDetails(String price, String quantity, String crop,String columnID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRICE, price);
        values.put(COLUMN_QUANTITY, quantity);
        values.put(COLUMN_CROP, crop);
        values.put(FARMER_ID,columnID );
        db.insert(FARMER_DETAILS, null, values);
        db.close();
    }


    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + USER_TABLE, null);
    }
    public Cursor getFarmerDetails(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + FARMER_DETAILS, null);
    }

}
