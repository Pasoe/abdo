package com.abdo.patrick.abdo.Views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.abdo.patrick.abdo.R;
import com.abdo.patrick.abdo.Views.Registraion.RegistraionComplete;
import com.abdo.patrick.abdo.Views.Registraion.RegistrationOverview;
import com.abdo.patrick.abdo.Views.Registraion.PainPlacement;
import com.abdo.patrick.abdo.Views.Startup.NewUserFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
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

        Fragment fr = new RegistraionComplete();
        getSupportFragmentManager().beginTransaction().add(R.id.main_activity_reg_fragment, fr).commit();
    }

    public int getToolbarHeight() {
        return toolbar.getHeight();
    }
}
