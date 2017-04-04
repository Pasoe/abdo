package com.abdo.patrick.abdo.Views.RegisterChild;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abdo.patrick.abdo.Controllers.ListController;
import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Child;
import com.abdo.patrick.abdo.Models.ChildAllergy;
import com.abdo.patrick.abdo.Models.ChildMedicine;
import com.abdo.patrick.abdo.Models.ChildSupplement;
import com.abdo.patrick.abdo.R;
import com.abdo.patrick.abdo.Views.Startup.NewUserFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChildOverviewFragment extends Fragment implements View.OnClickListener{


    public ChildOverviewFragment() {
        // Required empty public constructor
    }

    private ListController model;
    private TextView toolbarSave;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        model = new ListController(this);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.create_child_list, container, false);

        TextView toolbarTitle = (TextView) getActivity().findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Registrer barn");
        toolbarSave = (TextView) getActivity().findViewById(R.id.toolbar_save);
        toolbarSave.setVisibility(View.VISIBLE);
        toolbarSave.setOnClickListener(this);

        RelativeLayout child_data_header = (RelativeLayout)  view.findViewById(R.id.create_child_data_category);
        child_data_header.setOnClickListener(this);

        RelativeLayout child_medicine_header = (RelativeLayout)  view.findViewById(R.id.create_child_medicine_category);
        child_medicine_header.setOnClickListener(this);

        RelativeLayout child_allergy_header = (RelativeLayout)  view.findViewById(R.id.create_child_allergy_category);
        child_allergy_header.setOnClickListener(this);

        RelativeLayout child_supplement_header = (RelativeLayout)  view.findViewById(R.id.create_child_supplement_category);
        child_supplement_header.setOnClickListener(this);

        TextView stamdataContent = (TextView) view.findViewById(R.id.child_overview_stamdata_content);
        TextView medicineContent = (TextView) view.findViewById(R.id.child_overview_medicine_content);
        TextView allergyContent = (TextView) view.findViewById(R.id.child_overview_allergy_content);
        TextView supplementContent = (TextView) view.findViewById(R.id.child_overview_supplement_content);

        Child newChild = Application.getInstance().getNewChild();

        if(newChild.getInfo() != null){
            if(newChild.getInfo().getName() != null){
                stamdataContent.append(newChild.getInfo().getName()+"\n");
            }
            if(newChild.getInfo().getGender() != 0){
                stamdataContent.append(newChild.getInfo().getGenderString()+"\n");
            }
            if(newChild.getInfo().getBirthdate() != null){
                stamdataContent.append(newChild.getInfo().getBirthdateToView()+"\n");
            }
        }

        for(ChildMedicine medicine : newChild.getMedicineList()){
            medicineContent.append(medicine.toString()+"\n");
        }

        for(ChildAllergy allergy : newChild.getAllergies()){
            allergyContent.append(allergy.getType()+"\n");
        }

        for(ChildSupplement supplement : newChild.getSupplements()){
            supplementContent.append(supplement.getDescription()+"\n");
        }

        return view;
    }


    @Override
    public void onClick(View v) {
        Fragment fragment = null;

        if(v == toolbarSave){
            new com.abdo.patrick.abdo.Api.Child.Post().execute(Application.getInstance().getNewChild());

            toolbarSave.setVisibility(View.INVISIBLE);

            fragment = new NewUserFragment();
            FragmentManager fragmentManager2 = getFragmentManager();
            FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
            fragmentManager2.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentTransaction2.replace(R.id.main_activity_fragment, fragment);
            fragmentTransaction2.commit();
        }

        switch(v.getId()){
            case R.id.create_child_data_category:{
                fragment = new ChildStamData();
                break;
            }
            case R.id.create_child_medicine_category:{
                fragment = new ChildMedicineData();
                Bundle i = new Bundle();
                i.putString("listType", "medicine");
                fragment.setArguments(i);
                break;
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

        toolbarSave.setVisibility(View.INVISIBLE);
        FragmentManager fragmentManager2 = getFragmentManager();
        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
        fragmentTransaction2.addToBackStack(null);
        fragmentTransaction2.replace(R.id.main_activity_fragment, fragment);
        fragmentTransaction2.commit();
    }
}
