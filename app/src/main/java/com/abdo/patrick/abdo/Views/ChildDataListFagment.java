package com.abdo.patrick.abdo.Views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.abdo.patrick.abdo.Adapter;
import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChildDataListFagment extends Fragment {


    public ChildDataListFagment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_child_data_list_fagment, container, false);

        TextView toolbarTitle = (TextView) getActivity().findViewById(R.id.toolbar_title);


        TextView header = (TextView)view.findViewById(R.id.child_data_list_header);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.child_data_list);

        String listType = getArguments().getString("listType");

        if(listType.equals("allergies")){
            toolbarTitle.setText("Allergier");
            header.setText("Allergier");

            //Get allergies from server if it's empty
            if (Application.getInstance().get_allergyList().isEmpty()){
                new com.abdo.patrick.abdo.Api.Allergy.Get().execute();
            }
            initViews(recyclerView, Application.getInstance().get_allergyList());
        }
        if(listType.equals("supplements")){
            toolbarTitle.setText("Kosttilskud");
            header.setText("Kosttilskud");

            //Get supplements from server if it's empty
            if (Application.getInstance().get_supplementsList().isEmpty()){
                new com.abdo.patrick.abdo.Api.Supplement.Get().execute();
            }
            initViews(recyclerView, Application.getInstance().get_supplementsList());
        }

        return view;
    }

    private void initViews(RecyclerView recyclerView, ArrayList data){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        final RecyclerView.Adapter adapter = new Adapter(data);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getActivity().getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

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
                    Toast.makeText(getActivity().getApplicationContext(), "pressed "+rvAdapter.getItemName(position), Toast.LENGTH_SHORT).show();
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
