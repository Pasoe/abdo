package com.abdo.patrick.abdo.Views.RegisterChild;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abdo.patrick.abdo.Api.OkHttp;
import com.abdo.patrick.abdo.Controllers.ListController;
import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Allergy;
import com.abdo.patrick.abdo.Models.Child;
import com.abdo.patrick.abdo.Models.ChildAllergy;
import com.abdo.patrick.abdo.Models.ChildInfo;
import com.abdo.patrick.abdo.Models.ChildMedicine;
import com.abdo.patrick.abdo.Models.ChildSupplement;
import com.abdo.patrick.abdo.R;
import com.abdo.patrick.abdo.Views.MainActivity;
import com.abdo.patrick.abdo.Views.SplashActivity;
import com.abdo.patrick.abdo.Views.Startup.NewUserFragment;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChildOverviewFragment extends Fragment implements View.OnClickListener{


    public ChildOverviewFragment() {
        // Required empty public constructor
    }

    private ListController model;
    private TextView toolbarSave;
    private Boolean editMode = false;
    private Child newChild;

    private boolean modified = false;
    private Child tmp = new Child();
    private OkHttp okHttp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        model = new ListController(this);
        okHttp = new OkHttp();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.create_child_list, container, false);

        if(getArguments() != null){
            editMode = getArguments().getBoolean("edit", false);
        }

        TextView toolbarTitle = (TextView) getActivity().findViewById(R.id.toolbar_title);
        if(editMode){
            toolbarTitle.setText("Rediger");
            ImageView editIcon = (ImageView) view.findViewById(R.id.stamdata_header_image);
            editIcon.setImageDrawable(getResources().getDrawable(R.drawable.icon_edit));

            TextView editHeaderText = (TextView) view.findViewById(R.id.stamdata_header_text);
            editHeaderText.setText("Rediger information om dit barn her");

        }else{
            toolbarTitle.setText("Registrer barn");
            toolbarSave = (TextView) getActivity().findViewById(R.id.toolbar_save);
            toolbarSave.setVisibility(View.VISIBLE);
            toolbarSave.setOnClickListener(this);
        }

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


        if(editMode){
            newChild = Application.getInstance().getCurrentChild();
        }else{
            newChild = Application.getInstance().getNewChild();
        }

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

        if(editMode) {
            ((MainActivity) getActivity()).updateNavdrawerData();
        }

        Log.i("DEBUG", newChild.toString());
        return view;
    }

    @Override
    public void onDetach(){

        if (editMode)
        {
            modified = !tmp.equals(Application.getInstance().getCurrentChild());
            Log.d("onDetach", "tmp: "+tmp.toString());
            Log.d("onDetach", "cur: "+Application.getInstance().getCurrentChild().toString());
            Log.d("onDetach", "Edit mode: "+editMode);
            Log.d("onDetach", "Modified: "+modified);
        }
        if(editMode && modified){
            try
            {
                Gson gson = new Gson();
                String childApiURL = getString(R.string.api_child);
                String deviceId = Application.getAndroidId(getContext());
                String JSON = gson.toJson(Application.getInstance().getCurrentChild());
                Log.d("DEBUG", JSON);

                okHttp.put(childApiURL + deviceId, JSON);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        super.onDetach();
    }

    @Override
    public void onAttach(Context context) {

        Log.d("onAttach", "Checking arguments");
        if(getArguments() != null){
            editMode = getArguments().getBoolean("edit", false);
            Log.d("onAttach", "Arguments not null");
        }
        else Log.d("onAttach", "Arguments are null");

        Log.d("onAttach", "Checking edit mode");
        if(editMode)
        {
            Log.d("onAttach", "Edit mode true");
            //Take snapshot of current child
            Child cur = Application.getInstance().getCurrentChild();
            ChildInfo curInfo = cur.getInfo();

            Log.d("onAttach", "Creating temporary child");

            String guid = cur.getGuid();
            String name = curInfo.getName();
            String birthdate = curInfo.getBirthdate();
            int gender = curInfo.getGender();

            ArrayList<ChildAllergy> allergies = new ArrayList<>();
            for (ChildAllergy t: cur.getAllergies())
            {
                allergies.add(t);
            }

            ArrayList<ChildSupplement> supplements = new ArrayList<>();
            for (ChildSupplement t: cur.getSupplements())
            {
                supplements.add(t);
            }

            ArrayList<ChildMedicine> medicines = new ArrayList<>();
            for (ChildMedicine t: cur.getMedicineList())
            {
                medicines.add(t);
            }

            tmp = new Child(guid, allergies, medicines, supplements, name, birthdate, gender);

            Log.d("onAttach", "tmp: "+tmp.toString());
            Log.d("onAttach", "cur: "+Application.getInstance().getCurrentChild().toString());

        }
        else Log.d("onAttach", "Edit mode false");

        super.onAttach(context);
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;

        if(v == toolbarSave){

            try {
                String deviceId = Application.getAndroidId(Application.getInstance().getApplicationContext());
                Gson gson = new Gson();
                okHttp.post(getString(R.string.api_child)+deviceId, gson.toJson(Application.getInstance().getNewChild()));
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            //new com.abdo.patrick.abdo.Api.Child.Post().execute(Application.getInstance().getNewChild());
            Application.getInstance().addNewChildToAnonymous(Application.getInstance().getNewChild());
            Application.getInstance().removeNewChild();

            toolbarSave.setVisibility(View.INVISIBLE);

            Intent i = new Intent(getActivity(), MainActivity.class);
            startActivity(i);
            getActivity().finish();
            return;

//            fragment = new NewUserFragment();
//            FragmentManager fragmentManager2 = getFragmentManager();
//            FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
//            fragmentManager2.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//            fragmentTransaction2.replace(R.id.main_activity_fragment, fragment);
//            fragmentTransaction2.commit();
        }

        switch(v.getId()){
            case R.id.create_child_data_category:{
                fragment = new ChildStamData();
                Bundle i = new Bundle();
                i.putBoolean("edit", editMode);
                fragment.setArguments(i);
                break;
            }
            case R.id.create_child_medicine_category:{
                fragment = new ChildMedicineData();
                Bundle i = new Bundle();
                i.putString("listType", "medicine");
                i.putBoolean("edit", editMode);
                fragment.setArguments(i);
                break;
            }
            case R.id.create_child_allergy_category:{
                fragment = new ChildDataListFagment();
                Bundle i = new Bundle();
                i.putString("listType", "allergies");
                i.putBoolean("edit", editMode);
                fragment.setArguments(i);
                break;
            }
            case R.id.create_child_supplement_category:{
                fragment = new ChildDataListFagment();
                Bundle i = new Bundle();
                i.putString("listType", "supplements");
                i.putBoolean("edit", editMode);
                fragment.setArguments(i);
                break;
            }
        }

        if(!editMode){
            toolbarSave.setVisibility(View.INVISIBLE);
        }
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.addToBackStack(null);
        if(editMode){
            fragmentTransaction.replace(R.id.main_activity_reg_fragment, fragment);
        }else{
            fragmentTransaction.replace(R.id.main_activity_fragment, fragment);
        }
        fragmentTransaction.commit();
    }
}
