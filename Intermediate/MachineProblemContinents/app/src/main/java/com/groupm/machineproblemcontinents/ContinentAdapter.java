package com.groupm.machineproblemcontinents;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Jerick.Duguran on 4/14/2016.
 */

public class ContinentAdapter extends CursorAdapter
{
    public ContinentAdapter(Context context, Cursor c,int flags)
    {
        super(context, c,flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.continent_view, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        try {
            TextView name       = (TextView) view.findViewById(R.id.continent_name);
            String record_name  = cursor.getString(cursor.getColumnIndex("name"));
            name.setText(record_name);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}