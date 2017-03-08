package com.groupm.machineproblemcontinents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by Jerick.Duguran on 3/12/2016.
 */
public class FragmentCountryListView extends Fragment {

    ListView lists;
    String title;
    View view;

    String continent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean getUserVisibleHint() {
        return super.getUserVisibleHint();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view                = inflater.inflate(R.layout.country_pager, container, false);
        lists               = (ListView) view.findViewById(R.id.continent_country_list);
        title               = getArguments().getString("continent");
        Integer id          = getArguments().getInt("id");

        DatabaseHelper dh           = new DatabaseHelper(view.getContext());
        CountryCursor cc            = dh.getCountries(id);
        CountryAdapter aAdapter     = new CountryAdapter(view.getContext(),cc,0);
        lists.setAdapter(aAdapter);

        //getActivity().setTitle(title);

        return view;
    }
}
