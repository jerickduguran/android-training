package com.groupm.contactsapp;

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
    private static final String DBNAME = "contacts_db.db";
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
        db.execSQL("CREATE TABLE contacts (id integer primary key autoincrement, name varchar(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ContactCursor getAll()
    {
        Cursor wrapper = getReadableDatabase().rawQuery("select id _id,* from contacts ORDER BY name ASC", null);

        return new ContactCursor(wrapper);
    }

    public long insertContact(String name){
        ContentValues cv = new ContentValues();
        cv.put("name", name);

        return getWritableDatabase().insert("contacts", null, cv);
    }

    public void truncateContacts()
    {
        getWritableDatabase().execSQL("DROP TABLE IF EXISTS contacts");
    }

    public class ContactCursor extends CursorWrapper {

        public ContactCursor(Cursor cursor)
        {
            super(cursor);
        }

        public String getName(int position)
        {
            if(isAfterLast() || isBeforeFirst())
                return  null;

            moveToPosition(position);
            String name = getString(getColumnIndex("name"));

            return name;
        }
    }
}
