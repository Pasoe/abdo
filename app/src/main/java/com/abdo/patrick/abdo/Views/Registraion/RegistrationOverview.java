package com.abdo.patrick.abdo.Views.Registraion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.abdo.patrick.abdo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationOverview extends Fragment implements View.OnClickListener{


    public RegistrationOverview() {
        // Required empty public constructor
    }

    private RelativeLayout answer_more_button;
    private LinearLayout optional_info_layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration_overview, container, false);

        answer_more_button = (RelativeLayout) view.findViewById(R.id.answer_more_button);
        answer_more_button.setOnClickListener(this);

        optional_info_layout = (LinearLayout) view.findViewById(R.id.optional_info_layout);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == answer_more_button){
            answer_more_button.setVisibility(View.GONE);
            optional_info_layout.setVisibility(View.VISIBLE);
        }
    }
}
