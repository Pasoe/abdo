package com.abdo.patrick.abdo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import layout.NewUserFragment;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fr = new NewUserFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_activity_fragment, fr).commit();
    }
}
