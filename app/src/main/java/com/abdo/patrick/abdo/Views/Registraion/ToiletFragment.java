package com.abdo.patrick.abdo.Views.Registraion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToiletFragment extends Fragment implements View.OnClickListener {


    public ToiletFragment() {
        // Required empty public constructor
    }

    private LinearLayout turd_type_1;
    private LinearLayout turd_type_2;
    private LinearLayout turd_type_3;
    private LinearLayout turd_type_4;
    private LinearLayout turd_type_5;
    private LinearLayout turd_type_6;
    private LinearLayout turd_type_7;
    private LinearLayout turd_type_8;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_toilet, container, false);

        turd_type_1 = (LinearLayout) view.findViewById(R.id.turd_type_1_button);
        turd_type_2 = (LinearLayout) view.findViewById(R.id.turd_type_2_button);
        turd_type_3 = (LinearLayout) view.findViewById(R.id.turd_type_3_button);
        turd_type_4 = (LinearLayout) view.findViewById(R.id.turd_type_4_button);
        turd_type_5 = (LinearLayout) view.findViewById(R.id.turd_type_5_button);
        turd_type_6 = (LinearLayout) view.findViewById(R.id.turd_type_6_button);
        turd_type_7 = (LinearLayout) view.findViewById(R.id.turd_type_7_button);
        turd_type_8 = (LinearLayout) view.findViewById(R.id.turd_type_8_button);

        turd_type_1.setOnClickListener(this);
        turd_type_2.setOnClickListener(this);
        turd_type_3.setOnClickListener(this);
        turd_type_4.setOnClickListener(this);
        turd_type_5.setOnClickListener(this);
        turd_type_6.setOnClickListener(this);
        turd_type_7.setOnClickListener(this);
        turd_type_8.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int type = 0;

        if(v == turd_type_1){
            type = 2;
        }
        if(v == turd_type_2){
            type = 3;
        }
        if(v == turd_type_3){
            type = 4;
        }
        if(v == turd_type_4){
            type = 5;
        }
        if(v == turd_type_5){
            type = 6;
        }
        if(v == turd_type_6){
            type = 7;
        }
        if(v == turd_type_7){
            type = 8;
        }
        if(v == turd_type_8){
            type = 1;
        }

        if (type != 0 ) Application.getInstance().getCurrentRegistration().addFeces(type);

        FragmentManager fragmentManager2 = getFragmentManager();
        fragmentManager2.popBackStack();
    }
}
