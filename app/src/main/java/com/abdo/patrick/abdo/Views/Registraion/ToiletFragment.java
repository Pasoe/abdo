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
import android.widget.TextView;

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

        TextView toolbarTitle = (TextView) getActivity().findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Afføring");

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

        Integer fecesId = Application.getInstance().getCurrentRegistration().getFecesId();
        if(fecesId != null){
            setInitialFecesColor(fecesId);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        int type = 0;
        resetBackgroundColor();

        if(v == turd_type_1){
            type = 2;
            turd_type_1.setBackgroundColor(getResources().getColor(R.color.colorPurple));
        }
        if(v == turd_type_2){
            type = 3;
            turd_type_2.setBackgroundColor(getResources().getColor(R.color.colorPurple));
        }
        if(v == turd_type_3){
            type = 4;
            turd_type_3.setBackgroundColor(getResources().getColor(R.color.colorPurple));
        }
        if(v == turd_type_4){
            type = 5;
            turd_type_4.setBackgroundColor(getResources().getColor(R.color.colorPurple));
        }
        if(v == turd_type_5){
            type = 6;
            turd_type_5.setBackgroundColor(getResources().getColor(R.color.colorPurple));
        }
        if(v == turd_type_6){
            type = 7;
            turd_type_6.setBackgroundColor(getResources().getColor(R.color.colorPurple));
        }
        if(v == turd_type_7){
            type = 8;
            turd_type_7.setBackgroundColor(getResources().getColor(R.color.colorPurple));
        }
        if(v == turd_type_8){
            type = 1;
            turd_type_8.setBackgroundColor(getResources().getColor(R.color.colorPurple));
        }

        if (type != 0 ) Application.getInstance().getCurrentRegistration().addFeces(type);

        FragmentManager fragmentManager2 = getFragmentManager();
        fragmentManager2.popBackStack();
    }

    private void resetBackgroundColor(){
        turd_type_1.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        turd_type_2.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        turd_type_3.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        turd_type_4.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        turd_type_5.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        turd_type_6.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        turd_type_7.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        turd_type_8.setBackgroundColor(getResources().getColor(R.color.colorGrey));
    }

    private void setInitialFecesColor(int fecesId){
        switch(fecesId)   {
            case 1:
                turd_type_8.setBackgroundColor(getResources().getColor(R.color.colorPurple));
                break;
            case 2:
                turd_type_1.setBackgroundColor(getResources().getColor(R.color.colorPurple));
                break;
            case 3:
                turd_type_2.setBackgroundColor(getResources().getColor(R.color.colorPurple));
                break;
            case 4:
                turd_type_3.setBackgroundColor(getResources().getColor(R.color.colorPurple));
                break;
            case 5:
                turd_type_4.setBackgroundColor(getResources().getColor(R.color.colorPurple));
                break;
            case 6:
                turd_type_5.setBackgroundColor(getResources().getColor(R.color.colorPurple));
                break;
            case 7:
                turd_type_6.setBackgroundColor(getResources().getColor(R.color.colorPurple));
                break;
            case 8:
                turd_type_7.setBackgroundColor(getResources().getColor(R.color.colorPurple));
                break;
        }
    }
}
