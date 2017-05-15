package com.abdo.patrick.abdo.Controllers;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.abdo.patrick.abdo.Adapter;
import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Food;
import com.abdo.patrick.abdo.Models.Registration;
import com.abdo.patrick.abdo.R;
import com.abdo.patrick.abdo.Views.Registraion.FoodListFragment;
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

        final RecyclerView.Adapter adapter = new Adapter(data);
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
                Adapter rvAdapter = (Adapter)rv.getAdapter();
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);
                    int clickedId = rvAdapter.getId(position);

                    Registration reg = Application.getInstance().FindRegistration(clickedId);

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
