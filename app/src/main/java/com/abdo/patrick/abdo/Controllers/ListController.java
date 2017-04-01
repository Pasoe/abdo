package com.abdo.patrick.abdo.Controllers;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.abdo.patrick.abdo.Adapter;
import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Allergy;
import com.abdo.patrick.abdo.Models.Child;
import com.abdo.patrick.abdo.Models.Supplement;
import com.abdo.patrick.abdo.R;
import com.abdo.patrick.abdo.Views.RegisterChild.ChildDataListFagment;
import com.abdo.patrick.abdo.Views.RegisterChild.ChildMedicineData;
import com.abdo.patrick.abdo.Views.RegisterChild.ChildOverviewFragment;

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
        _context = _childOverviewFragment.getActivity().getApplicationContext();
    }

    public void InitViews(RecyclerView recyclerView, ArrayList data){

        final RecyclerView.Adapter adapter = new Adapter(data);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(null);

        if(_childDataListFagment != null){
            layoutManager = new LinearLayoutManager(_context);
        }
        if(_childOverviewFragment != null){
            layoutManager = new LinearLayoutManager(_context);
        }
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

                    Fragment fragment = _childOverviewFragment != null ? _childOverviewFragment : _childDataListFagment;
                    String listType = fragment.getArguments().getString("listType");

                    String note = "";

                    if(listType.equals("supplements")){
                        Child newChild = Application.getInstance().getNewChild();
                        boolean exists = newChild.supplementExists(clickedId);
                        if(exists){
                            newChild.removeSupplement(clickedId);
                            child.findViewById(R.id.row_selected_icon).setVisibility(View.INVISIBLE);
                            note = "Kosttilskud: "+clickedName + " fjernet";
                        }else{
                            newChild.addSupplement(clickedId);
                            child.findViewById(R.id.row_selected_icon).setVisibility(View.VISIBLE);
                            note = "Kosttilskud: "+clickedName + " tilføjet";
                        }
                        Application.getInstance().setNewChild(newChild);
                    }
                    else if(listType.equals("medicine")){

                    }
                    else if(listType.equals("allergies")){
                        Child newChild = Application.getInstance().getNewChild();
                        if(newChild.allergyExists(clickedId)){
                            newChild.removeAllergy(clickedId);
                            child.findViewById(R.id.row_selected_icon).setVisibility(View.INVISIBLE);
                            note = "Allergi: "+clickedName + " fjernet";
                        }else{
                            newChild.addAllergy(clickedId);
                            child.findViewById(R.id.row_selected_icon).setVisibility(View.VISIBLE);
                            note = "Allergi: "+clickedName + " tilføjet";
                        }
                        Application.getInstance().setNewChild(newChild);
                    }
                    Toast.makeText(_context, note, Toast.LENGTH_SHORT).show();
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
