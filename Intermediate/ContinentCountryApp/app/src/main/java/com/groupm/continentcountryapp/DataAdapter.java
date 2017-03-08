package com.groupm.continentcountryapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.groupm.com.groupm.model.Continent;
import com.groupm.com.groupm.model.Country;

import java.util.ArrayList;

/**
 * Created by Jerick.Duguran on 3/12/2016.
 */
public class DataAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Continent> arrayList = new ArrayList<>();

    public DataAdapter(FragmentManager fm, ArrayList<Continent> iArrayList)
    {
        super(fm);
        arrayList = iArrayList;
    }

    public Fragment getItem(int position)
    {
        MyFragment listings = new MyFragment();
        Bundle args         = new Bundle();
        Continent continent =  arrayList.get(position);
        args.putSerializable("continent",continent);
        listings.setArguments(args);

        return listings;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }
}
