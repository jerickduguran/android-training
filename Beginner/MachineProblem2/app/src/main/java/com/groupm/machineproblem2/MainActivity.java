package com.groupm.machineproblem2;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void register(View view)
    {
        Boolean is_valid    = true;
        String  message     = "";

        EditText first_name = (EditText) findViewById(R.id.firstname);
        EditText last_name  = (EditText) findViewById(R.id.lastName);
        EditText email      = (EditText) findViewById(R.id.email);
        EditText mobile     = (EditText) findViewById(R.id.mobile);

        String gender    = "";

        RadioGroup genderGrp = (RadioGroup)findViewById(R.id.grpGender);


        if(first_name.getText().toString().isEmpty())
        {
            is_valid = false;
            message  += "First Name required.\n";
        }
        if(last_name.getText().toString().isEmpty())
        {
            is_valid = false;
            message  += "Last Name required.\n";
        }

        if(isValidEmail(email.getText().toString()) == false)
        {
            is_valid = false;
            message  += "Invalid Email.\n";
        }

        if(mobile.getText().toString().isEmpty())
        {
            is_valid = false;
            message  += "Mobile number required.\n";
        }


        if(genderGrp.getCheckedRadioButtonId() != -1)
        {
            gender        = ((RadioButton)findViewById(genderGrp.getCheckedRadioButtonId())).getText().toString();
        }else{

            is_valid = false;
            message  += "Please select gender.\n";
        }


        if(false == is_valid)
        {
            AlertDialog.Builder dBuilder = new AlertDialog.Builder(view.getContext())
                    .setTitle("OPPS!");
            dBuilder.setMessage(message);
            AlertDialog dialog = dBuilder.create();
            dialog.show();
        }else{

            ArrayList<String> user_data = new ArrayList<>();
            user_data.add(0,first_name.getText().toString());
            user_data.add(1,last_name.getText().toString());
            user_data.add(2,mobile.getText().toString());
            user_data.add(3,email.getText().toString());
            user_data.add(4,gender);

            Intent dashboard  = new Intent(MainActivity.this, DashboardActivity.class);
            dashboard.putStringArrayListExtra("user_data", user_data);

            

            startActivity(dashboard);
        }



    }

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
