package com.abdo.patrick.abdo.Views.RegisterChild;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abdo.patrick.abdo.Controllers.ListController;
import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Allergy;
import com.abdo.patrick.abdo.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChildMedicineData extends Fragment {

    private ListController model;

    public ChildMedicineData() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        model = new ListController(this);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_child_medicine_data, container, false);


        TextView toolbarTitle = (TextView) getActivity().findViewById(R.id.toolbar_title);


        TextView header = (TextView)view.findViewById(R.id.child_data_list_header);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.child_data_list);

        toolbarTitle.setText("Allergier");
        header.setText("Allergier");

        ArrayList<Allergy> allergies = Application.getInstance().get_allergyList();
        model.InitViews(recyclerView, Application.getInstance().getAllergyListView(allergies, Application.getInstance().getNewChild()));

        return view;
    }

}
