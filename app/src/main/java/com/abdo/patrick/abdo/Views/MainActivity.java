package com.abdo.patrick.abdo.Views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.abdo.patrick.abdo.Api.Anonymous.Post;
import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Allergy;
import com.abdo.patrick.abdo.Models.Anonymous;
import com.abdo.patrick.abdo.Models.Supplement;
import com.abdo.patrick.abdo.R;
import com.abdo.patrick.abdo.Views.Startup.NewUserFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.joda.time.Interval;
import org.joda.time.Period;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);// show back button
                    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onBackPressed();
                        }
                    });
                }else{
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);// hide back button
                }
            }
        });


        Fragment fr = new NewUserFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_activity_fragment, fr).commit();


        //Getting shared preference with the name <Abdo>
        SharedPreferences settings = getSharedPreferences("Abdo", MODE_PRIVATE);
        Gson gson = new Gson();
        boolean registered = settings.getBoolean("REG", false);
        Anonymous anonymous;

        //If anonymous not registered in shared preference
        if (!registered){
            //Create anonymous and call anonymous-post web service
            anonymous = new Anonymous(Application.getAndroidId(getApplicationContext()), android.os.Build.MODEL);
            new Post().execute(anonymous);
            Log.i("INFO", "Creating new anonymous");
        }
        else
        {
            String json = settings.getString("Anonymous", "");
            anonymous = gson.fromJson(json, Anonymous.class);
            Log.i("INFO", "Retrieved anonymous from shared preferences");
        }
        Application.getInstance().set_anonymous(anonymous);


        //Get allergies and supplements from pref.
        String allergies_json = settings.getString("Allergies", "");
        String supplements_json = settings.getString("Supplements", "");

        //Get last time data was saved to pref
        long lastLogin = settings.getLong("DataTimestamp", Long.MIN_VALUE);
        boolean update = true;

        if (lastLogin != Long.MIN_VALUE)
        {
            Period interval = new Period(lastLogin, new Date().getTime());
            update = interval.getHours() > 1;
        }

        Log.i("INFO", update ? "Data is out of date, asking server for new." : "Using existing data");

        if (!update && !allergies_json.isEmpty()){
            ArrayList<Allergy> allergies =
                    gson.fromJson(allergies_json, new TypeToken<ArrayList<Allergy>>(){}.getType());
            Application.getInstance().set_allergyList(allergies);
            Log.i("INFO", "Fetched allergies from pref");
            Log.d("DATA", Application.getInstance().get_allergyList().toString());
        }
        else new com.abdo.patrick.abdo.Api.Allergy.Get().execute();


        if (!update && !supplements_json.isEmpty()){
            ArrayList<Supplement> supplements =
                    gson.fromJson(supplements_json, new TypeToken<ArrayList<Supplement>>(){}.getType());
            Application.getInstance().set_supplementList(supplements);
            Log.i("INFO", "Fetched supplements from pref");
            Log.d("DATA", Application.getInstance().get_supplementList().toString());
        }
        else new com.abdo.patrick.abdo.Api.Supplement.Get().execute();

    }

}
