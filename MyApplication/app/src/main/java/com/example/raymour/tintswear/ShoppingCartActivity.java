package com.example.raymour.tintswear;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity {

    DatabaseHelper helper = DatabaseHelper.getsInstance(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        ArrayList<SunglassSale> sunglassSaleArrayList = helper.getCartItemsObjects();
        //method set up to redirect user from shopping cart if empty
        if (sunglassSaleArrayList.size() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCartActivity.this);
            builder.setPositiveButton("Take me Back to the Store", null)
                    .setTitle("Shopping Cart is Empty!")
                    .setMessage("Go find something in the store");
            final AlertDialog dialog = builder.create();
            dialog.show();
            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }




        //sets up RV for ShoppingCart Activity
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSC);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        ShoppingCartRVAdapter adapter = new ShoppingCartRVAdapter(ShoppingCartActivity.this, sunglassSaleArrayList);
        recyclerView.setAdapter(adapter);

        //called the clear shopping cart method from DBhelper
        Button clearShopCartButton = (Button) findViewById(R.id.clearShopCartButton);
        clearShopCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShoppingCartActivity.this, "Your cart has been cleared", Toast.LENGTH_SHORT).show();

                helper.clearCartButtonMethod();
                finish();
            }
        });

        //finalize order button
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShoppingCartActivity.this, "Your sunglasses are on the way!", Toast.LENGTH_LONG).show();

                helper.clearCartButtonMethod();
                finish();
            }
        });



    }

}
