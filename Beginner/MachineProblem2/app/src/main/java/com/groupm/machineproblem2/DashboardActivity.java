package com.groupm.machineproblem2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ArrayList<String> user_data = getIntent().getStringArrayListExtra("user_data");

        TextView info_container = (TextView) findViewById(R.id.userDetails);
        ImageView img_container = (ImageView) findViewById(R.id.genderView);

        if(user_data.get(4).equals("Male"))
        {
            img_container.setImageResource(R.drawable.male);
        }else{
            img_container.setImageResource(R.drawable.female);
        }

        String message = "Hi, ";
        message += user_data.get(0) + " " + user_data.get(1) + "\n";
        message += "We will call you through " + user_data.get(2) + " or email you @ " + user_data.get(3) + " the soonest.";

        info_container.setText(message);
    }
}
