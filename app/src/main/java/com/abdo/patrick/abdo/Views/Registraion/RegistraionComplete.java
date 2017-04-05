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
public class RegistraionComplete extends Fragment implements View.OnClickListener{


    public RegistraionComplete() {
        // Required empty public constructor
    }

    private RelativeLayout answer_more_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registraion_complete, container, false);

        answer_more_button = (RelativeLayout) view.findViewById(R.id.answer_more_button);
        answer_more_button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == answer_more_button){

        }
    }

}
