package com.example.raymour.tintswear;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

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
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(linearLayoutManager);

        DatabaseHelper databaseHelper = DatabaseHelper.getsInstance(MainActivity.this);

        Inventory item1 = new Inventory("Black Betty's", "R.id.blackbetty", "29.99", "Bamboo Frames, Stylish, Polarized");
        Inventory item2 = new Inventory("Bushwoods", "R.id.blackblue", "29.99", "Bamboo Frames, Stylish, Polarized");
        Inventory item3 = new Inventory("Black Outs", "R.id.blackgreen", "29.99", "Bamboo Frames, Stylish, Polarized");
        Inventory item4 = new Inventory("Los Leches", "R.id.greyblue", "29.99", "Bamboo Frames, Stylish, Polarized");

        databaseHelper.insertRowTintsInventory(item1);
        databaseHelper.insertRowTintsInventory(item2);
        databaseHelper.insertRowTintsInventory(item3);
        databaseHelper.insertRowTintsInventory(item4);

        ArrayList<CustomProduct> customProducts = new ArrayList<>();
        customProducts.add(new CustomProduct("Black Betty", "29.99" ,"Bamboo Frames"));
        customProducts.add(new CustomProduct("Black Betty", "29.99" ,"Bamboo Frames"));
       customProducts.add(new CustomProduct("Black Betty", "29.99" ,"Bamboo Frames"));



        mRecyclerView.setAdapter(new CPRecyclerViewAdapter(customProducts));

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
