package com.abdo.patrick.abdo.Views;

import android.support.v4.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.R;
import com.abdo.patrick.abdo.Views.RegisterChild.ChildOverviewFragment;
import com.abdo.patrick.abdo.Views.Registraion.PainPlacement;
import com.abdo.patrick.abdo.Views.Shared.RegistrationOverviewFragment;
import com.abdo.patrick.abdo.Views.Shared.RestoreFromEmailFragment;
import com.abdo.patrick.abdo.Views.Shared.TypeCodeFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mTitle;
    private CharSequence mDrawerTitle;
    private NavigationView navigation;


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

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.string.navigation_drawer_open,  /* "open drawer" description */
                R.string.navigation_drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(false);

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);

        navigation = (NavigationView) findViewById(R.id.nav_view);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                displayView(id);
                mDrawerLayout.closeDrawer(navigation);
                return false;
            }
        });

        updateNavdrawerData();

        Application.getInstance().getDataSync().seedStaticData();
        Application.getInstance().getDataSync().syncAllData();

        Fragment fr = new PainPlacement();
        getSupportFragmentManager().beginTransaction().add(R.id.main_activity_reg_fragment, fr).commit();
    }

    public void updateNavdrawerData() {
        TextView childName = ((TextView) navigation.getHeaderView(0).findViewById(R.id.nav_header_name));
        ImageView childImage = ((ImageView) navigation.getHeaderView(0).findViewById(R.id.img_profile));

        if(Application.getInstance().getCurrentChild() != null){
            if(Application.getInstance().getCurrentChild().getInfo() != null){

                if(TextUtils.isEmpty(Application.getInstance().getCurrentChild().getInfo().getName())){
                    childName.setText("Intet navn");
                }else{
                    childName.setText(Application.getInstance().getCurrentChild().getInfo().getName());
                }

                if(Application.getInstance().getCurrentChild().getInfo().getGender() == 2 || Application.getInstance().getCurrentChild().getInfo().getGender() == 0){
                    childImage.setImageDrawable(getResources().getDrawable(R.drawable.girl_thumbnail));
                }else{
                    childImage.setImageDrawable(getResources().getDrawable(R.drawable.boy_thumbnail));
                }
            }
        }


    }

    public int getToolbarHeight() {
        return toolbar.getHeight();
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }


    private void displayView(int id){

        Fragment fragment = null;
        Bundle i = new Bundle();

        switch (id) {
            case R.id.nav_childinfo:
                fragment = new ChildOverviewFragment();
                i.putBoolean("edit", true);
                fragment.setArguments(i);
                break;
            case R.id.nav_calendar:
                fragment = new RegistrationOverviewFragment();
                i.putBoolean("generate", true);
                fragment.setArguments(i);
                break;
            case R.id.nav_sharecode:
                fragment = new TypeCodeFragment();
                i.putBoolean("generate", true);
                fragment.setArguments(i);
                break;
            case R.id.nav_restore:
                fragment = new RestoreFromEmailFragment();
                i.putBoolean("save", true);
                fragment.setArguments(i);
                break;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.main_activity_reg_fragment, fragment);
        fragmentTransaction.commit();
    }

    private Boolean exit = false;
    @Override
    public void onBackPressed() {

        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntryCount != 0)
        {
            super.onBackPressed();
            return;
        }

        if (exit) {
            moveTaskToBack(true);
        }
        else
        {
            Toast.makeText(this, "Tryk tilbage igen for at lukke appen ned.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 2 * 1000);
        }
    }
}
