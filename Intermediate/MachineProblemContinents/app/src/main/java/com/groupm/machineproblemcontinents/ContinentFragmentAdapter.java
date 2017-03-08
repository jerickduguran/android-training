package com.groupm.machineproblemcontinents;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Jerick.Duguran on 3/12/2016.
 */
public class ContinentFragmentAdapter extends FragmentStatePagerAdapter {

    private ContinentCursor  continent_cursor = null;

    public ContinentFragmentAdapter(FragmentManager fm, ContinentCursor cursor)
    {
        super(fm);
        continent_cursor = cursor;
    }

    public Fragment getItem(int position)
    {
        FragmentCountryListView country_list_view = new FragmentCountryListView();

        Bundle args = new Bundle();
        args.putString("continent", continent_cursor.getName(position));
        args.putInt("id", continent_cursor.getId(position));

        country_list_view.setArguments(args);

        return country_list_view;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
/*
    public int getItemPosition(Object item) {
        FragmentCountryListView fragment = (FragmentCountryListView)item;
        String title = fragment.get();
        int position = titles.indexOf(title);

        if (position >= 0) {
            return position;
        } else {
            return POSITION_NONE;
        }
    } */

    @Override
    public int getCount() {
        return continent_cursor.getCount();
    }


}
