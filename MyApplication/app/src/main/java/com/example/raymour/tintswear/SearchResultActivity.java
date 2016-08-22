package com.example.raymour.tintswear;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class SearchResultActivity extends AppCompatActivity {
    private static final String TAG = "SearchResultActivity";
    CursorAdapter cursorAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        listView = (ListView) findViewById(R.id.listview_search_results);
        Log.i(TAG, "onCreate: SearchResultsActivity");
        if(Intent.ACTION_SEARCH.equals(getIntent().getAction())){
            String query = getIntent().getStringExtra(SearchManager.QUERY);
            Cursor cursor = DatabaseHelper.getsInstance(this).searchSunglasses(query);
            cursorAdapter = new CursorAdapter(this, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {
                @Override
                public View newView(Context context, Cursor cursor, ViewGroup parent) {
                    return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);

                }

                @Override
                public void bindView(View view, Context context, Cursor cursor) {
                    TextView textView = (TextView) view.findViewById(android.R.id.text1);
                    int index = cursor.getColumnIndex(DatabaseHelper.COL_SUNGLASS_NAME);
                    textView.setText(cursor.getString(index));

                }
            };
            listView.setAdapter(cursorAdapter);
        }
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_searchresultactivity);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchResultActivity.this, ShoppingCartActivity.class));
            }
        });

    }
}
