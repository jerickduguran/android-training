package com.groupm.contactsapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.zip.Inflater;

public class MainActivity extends BaseActivity {

    ListView contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contacts          = (ListView) findViewById(R.id.contacts);
        DatabaseHelper dh = new DatabaseHelper(MainActivity.this);
        DatabaseHelper.ContactCursor cc = dh.getAll();
        ContactAdapter contactAdapter   = new ContactAdapter(this,cc,0);
        contacts.setAdapter(contactAdapter);
    }

    public class ContactAdapter extends CursorAdapter
    {
        public ContactAdapter(Context context, Cursor c,int flags)
        {
            super(context, c, flags);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent){
            return LayoutInflater.from(context).inflate(R.layout.activity_main, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor)
        {
            try {
                TextView name       = (TextView) view.findViewById(R.id.name);
                String record_name  = cursor.getString(cursor.getColumnIndex("name"));
                name.setText(record_name);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
