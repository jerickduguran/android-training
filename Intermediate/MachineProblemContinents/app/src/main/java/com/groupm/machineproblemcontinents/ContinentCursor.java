package com.groupm.machineproblemcontinents;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * Created by Jerick.Duguran on 4/12/2016.
 */
public class ContinentCursor extends CursorWrapper {

    public ContinentCursor(Cursor cursor)
    {
        super(cursor);
    }

    public String getName(int position)
    {
        moveToPosition(position);
        String name = getString(getColumnIndex("name"));

        return name;
    }

    public Integer getId(int position)
    {
        moveToPosition(position);
        Integer id = getInt(getColumnIndex("id"));

        return id;
    }

}
