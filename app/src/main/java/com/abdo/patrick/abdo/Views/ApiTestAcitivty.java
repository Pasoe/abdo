package com.abdo.patrick.abdo.Views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.abdo.patrick.abdo.Api.Anonymous.*;
import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Anonymous;
import com.abdo.patrick.abdo.R;

public class ApiTestAcitivty extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_test_acitivty);


        textView = (TextView) findViewById(R.id.textViewApiTest);

        button = (Button) findViewById(R.id.buttonApiTest);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == button.getId()){

            //Post a new anonymous
//            Anonymous anonymous = new Anonymous();
//            anonymous.setDeviceId("DeviceIdFromAndroid");
//            anonymous.setDeviceName("Samsung Galaxy S7");
//            new Post().execute(anonymous);

            // Get anonymous with the given DeviceId
//            new GetById(this).execute("QE58711871dds81dg838344fga");

        }
    }

    public TextView getTextView() {
        return textView;
    }
}
