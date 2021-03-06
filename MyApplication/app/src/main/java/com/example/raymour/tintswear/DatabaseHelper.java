package com.example.raymour.tintswear;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raymour on 7/26/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TintsDatabase";

    public static final String TINTSINVENTORY_TABLE_NAME = "TintsInventory";
    public static final String COL_ID = "_id";
    public static final String COL_SUNGLASS_NAME = "sunglass_name";
    public static final String COL_SUNGLASS_IMAGELOCATION = "image_location";
    public static final String COL_SUNGLASS_PRICE = "sunglass_price";
    public static final String COL_SUNGLASS_DESCRIPTION = "sunglasses_description";


    public static final String[] INVENTORY_COLUMNS = {COL_ID, COL_SUNGLASS_NAME, COL_SUNGLASS_DESCRIPTION};

    public static final String SUNGLASSSALE_TABLE_NAME = "SunglassSales";
    public static final String COL_SUNGLASSSALE_NAME = "sunglass_sale_name";
    public static final String COL_SUNGLASSSALE_PRICE = "sunglass_sale_price";
    public static final String COL_SUNGLASSSALE_COUPONCODE = "coupon_code";

    //created database with two tables

    private static DatabaseHelper sInstance;

    public static DatabaseHelper getsInstance(Context context){
        if(sInstance == null){
            sInstance = new DatabaseHelper(context);
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
                COL_SUNGLASS_PRICE + " TEXT, " +
                COL_SUNGLASS_DESCRIPTION + " TEXT, " +
                COL_SUNGLASS_IMAGELOCATION + " TEXT )");

        sqLiteDatabase.execSQL("CREATE TABLE " + SUNGLASSSALE_TABLE_NAME + " (" +
                COL_SUNGLASSSALE_NAME + " TEXT, " +
                COL_SUNGLASSSALE_PRICE + " TEXT, " +
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

    //inserts rows for inventory database
    public void insertRowTintsInventory (Inventory inventory) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_SUNGLASS_NAME, inventory.getSunglassName());
        values.put(COL_SUNGLASS_IMAGELOCATION, inventory.getSunglassImageLocation());
        values.put(COL_SUNGLASS_PRICE, inventory.getSunglassPrice());
        values.put(COL_SUNGLASS_DESCRIPTION, inventory.getSunglassDescription());
        sqLiteDatabase.insertOrThrow(TINTSINVENTORY_TABLE_NAME, null, values);
    }

    public void insertRowSunglassSale (SunglassSale sunglassSale){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_SUNGLASSSALE_NAME, sunglassSale.getSunglassSaleName());
        values.put(COL_SUNGLASSSALE_PRICE, sunglassSale.getSunglassSalePrice());
        values.put(COL_SUNGLASSSALE_COUPONCODE, sunglassSale.getSunglassCouponCode());
        sqLiteDatabase.insertOrThrow(SUNGLASSSALE_TABLE_NAME, null, values);

    }

    public List<Inventory> getInventoryList(){
        List<Inventory> sunglassList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String query = "SELECT * FROM " + TINTSINVENTORY_TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Inventory inventory1 = new Inventory(cursor.getString(cursor.getColumnIndex(COL_SUNGLASS_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_SUNGLASS_IMAGELOCATION)),
                        cursor.getString(cursor.getColumnIndex(COL_SUNGLASS_PRICE)),
                        cursor.getString(cursor.getColumnIndex(COL_SUNGLASS_DESCRIPTION)));
                sunglassList.add(inventory1);
                cursor.moveToNext();

            }
        }

        database.close();
        return sunglassList;
    }

    //adds a row to the shopping cart
    public void addToCart(Inventory sunglass){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        String sunglassName = sunglass.getSunglassName();
        String sunglassPrice = sunglass.getSunglassPrice();
        values.put(COL_SUNGLASSSALE_NAME, sunglassName);
        values.put(COL_SUNGLASSSALE_PRICE, sunglassPrice);
        long name = db.insertOrThrow(SUNGLASSSALE_TABLE_NAME, null, values);
        db.close();

    }

    //turns table into a list of object to pass to the RV adapter
    public ArrayList<SunglassSale> getCartItemsObjects() {
        ArrayList<SunglassSale> shoppingCartList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + SUNGLASSSALE_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                SunglassSale sunglassSale1 = new SunglassSale(cursor.getString(cursor.getColumnIndex(COL_SUNGLASSSALE_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_SUNGLASSSALE_PRICE)),
                        cursor.getString(cursor.getColumnIndex(COL_SUNGLASSSALE_COUPONCODE)));
                shoppingCartList.add(sunglassSale1);

                cursor.moveToNext();

            }
        }
        cursor .close();
        return shoppingCartList;
    }

//    public int getIdFromCartObject (SunglassSale item) {
//        int id = 0;
//        String sunglassName = String.valueOf(item.getSunglassSaleName());
//        SQLiteDatabase database = getReadableDatabase();
//        String query = "SELECT " + COL_ID + " FROM " +
//                TINTSINVENTORY_TABLE_NAME + " WHERE " +
//                COL_SUNGLASS_NAME + " LIKE " + sunglassName;
//        Cursor cursor = database.rawQuery(query, null);
//        if (cursor.moveToFirst()) {
//            while (!cursor.isAfterLast()) {
//                id = cursor.getInt(cursor.getColumnIndex(COL_ID));
//                cursor.moveToNext();
//            }
//        }
//        database.close();
//        return id;
//    }
//
//    public void removeItemFromCart (SunglassSale item) {
//        int id = getIdFromCartObject(item);
//        SQLiteDatabase database = getWritableDatabase();
//
//        String selection = COL_SUNGLASS_NAME + " = ?";
//        String[] selectionArgs = new String[]{String.valueOf(id)};
//        database.delete(SUNGLASSSALE_TABLE_NAME, selection, selectionArgs);
//        database.close();
//    }
    //method created to clear the database of cart info
    public void clearCartButtonMethod() {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("DELETE FROM " + SUNGLASSSALE_TABLE_NAME);
        database.close();
    }

    //search method
    public Cursor searchSunglasses(String query){
        SQLiteDatabase database = getReadableDatabase();
        String where = " " + COL_SUNGLASS_NAME + " LIKE ? OR " +
                COL_SUNGLASS_DESCRIPTION + " LIKE ?";
        Cursor cursor = database.query(TINTSINVENTORY_TABLE_NAME,
                INVENTORY_COLUMNS, where,
                new String[]{"%" + query + "%","%" + query + "%"},null,null,null);
        Log.i(TAG, "searchSunglasses: query");
                return cursor;
    }
}

