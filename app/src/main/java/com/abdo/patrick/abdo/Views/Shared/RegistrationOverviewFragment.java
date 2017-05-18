package com.abdo.patrick.abdo.Views.Shared;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdo.patrick.abdo.Api.OkHttp;
import com.abdo.patrick.abdo.Controllers.RegistrationOverviewController;
import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Registration;
import com.abdo.patrick.abdo.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationOverviewFragment extends Fragment {


    private OkHttp okHttp;

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
        okHttp = new OkHttp();

        ArrayList<Registration> list = Application.getInstance().getCurrentChild().getRegistrations();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.child_registration_list);
        model.InitViews(recyclerView, Application.getInstance().getRegistrationListView(list));

        return view;
    }

    @Override
    public void onDetach() {
        Log.d("onDetach", "Registration overview");

        //TODO: Check if modified

        ArrayList<Registration> cur_registrations = Application.getInstance().getCurrentChild().getRegistrations();

        try {
            Gson gson = new Gson();
            String registrationApiURL = getString(R.string.api_registration);
            String deviceId = Application.getAndroidId(getContext());
            String JSON = gson.toJson(cur_registrations);
            Log.d("onDetach", "Currengt registrations, JSON:\n" +JSON.toString());
            okHttp.put(registrationApiURL+deviceId, JSON);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        super.onDetach();
    }
}
