package com.abdo.patrick.abdo.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.abdo.patrick.abdo.Api.OkHttp;
import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Allergy;
import com.abdo.patrick.abdo.Models.Anonymous;
import com.abdo.patrick.abdo.Models.Child;
import com.abdo.patrick.abdo.Models.Feces;
import com.abdo.patrick.abdo.Models.Food;
import com.abdo.patrick.abdo.Models.FoodCategory;
import com.abdo.patrick.abdo.Models.Supplement;
import com.abdo.patrick.abdo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.joda.time.Period;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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
        }, 2500);

        Application.getInstance().getDataSync().seedStaticData();
        Application.getInstance().getDataSync().syncAllData(getApplicationContext());
    }
}
