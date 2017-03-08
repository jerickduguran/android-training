package com.groupm.machineproblemcontinents;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ListView lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lists = (ListView) findViewById(R.id.continents);
        DatabaseHelper dh = new DatabaseHelper(MainActivity.this);
        dh.insertData();

        ContinentCursor cc = dh.getContinents();
        ContinentAdapter aAdapter          = new ContinentAdapter(this,cc,0);
        lists.setAdapter(aAdapter);

        lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent country_list  = new Intent(MainActivity.this, CountryListActivity.class);
                country_list.putExtra("continent", position);
                startActivity(country_list);
            }
        });

    }
}
