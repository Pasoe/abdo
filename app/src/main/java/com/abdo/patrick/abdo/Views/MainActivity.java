package com.abdo.patrick.abdo.Views;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.abdo.patrick.abdo.R;
import com.abdo.patrick.abdo.Views.Startup.NewUserFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fr = new NewUserFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_activity_fragment, fr).commit();
    }
}
