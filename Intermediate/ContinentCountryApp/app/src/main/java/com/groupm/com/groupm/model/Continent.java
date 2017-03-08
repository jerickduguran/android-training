package com.groupm.com.groupm.model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Jerick.Duguran on 4/19/2016.
 */
public class Continent implements Serializable {

    private String name;

    private ArrayList<Country> countries;

    public Continent()
    {
        countries = new ArrayList<Country>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCountry(Country country)
    {
        this.countries.add(country);
    }

    public ArrayList getCountries()
    {
        return this.countries;
    }

    public void setCountries(ArrayList<String> countries)
    {
        for(int i=0;i<countries.size();i++)
        {
            Country country = new Country();
            country.setName(countries.get(i));
            this.countries.add(country);
        }
    }
}
