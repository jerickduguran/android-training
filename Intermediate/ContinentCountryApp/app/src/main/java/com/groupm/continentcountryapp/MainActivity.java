package com.groupm.continentcountryapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.groupm.com.groupm.model.Continent;
import com.groupm.com.groupm.model.Country;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Continent> continents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewPager       vp = (ViewPager)findViewById(R.id.viewpager);
        FragmentManager fm = getSupportFragmentManager();

        continents         = this.prepareData();

        DataAdapter dataAdapter = new DataAdapter(fm,continents);
        vp.setAdapter(dataAdapter);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setTitle(continents.get(position).getName());
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private ArrayList<Continent> prepareData()
    {
        ArrayList<Continent> continents = new ArrayList<>();

        //Setup Asia
        Continent asia = new Continent();
        asia.setName("Asia");
        ArrayList<String> asian_countries = new ArrayList<String>();
        asian_countries.add("Hongkong");
        asian_countries.add("Philippines");
        asian_countries.add("China");
        asian_countries.add("Malaysia");
        asian_countries.add("Singapore");
        asia.setCountries(asian_countries);
        continents.add(asia);


        //Setup Africa
        Continent africa = new Continent();
        africa.setName("Africa");
        ArrayList<String> africa_countries = new ArrayList<String>();
        africa_countries.add("Algeria");
        africa_countries.add("Angola");
        africa_countries.add("Congo");
        africa_countries.add("Morroco");
        africa_countries.add("Malawi");
        africa_countries.add("Mali");
        africa.setCountries(africa_countries);
        continents.add(africa);


        //Setup Australia
        Continent australia = new Continent();
        australia.setName("Australia");
        ArrayList<String> australia_countries = new ArrayList<String>();
        australia_countries.add("Australia");
        australia_countries.add("New Zealand");
        australia.setCountries(australia_countries);
        continents.add(australia);

        //Setup Europe
        Continent europe = new Continent();
        europe.setName("Europe");
        ArrayList<String> europe_countries = new ArrayList<String>();
        europe_countries.add("Austria");
        europe_countries.add("Cyprus");
        europe_countries.add("Denmark");
        europe_countries.add("Finland");
        europe_countries.add("France");
        europe_countries.add("Germany");
        europe_countries.add("Greece");
        europe.setCountries(europe_countries);
        continents.add(europe);

        //Setup South America
        Continent south_america = new Continent();
        south_america.setName("South America");
        ArrayList<String> south_america_countries = new ArrayList<String>();
        south_america_countries.add("Argentina");
        south_america_countries.add("Ecuador");
        south_america_countries.add("Peru");
        south_america_countries.add("Uruguay");
        south_america_countries.add("Chile");
        south_america_countries.add("Brazil");
        south_america.setCountries(south_america_countries);
        continents.add(south_america);

        //Setup North America
        Continent north_america = new Continent();
        north_america.setName("North America");
        ArrayList<String> north_america_countries = new ArrayList<String>();
        north_america_countries.add("Bahamas");
        north_america_countries.add("Costa Rica");
        north_america_countries.add("Cuba");
        north_america_countries.add("Haiti");
        north_america_countries.add("Honduras");
        north_america_countries.add("El Salvador");
        north_america_countries.add("Panama");
        north_america_countries.add("Puirto Rico");
        north_america.setCountries(north_america_countries);
        continents.add(north_america);

        return continents;
    }
}
