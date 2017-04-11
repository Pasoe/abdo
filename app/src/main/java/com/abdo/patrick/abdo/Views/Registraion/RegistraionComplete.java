package com.abdo.patrick.abdo.Views.Registraion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.abdo.patrick.abdo.R;
import com.abdo.patrick.abdo.Views.Startup.NewUserFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistraionComplete extends Fragment implements View.OnClickListener{


    public RegistraionComplete() {
        // Required empty public constructor
    }

    private LinearLayout answer_toilet_tile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registraion_complete, container, false);

        answer_toilet_tile = (LinearLayout) view.findViewById(R.id.answer_toilet_tile);
        answer_toilet_tile.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == answer_toilet_tile){
            Fragment fragment = new ToiletFragment();
            FragmentManager fragmentManager2 = getFragmentManager();
            FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
            fragmentTransaction2.addToBackStack(null);
            fragmentTransaction2.replace(R.id.main_activity_reg_fragment, fragment);
            fragmentTransaction2.commit();
        }
    }

}
