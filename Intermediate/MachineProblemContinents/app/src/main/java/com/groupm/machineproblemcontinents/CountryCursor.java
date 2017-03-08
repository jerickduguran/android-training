package com.groupm.machineproblemcontinents;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * Created by Jerick.Duguran on 4/12/2016.
 */
public class CountryCursor extends CursorWrapper {

    public CountryCursor(Cursor cursor)
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
