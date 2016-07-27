package com.example.raymour.tintswear;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);


        DatabaseHelper databaseHelper = DatabaseHelper.getsInstance(MainActivity.this);

        Inventory item1 = new Inventory("Black Betty's", "blackbetty.png", "29.99", "Bamboo Frames, Stylish, Polarized");
        Inventory item2 = new Inventory("Black Betty Blues", "blackblue.png", "29.99", "Bamboo Frames, Stylish, Polarized, Blue Reflective Lenses");
        Inventory item3 = new Inventory("Black Betty Fire", "blackorange.png", "29.99", "Bamboo Frames, Stylish, Polarized, Orange Reflective Lenses");
        Inventory item4 = new Inventory("Bushwood Blacks", "bushblack.png", "29.99", "Bamboo Frames, Stylish, Polarized");
        Inventory item5 = new Inventory("Bushwood Blues", "bushblue.png", "29.99", "Bamboo Frames, Stylish, Polarized, Blue Reflective Lenses");
        Inventory item6 = new Inventory("Bushwood Browns", "bushbrowns.png", "29.99", "Bamboo Frames, Stylish, Polarized");
        Inventory item7 = new Inventory("Bushwood Greens", "bushgreen.png", "29.99", "Bamboo Frames, Stylish, Polarized, Green Reflective Lenses");
        Inventory item8 = new Inventory("Bushwood SunFire", "bushyellow.png", "29.99", "Bamboo Frames, Stylish, Polarized, Yellow Reflective Lenses");
        Inventory item9 = new Inventory("Magnum Blues", "greyblue.png", "29.99", "Bamboo Frames, Stylish, Polarized, Blue Reflective Lenses");
        Inventory item10 = new Inventory("Los Leches", "whiteblack.png", "29.99", "Bamboo Frames, Stylish, Polarized");
        Inventory item11 = new Inventory("Los Leches Blues", "whiteblue.png", "29.99", "Bamboo Frames, Stylish, Polarized, Blue Reflective Lenses");
        Inventory item12 = new Inventory("Los Leches Greens", "whitegreen.png", "29.99", "Bamboo Frames, Stylish, Polarized, Green Reflective Lenses");
        Inventory item13 = new Inventory("Los Leches Mirrors", "whitemirror.png", "29.99", "Bamboo Frames, Stylish, Polarized");
        Inventory item14 = new Inventory("Los Leches Fire", "whiteorange.png", "29.99", "Bamboo Frames, Stylish, Polarized");


        databaseHelper.insertRowTintsInventory(item1);
        databaseHelper.insertRowTintsInventory(item2);
        databaseHelper.insertRowTintsInventory(item3);
        databaseHelper.insertRowTintsInventory(item4);
        databaseHelper.insertRowTintsInventory(item5);
        databaseHelper.insertRowTintsInventory(item6);
        databaseHelper.insertRowTintsInventory(item7);
        databaseHelper.insertRowTintsInventory(item8);
        databaseHelper.insertRowTintsInventory(item9);
        databaseHelper.insertRowTintsInventory(item10);
        databaseHelper.insertRowTintsInventory(item11);
        databaseHelper.insertRowTintsInventory(item12);
        databaseHelper.insertRowTintsInventory(item13);
        databaseHelper.insertRowTintsInventory(item14);

        Cursor cursor = DatabaseHelper.getsInstance(MainActivity.this).getSunglassList();

        CursorAdapter cursorAdapter = new CursorAdapter(MainActivity.this, cursor,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.customproduct,parent,false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView textViewName = (TextView) view.findViewById(R.id.productName);
                ImageView imageView = (ImageView) view.findViewById(R.id.productImage);
                TextView textViewPrice = (TextView) view.findViewById(R.id.productPrice);
                TextView textViewDescription = (TextView) view.findViewById(R.id.productDesc);

                int colSunName = cursor.getColumnIndex(DatabaseHelper.COL_SUNGLASS_NAME);
                String sunglassName = cursor.getString(colSunName);
                textViewName.setText(sunglassName);

                int colSunPrice = cursor.getColumnIndex(DatabaseHelper.COL_SUNGLASS_PRICE);
                String sunglassPrice = cursor.getString(colSunPrice);
                textViewPrice.setText(sunglassPrice);

                int colSunDesc = cursor.getColumnIndex(DatabaseHelper.COL_SUNGLASS_DESCRIPTION);
                String sunglassDescription = cursor.getString(colSunDesc);
                textViewDescription.setText(sunglassDescription);

            }
        };

        mRecyclerView.setAdapter(cursorAdapter);

        cursor.close();




//        ArrayList<CustomProduct> customProducts = new ArrayList<>();
//        customProducts.add(new CustomProduct("Black Betty", "29.99" ,"Bamboo Frames"));
//        customProducts.add(new CustomProduct("Black Betty", "29.99" ,"Bamboo Frames"));
//       customProducts.add(new CustomProduct("Black Betty", "29.99" ,"Bamboo Frames"));
//
//
//
//        mRecyclerView.setAdapter(new CPRecyclerViewAdapter(customProducts));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
