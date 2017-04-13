package com.abdo.patrick.abdo.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.abdo.patrick.abdo.Api.Anonymous.Post;
import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Allergy;
import com.abdo.patrick.abdo.Models.Anonymous;
import com.abdo.patrick.abdo.Models.Child;
import com.abdo.patrick.abdo.Models.Supplement;
import com.abdo.patrick.abdo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.joda.time.Period;

import java.util.ArrayList;
import java.util.Date;

import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imageView = (ImageView) findViewById(R.id.splashimage);

        imageView.setBackgroundResource(R.drawable.splashanimation);
        AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
        anim.start();


        new Handler().postDelayed(new Runnable() {
            public void run() {
                if(Application.getInstance().get_anonymous().childrenExists()){
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                } else{
                    Intent i = new Intent(SplashActivity.this, SetupActivity.class);
                    startActivity(i);
                }
                finish();
            }
        }, 3000);


        syncData();
    }

    private void syncData(){
        //Getting shared preference with the name <Abdo>
        SharedPreferences settings = getSharedPreferences("Abdo", MODE_PRIVATE);
        Gson gson = new Gson();

        syncAnonymousData(settings, gson);

        syncChildData(settings, gson);

        syncStaticData(settings, gson);
    }

    public void syncAnonymousData(SharedPreferences settings, Gson gson){
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
    }

    public void syncChildData(SharedPreferences settings, Gson gson){
        //Get child data from pref.
        String children_json = settings.getString("Children", "");
        String currentChild_json = settings.getString("CurrentChild", "");
        if(children_json.isEmpty()){
            Log.i("INFO", "No children found in shared preferences");
        } else{
            ArrayList<Child> children = gson.fromJson(children_json, new TypeToken<ArrayList<Child>>(){}.getType());
            Application.getInstance().set_children(children);
            Log.i("INFO", "Children fetched from shared preferences");

            if(currentChild_json.isEmpty()){
                Application.getInstance().set_currentChild(children.get(0).getGuid());
                Log.i("INFO", "Current child not found in shared preferences. Setting first child to current.");
            }else{
                Application.getInstance().set_currentChild(currentChild_json);
                Log.i("INFO", "Current child fetched from shared preferences");
            }

        }
    }

    public void syncStaticData(SharedPreferences settings, Gson gson){
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
