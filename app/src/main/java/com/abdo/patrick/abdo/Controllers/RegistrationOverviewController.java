package com.abdo.patrick.abdo.Controllers;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.abdo.patrick.abdo.Adapter;
import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Food;
import com.abdo.patrick.abdo.Models.Registration;
import com.abdo.patrick.abdo.R;
import com.abdo.patrick.abdo.RegistrationAdapter;
import com.abdo.patrick.abdo.Views.RegisterChild.ChildOverviewFragment;
import com.abdo.patrick.abdo.Views.Registraion.FoodListFragment;
import com.abdo.patrick.abdo.Views.Registraion.RegistraionComplete;
import com.abdo.patrick.abdo.Views.Shared.RegistrationOverviewFragment;

import java.util.ArrayList;

/**
 * Created by Patrick on 14-05-2017.
 */

public class RegistrationOverviewController {
    private RegistrationOverviewFragment _registrationOverview;
    private Context _context;

    public RegistrationOverviewController(RegistrationOverviewFragment registrationOverview) {
        _registrationOverview = registrationOverview;
        _context = _registrationOverview.getActivity().getApplicationContext();
    }

    public void InitViews(RecyclerView recyclerView, ArrayList data){

        final RecyclerView.Adapter adapter = new RegistrationAdapter(data);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(_context);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(_context, new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                RegistrationAdapter rvAdapter = (RegistrationAdapter)rv.getAdapter();
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);
                    String clickedId = rvAdapter.getItemGuid(position);

                    Application.getInstance().set_currentRegistration(Application.getInstance().getCurrentChild().getRegistration(clickedId));

                    Fragment fragment = new RegistraionComplete();
                    Bundle i = new Bundle();
                    i.putBoolean("edit", true);
                    fragment.setArguments(i);
                    FragmentManager fragmentManager = _registrationOverview.getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.addToBackStack("editreg");
                    fragmentTransaction.replace(R.id.main_activity_reg_fragment, fragment);

                    fragmentTransaction.commit();
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e){}

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept){}
        });
    }
}
