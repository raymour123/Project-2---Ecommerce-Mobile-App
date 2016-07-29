package com.example.raymour.tintswear;

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

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSC);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        ShoppingCartRVAdapter adapter = new ShoppingCartRVAdapter(ShoppingCartActivity.this, sunglassSaleArrayList);
        recyclerView.setAdapter(adapter);


        Button clearShopCartButton = (Button) findViewById(R.id.clearShopCartButton);
        clearShopCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShoppingCartActivity.this, "Your cart has been cleared", Toast.LENGTH_SHORT).show();

                helper.clearCartButtonMethod();
                finish();
            }
        });


    }

}
