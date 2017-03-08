package com.groupm.machineproblerm1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String default_username = "admin";
    String default_password = "movent.123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = (Button)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText username;
                EditText password;

                username =  (EditText) findViewById(R.id.username);
                password =  (EditText) findViewById(R.id.password);

                AlertDialog.Builder dBuilder = new AlertDialog.Builder(v.getContext())
                        .setTitle("LOGIN");


                if(username.getText().toString().equals(default_username.toString()) == false ||
                        password.getText().toString().equals(default_password.toString()) == false)
                {
                    dBuilder.setMessage("Invalid Login!");

                }else{

                    dBuilder.setMessage("Login Success!");
                }

                AlertDialog dialog = dBuilder.create();
                dialog.show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
