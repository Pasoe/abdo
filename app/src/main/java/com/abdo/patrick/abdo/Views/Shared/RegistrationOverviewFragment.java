package com.abdo.patrick.abdo.Views.Shared;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdo.patrick.abdo.Controllers.RegistrationOverviewController;
import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Registration;
import com.abdo.patrick.abdo.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationOverviewFragment extends Fragment {


    public RegistrationOverviewFragment() {
        // Required empty public constructor
    }

    private RegistrationOverviewController model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration_overview, container, false);

        model = new RegistrationOverviewController(this);

        ArrayList<Registration> list = Application.getInstance().getCurrentChild().getRegistrations();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.child_registration_list);
        model.InitViews(recyclerView, Application.getInstance().getRegistrationListView(list));

        return view;
    }

}
