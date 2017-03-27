package com.abdo.patrick.abdo.Views.RegisterChild;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abdo.patrick.abdo.Controllers.ListController;
import com.abdo.patrick.abdo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChildOverviewFragment extends Fragment implements View.OnClickListener{


    public ChildOverviewFragment() {
        // Required empty public constructor
    }

    private ListController model;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        model = new ListController(this);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.create_child_list, container, false);

        TextView toolbarTitle = (TextView) getActivity().findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Registrer barn");

        RelativeLayout child_data_header = (RelativeLayout)  view.findViewById(R.id.create_child_data_category);
        child_data_header.setOnClickListener(this);

        RelativeLayout child_medicine_header = (RelativeLayout)  view.findViewById(R.id.create_child_medicine_category);
        child_medicine_header.setOnClickListener(this);

        RelativeLayout child_allergy_header = (RelativeLayout)  view.findViewById(R.id.create_child_allergy_category);
        child_allergy_header.setOnClickListener(this);

        RelativeLayout child_supplement_header = (RelativeLayout)  view.findViewById(R.id.create_child_supplement_category);
        child_supplement_header.setOnClickListener(this);



        return view;
    }


    @Override
    public void onClick(View v) {
        Fragment fragment = null;

        switch(v.getId()){
            case R.id.create_child_data_category:{
                fragment = new ChildStamData();
                break;
            }
            case R.id.create_child_medicine_category:{
                return;
            }
            case R.id.create_child_allergy_category:{
                fragment = new ChildDataListFagment();
                Bundle i = new Bundle();
                i.putString("listType", "allergies");
                fragment.setArguments(i);
                break;
            }
            case R.id.create_child_supplement_category:{
                fragment = new ChildDataListFagment();
                Bundle i = new Bundle();
                i.putString("listType", "supplements");
                fragment.setArguments(i);
                break;
            }
        }

        FragmentManager fragmentManager2 = getFragmentManager();
        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
        fragmentTransaction2.addToBackStack(null);
        fragmentTransaction2.replace(R.id.main_activity_fragment, fragment);
        fragmentTransaction2.commit();
    }
}
