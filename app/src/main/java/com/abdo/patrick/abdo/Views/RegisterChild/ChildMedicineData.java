package com.abdo.patrick.abdo.Views.RegisterChild;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.abdo.patrick.abdo.Controllers.ListController;
import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Allergy;
import com.abdo.patrick.abdo.Models.ChildMedicine;
import com.abdo.patrick.abdo.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChildMedicineData extends Fragment implements View.OnClickListener {

    private ListController model;

    public ChildMedicineData() {
        // Required empty public constructor
    }


    private FloatingActionButton button;
    private Boolean editMode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        model = new ListController(this);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_child_medicine_data, container, false);

        editMode = getArguments().getBoolean("edit", false);

        TextView toolbarTitle = (TextView) getActivity().findViewById(R.id.toolbar_title);

        button = (FloatingActionButton) view.findViewById(R.id.medicine_add_button);
        button.setOnClickListener(this);

        TextView header = (TextView)view.findViewById(R.id.child_data_list_header);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.child_data_list);

        toolbarTitle.setText("Medicin");
        header.setText("Medicin");

        if(editMode){
            model.InitViews(recyclerView, Application.getInstance().getMedicineListView(Application.getInstance().getCurrentChild().getMedicineList(), Application.getInstance().getCurrentChild()));
        }else{
            model.InitViews(recyclerView, Application.getInstance().getMedicineListView(Application.getInstance().getNewChild().getMedicineList(), Application.getInstance().getNewChild()));
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == button){
            Fragment fragment = new ChildMedicineEditFragment();
            Bundle i = new Bundle();
            i.putBoolean("edit", editMode);
            fragment.setArguments(i);

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_bot, R.anim.exit_to_top, R.anim.enter_from_top, R.anim.exit_to_bot);
            fragmentTransaction.addToBackStack(null);
            if(editMode){
                fragmentTransaction.replace(R.id.main_activity_reg_fragment, fragment);
            }else{
                fragmentTransaction.replace(R.id.main_activity_fragment, fragment);
            }
            fragmentTransaction.commit();
        }
    }
}
