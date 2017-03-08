package com.groupm.contactsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddContactActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }

    public void addNewContact(View v)
    {
        EditText name     = (EditText)findViewById(R.id.name);
        DatabaseHelper dh = new DatabaseHelper(AddContactActivity.this);
        dh.insertContact(name.getText().toString());
        Intent list = new Intent(AddContactActivity.this,MainActivity.class);
        startActivity(list);
    }
}
