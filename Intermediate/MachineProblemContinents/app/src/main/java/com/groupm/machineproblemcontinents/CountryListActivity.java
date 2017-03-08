package com.groupm.machineproblemcontinents;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CountryListActivity extends AppCompatActivity {
    ContinentCursor   cc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        Integer continent_id = ((Integer) getIntent().getExtras().get("continent"));

        DatabaseHelper    dh = new DatabaseHelper(CountryListActivity.this);
        cc = dh.getContinents();
        FragmentManager   fm = getSupportFragmentManager();

        ContinentFragmentAdapter aAdapter    = new ContinentFragmentAdapter(fm,cc);
        ViewPager country_pager = (ViewPager)findViewById(R.id.country_pager);
        country_pager.setAdapter(aAdapter);
        country_pager.setCurrentItem(continent_id);

        country_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setTitle(cc.getName(position));
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }
}
