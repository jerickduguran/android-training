package com.groupm.contactsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by Jerick.Duguran on 4/20/2016.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.addContact:
                Intent add_new = new Intent(this, AddContactActivity.class);
                startActivity(add_new);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
