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

import com.abdo.patrick.abdo.Adapter;
import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Child;
import com.abdo.patrick.abdo.R;
import com.abdo.patrick.abdo.Views.RegisterChild.ChildDataListFagment;
import com.abdo.patrick.abdo.Views.RegisterChild.ChildMedicineData;
import com.abdo.patrick.abdo.Views.RegisterChild.ChildMedicineEditFragment;
import com.abdo.patrick.abdo.Views.RegisterChild.ChildOverviewFragment;
import com.abdo.patrick.abdo.Views.Registraion.FoodListFragment;
import com.abdo.patrick.abdo.Views.Registraion.PainPlacementRating;

import java.util.ArrayList;

/**
 * Created by Patrick on 11-04-2017.
 */

public class RegistrationListController {
    private FoodListFragment _foodListFragment;
    private Context _context;

    public RegistrationListController(FoodListFragment foodListFragment) {
        _foodListFragment = foodListFragment;
        _context = _foodListFragment.getActivity().getApplicationContext();
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
                    String clickedName = rvAdapter.getItemName(position);

                    Fragment fragment = null;

                    if(_foodListFragment != null){
                        fragment = _foodListFragment;
                    }

                    String listType = fragment.getArguments().getString("foodType", "");

                    if(listType.equals("breakfast")){
                        Child currentChild = Application.getInstance().getCurrentChild();
                        boolean exists = currentChild.supplementExists(clickedId);
                        if(exists){
//                            currentChild.removeSupplement(clickedId);
                            child.findViewById(R.id.row_selected_icon).setVisibility(View.INVISIBLE);

                        }else{
//                            currentChild.addSupplement(clickedId);
                            child.findViewById(R.id.row_selected_icon).setVisibility(View.VISIBLE);
                        }
                    }
                    if(listType.equals("lunch")){
                        Child currentChild = Application.getInstance().getCurrentChild();
                        boolean exists = currentChild.supplementExists(clickedId);
                        if(exists){
//                            currentChild.removeSupplement(clickedId);
                            child.findViewById(R.id.row_selected_icon).setVisibility(View.INVISIBLE);

                        }else{
//                            currentChild.addSupplement(clickedId);
                            child.findViewById(R.id.row_selected_icon).setVisibility(View.VISIBLE);
                        }
                    }
                    if(listType.equals("dinner")){
                        Child currentChild = Application.getInstance().getCurrentChild();
                        boolean exists = currentChild.supplementExists(clickedId);
                        if(exists){
//                            currentChild.removeSupplement(clickedId);
                            child.findViewById(R.id.row_selected_icon).setVisibility(View.INVISIBLE);

                        }else{
//                            currentChild.addSupplement(clickedId);
                            child.findViewById(R.id.row_selected_icon).setVisibility(View.VISIBLE);
                        }
                    }
                    if(listType.equals("fruit")){
                        Child currentChild = Application.getInstance().getCurrentChild();
                        boolean exists = currentChild.supplementExists(clickedId);
                        if(exists){
//                            currentChild.removeSupplement(clickedId);
                            child.findViewById(R.id.row_selected_icon).setVisibility(View.INVISIBLE);

                        }else{
//                            currentChild.addSupplement(clickedId);
                            child.findViewById(R.id.row_selected_icon).setVisibility(View.VISIBLE);
                        }
                    }
                    if(listType.equals("candy")){
                        Child currentChild = Application.getInstance().getCurrentChild();
                        boolean exists = currentChild.supplementExists(clickedId);
                        if(exists){
//                            currentChild.removeSupplement(clickedId);
                            child.findViewById(R.id.row_selected_icon).setVisibility(View.INVISIBLE);

                        }else{
//                            currentChild.addSupplement(clickedId);
                            child.findViewById(R.id.row_selected_icon).setVisibility(View.VISIBLE);
                        }
                    }
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }
}
