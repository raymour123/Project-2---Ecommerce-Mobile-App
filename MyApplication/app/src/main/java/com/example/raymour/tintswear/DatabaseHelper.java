package com.example.raymour.tintswear;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by raymour on 7/26/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TintsDatabase";

    public static final String TINTSINVENTORY_TABLE_NAME = "TintsInventory";
    public static final String COL_ID = "_id";
    public static final String COL_SUNGLASS_NAME = "sunglass_name";
    public static final double COL_SUNGLASS_PRICE = 29.99;
    public static final String COL_SUNGLASS_DESCRIPTION = "sunglasses_description";
    public static final String COL_SUNGLASS_IMAGELOCATION = "R.id.blackbetty";

    public static final String SUNGLASSSALE_TABLE_NAME = "SunglassSales";
    public static final String COL_SUNGLASSSALE_NAME = "Sunglass_Sale_Name";
    public static final double COL_SUNGLASSSALE_PRICE = 24.99;
    public static final String COL_SUNGLASSSALE_COUPONCODE = "couponcode";

    //created database with two tables

    private static DatabaseHelper sInstance;

    public static DatabaseHelper getsInstance(Context context){
        if(sInstance == null){
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //created the SQLiteDB constructor and override onCreate and upgrade.

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TINTSINVENTORY_TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY, " +
                COL_SUNGLASS_NAME + " TEXT, " +
                COL_SUNGLASS_PRICE + " REAL, " +
                COL_SUNGLASS_DESCRIPTION + " TEXT, " +
                COL_SUNGLASS_IMAGELOCATION + " TEXT )");

        sqLiteDatabase.execSQL("CREATE TABLE " + SUNGLASSSALE_TABLE_NAME + " (" +
                COL_SUNGLASSSALE_NAME + " TEXT, " +
                COL_SUNGLASSSALE_PRICE + " REAL, " +
                COL_SUNGLASSSALE_COUPONCODE + " TEXT, " +
                "FOREIGN KEY(" + COL_SUNGLASSSALE_NAME +") " + "REFERENCES " + TINTSINVENTORY_TABLE_NAME
                + "(" + COL_SUNGLASS_NAME + "))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TINTSINVENTORY_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SUNGLASSSALE_TABLE_NAME);
        sqLiteDatabase.close();
    }



}
