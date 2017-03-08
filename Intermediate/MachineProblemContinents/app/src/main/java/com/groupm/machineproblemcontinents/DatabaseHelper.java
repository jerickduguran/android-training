package com.groupm.machineproblemcontinents;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jerick.Duguran on 4/12/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DBNAME = "machineproblem__continent.db";
    private static final int VERSION   = 1;

    public DatabaseHelper(Context context)
    {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    public void createTables(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE continent (id integer primary key autoincrement, name varchar(100))");
        db.execSQL("CREATE TABLE country (id integer primary key autoincrement, name varchar(100),continent_id integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertContinent(String name)
    {
        ContentValues cv = new ContentValues();
        cv.put("name",name);

        return getWritableDatabase().insert("continent",null,cv);
    }

    public long insertCountry(String name,Integer continent_id)    {
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("continent_id", continent_id);

        return getWritableDatabase().insert("country", null, cv);
    }

    public void truncateContinent()
    {

        getWritableDatabase().execSQL("DROP TABLE IF EXISTS continent");
    }

    public void truncateCountry()
    {

        getWritableDatabase().execSQL("DROP TABLE  IF EXISTS country");
    }

    public ContinentCursor getContinents()
    {
        Cursor wrapper = getReadableDatabase().rawQuery("select id _id,* from continent  ORDER BY name ASC", null);

        return new ContinentCursor(wrapper);
    }


    public CountryCursor getCountries(Integer continent)
    {
        Cursor wrapper = getReadableDatabase().rawQuery("select id _id,* from country WHERE continent_id= " + continent+ " ORDER BY name ASC", null);

        return new CountryCursor(wrapper);
    }

    public void insertData()
    {
        this.truncateContinent();
        this.truncateCountry();

        this.createTables(getWritableDatabase());

        //INSERT CONTINENT
        this.insertContinent("Asia");
        this.insertContinent("Africa");
        this.insertContinent("Antarctica");
        this.insertContinent("Australia");
        this.insertContinent("Europe");
        this.insertContinent("North America");
        this.insertContinent("South America");

        //ASIA
        this.insertCountry("Philippines", 1);
        this.insertCountry("Hongkong", 1);
        this.insertCountry("Japan", 1);
        this.insertCountry("South Korea", 1);
        this.insertCountry("Taiwan", 1);
        this.insertCountry("Malaysia", 1);
        //Africa
        this.insertCountry("Bulgaria", 2);
        this.insertCountry("Algeria", 2);
        this.insertCountry("Congo", 2);
        this.insertCountry("Mali", 2);
        this.insertCountry("Malawi", 2);
        this.insertCountry("Morocco", 2);
        //Antractica
        this.insertCountry("South Africa", 3);
        this.insertCountry("New Zealand", 3);
        //Australia
        this.insertCountry("Australia", 4);
        this.insertCountry("Papua New Genuia", 4);
        this.insertCountry("Timor", 4);
        //EUROPE
        this.insertCountry("France", 5);
        this.insertCountry("Belgium", 5);
        this.insertCountry("Germany", 5);
        this.insertCountry("Monaco", 5);
        this.insertCountry("Malta", 5);
        this.insertCountry("Denmark", 5);
        this.insertCountry("Finland", 5);
        //North America
        this.insertCountry("Panama", 6);
        this.insertCountry("El Salvador", 6);
        this.insertCountry("Haitti", 6);
        this.insertCountry("Jamaica", 6);
        this.insertCountry("Mexico", 6);
        this.insertCountry("Costa Rica", 6);
        this.insertCountry("Canada", 6);
        //South America
        this.insertCountry("Brazil", 7);
        this.insertCountry("Chili", 7);
        this.insertCountry("Argentina", 7);
        this.insertCountry("Uruguay", 7);
        this.insertCountry("Bolivia", 7);
        this.insertCountry("Brazil", 7);
    }
}
