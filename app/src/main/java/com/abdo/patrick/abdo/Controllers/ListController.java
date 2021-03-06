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
import com.abdo.patrick.abdo.Views.Registraion.Rating;

import java.util.ArrayList;

/**
 * Created by Khaled on 26-03-2017.
 */

public class ListController {

    private ChildDataListFagment _childDataListFagment;
    private ChildOverviewFragment _childOverviewFragment;
    private ChildMedicineData _childMedicineFragment;
    private Context _context;

    public ListController(ChildDataListFagment childDataListFagment) {
        _childDataListFagment = childDataListFagment;
        _context = _childDataListFagment.getActivity().getApplicationContext();
    }

    public ListController(ChildOverviewFragment childOverviewFragment) {
        _childOverviewFragment = childOverviewFragment;
        _context = _childOverviewFragment.getActivity().getApplicationContext();
    }

    public ListController(ChildMedicineData childMedicineFragment) {
        _childMedicineFragment = childMedicineFragment;
        _context = _childMedicineFragment.getActivity().getApplicationContext();
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
            private Child newChild;

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                Adapter rvAdapter = (Adapter)rv.getAdapter();
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);
                    int clickedId = rvAdapter.getId(position);
                    String clickedName = rvAdapter.getItemName(position);

                    Fragment fragment = null;

                    if(_childOverviewFragment != null){
                        fragment = _childOverviewFragment;
                    }
                    if(_childDataListFagment != null){
                        fragment = _childDataListFagment;
                    }
                    if(_childMedicineFragment != null){
                        fragment = _childMedicineFragment;
                    }

                    String listType = fragment.getArguments().getString("listType", "");
                    Boolean editMode = fragment.getArguments().getBoolean("edit", false);

                    if(listType.equals("supplements")){
                        if(editMode){
                            newChild = Application.getInstance().getCurrentChild();
                        }else{
                            newChild = Application.getInstance().getNewChild();
                        }

                        boolean exists = newChild.supplementExists(clickedId);
                        if(exists){
                            newChild.removeSupplement(clickedId);
                            child.findViewById(R.id.row_selected_icon).setVisibility(View.INVISIBLE);

                        }else{
                            newChild.addSupplement(clickedId);
                            child.findViewById(R.id.row_selected_icon).setVisibility(View.VISIBLE);
                        }
                        if(editMode) {
                            Application.getInstance().updateCurrentChildData(newChild);
                        }else{
                            Application.getInstance().setNewChild(newChild);
                        }
                    }
                    else if(listType.equals("medicine")){
                        Fragment fragment2 = new ChildMedicineEditFragment();

                        Bundle i = new Bundle();
                        if(editMode){
                            i.putString("type", Application.getInstance().getCurrentChild().getMedicineList().get(position).getType());
                            i.putString("dosage", Application.getInstance().getCurrentChild().getMedicineList().get(position).getDosage());
                        }else{
                            i.putString("type", Application.getInstance().getNewChild().getMedicineList().get(position).getType());
                            i.putString("dosage", Application.getInstance().getNewChild().getMedicineList().get(position).getDosage());
                        }
                        i.putBoolean("edit", editMode);
                        fragment2.setArguments(i);

                        FragmentManager fragmentManager2 = _childMedicineFragment.getFragmentManager();
                        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                        fragmentTransaction2.addToBackStack(null);
                        if(editMode){
                            fragmentTransaction2.replace(R.id.main_activity_reg_fragment, fragment2);
                        }else{
                            fragmentTransaction2.replace(R.id.main_activity_fragment, fragment2);
                        }
                        fragmentTransaction2.commit();
                    }
                    else if(listType.equals("allergies")){

                        if(editMode){
                            newChild = Application.getInstance().getCurrentChild();
                        }else{
                            newChild = Application.getInstance().getNewChild();
                        }

                        if(newChild.allergyExists(clickedId)){
                            newChild.removeAllergy(clickedId);
                            child.findViewById(R.id.row_selected_icon).setVisibility(View.INVISIBLE);
                        }else{
                            newChild.addAllergy(clickedId);
                            child.findViewById(R.id.row_selected_icon).setVisibility(View.VISIBLE);
                        }
                        if(editMode) {
                            Application.getInstance().updateCurrentChildData(newChild);
                        }else{
                            Application.getInstance().setNewChild(newChild);
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
