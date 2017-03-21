package com.abdo.patrick.abdo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateChild extends Fragment {


    public CreateChild() {
        // Required empty public constructor
    }

    private ArrayList stamdataList;
    private ArrayList medicineList;
    private ArrayList allergiesList;
    private ArrayList supplementsList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.create_child_list, container, false);

        generateDummyDate();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.stamdata_list);
        initViews(recyclerView, stamdataList);

        RecyclerView recyclerView1 = (RecyclerView) view.findViewById(R.id.medicine_list);
        initViews(recyclerView1, medicineList);

        RecyclerView recyclerView2 = (RecyclerView) view.findViewById(R.id.allergies_list);
        initViews(recyclerView2, allergiesList);

        RecyclerView recyclerView3 = (RecyclerView) view.findViewById(R.id.supplements_list);
        initViews(recyclerView3, supplementsList);

        return view;
    }

    private void generateDummyDate(){
        stamdataList = new ArrayList<>();
        stamdataList.add("Hans");
        stamdataList.add("Dreng");
        stamdataList.add("12-01-2007");

        medicineList = new ArrayList<>();
        medicineList.add("Rohypnol");
        medicineList.add("Kodimagnyl");
        medicineList.add("Random astma ting");

        allergiesList = new ArrayList<>();
        allergiesList.add("Ananas");
        allergiesList.add("Heste");

        supplementsList = new ArrayList<>();
        supplementsList.add("Jern");
        supplementsList.add("A-vitamin");
        supplementsList.add("B-vitamin");
        supplementsList.add("C-vitamin");
        supplementsList.add("D-vitamin");
        supplementsList.add("E-vitamin");
        supplementsList.add("F-vitamin");
        supplementsList.add("G-vitamin");

        return;
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
