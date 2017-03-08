package com.groupm.continentcountryapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.groupm.com.groupm.model.Continent;
import com.groupm.com.groupm.model.Country;

import java.util.ArrayList;

/**
 * Created by Jerick.Duguran on 3/12/2016.
 */
public class MyFragment extends Fragment {

    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view              = inflater.inflate(R.layout.pager, container, false);
        ListView country_lists = (ListView)view.findViewById(R.id.country_list);
        Continent continent    = (Continent) getArguments().getSerializable("continent");

        CountryAdapter countryAdopter      = new CountryAdapter(view.getContext(), continent.getCountries());
        country_lists.setAdapter(countryAdopter);

        return view;
    }


    private class CountryAdapter extends BaseAdapter {

        Context context;

        ArrayList<Country> country_list = new ArrayList<>();

        public CountryAdapter(Context context, ArrayList<Country> country_list)
        {
            this.context = context;
            this.country_list = country_list;
        }

        @Override
        public int getCount() {
            return country_list.size();
        }

        @Override
        public int getViewTypeCount() {
            return super.getViewTypeCount();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;

            if(view == null)
            {
                view = LayoutInflater.from(context).inflate(R.layout.country_view,parent,false);
                TextView textView = (TextView) view.findViewById(R.id.country_name);
                textView.setText(country_list.get(position).getName());
            }

            return view;
        }
    }


}
