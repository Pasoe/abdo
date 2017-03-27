package com.abdo.patrick.abdo.Controllers;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.abdo.patrick.abdo.Adapter;
import com.abdo.patrick.abdo.Views.RegisterChild.ChildDataListFagment;
import com.abdo.patrick.abdo.Views.RegisterChild.ChildOverviewFragment;

import java.util.ArrayList;

/**
 * Created by Khaled on 26-03-2017.
 */

public class ListController {

    private ChildDataListFagment _childDataListFagment;
    private ChildOverviewFragment _childOverviewFragment;
    private Context _context;

    public ListController(ChildDataListFagment childDataListFagment) {
        _childDataListFagment = childDataListFagment;
        _context = _childDataListFagment.getActivity().getApplicationContext();
    }

    public ListController(ChildOverviewFragment childOverviewFragment) {
        _childOverviewFragment = childOverviewFragment;
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
                    Toast.makeText(_context, "pressed " + rvAdapter.getId(position), Toast.LENGTH_SHORT).show();
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
