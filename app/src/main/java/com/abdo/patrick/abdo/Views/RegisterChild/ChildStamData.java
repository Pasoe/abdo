package com.abdo.patrick.abdo.Views.RegisterChild;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Child;
import com.abdo.patrick.abdo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChildStamData extends Fragment implements View.OnClickListener{


    public ChildStamData() {
        // Required empty public constructor
    }

    private TextView toolbarSave;
    private EditText nameField;
    private RadioButton genderBoyField;
    private RadioButton genderGirlField;
    private DatePicker birthdayField;
    private Child newChild;
    private Boolean editMode = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_child_stam_data, container, false);

        if(getArguments() != null){
            editMode = getArguments().getBoolean("edit", false);
        }
        if(editMode){
            newChild = Application.getInstance().getCurrentChild();
        }else{
            newChild = Application.getInstance().getNewChild();
        }

        TextView toolbarTitle = (TextView) getActivity().findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Stamdata");
        toolbarSave = (TextView) getActivity().findViewById(R.id.toolbar_save);
        toolbarSave.setVisibility(View.VISIBLE);
        toolbarSave.setOnClickListener(this);

        nameField = (EditText) view.findViewById(R.id.stamdata_name);
        genderBoyField = (RadioButton) view.findViewById(R.id.radio_button_boy);
        genderGirlField = (RadioButton) view.findViewById(R.id.radio_button_girl);
        birthdayField = (DatePicker) view.findViewById(R.id.stamdata_datepicker);

        populateInputFields();

        nameField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                InputMethodManager keyboard = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (focused)
                    keyboard.showSoftInput(nameField, 0);
                else
                    keyboard.hideSoftInputFromWindow(nameField.getWindowToken(), 0);
            }
        });

        return view;
    }

    private void populateInputFields(){

        if(newChild.getInfo() != null){
            if(newChild.getInfo().getName() != null){
                nameField.setText(newChild.getInfo().getName());
            }
            if(newChild.getInfo().getGender() != 0){
                if(newChild.getInfo().getGender() == 1){
                    genderBoyField.setChecked(true);
                    genderGirlField.setChecked(false);
                }
                else if(newChild.getInfo().getGender() == 2){
                    genderBoyField.setChecked(false);
                    genderGirlField.setChecked(true);
                }
            }

            if(newChild.getInfo().getName() != null){
                nameField.setText(newChild.getInfo().getName());
            }

            if(newChild.getInfo().getBirthdate() != null) {
                String[] datearray = newChild.getInfo().getBirthdate().split("-");
                birthdayField.init(Integer.parseInt(datearray[2]), Integer.parseInt(datearray[1]), Integer.parseInt(datearray[0]),null);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v == v.findViewById(R.id.toolbar_save)){

            newChild.getInfo().setName(nameField.getText().toString());
            if(genderBoyField.isChecked()){
                newChild.getInfo().setGender(1);
            }else if(genderGirlField.isChecked()){
                newChild.getInfo().setGender(2);
            }
            String birthday = birthdayField.getDayOfMonth()+"-"+birthdayField.getMonth()+"-"+birthdayField.getYear();
            newChild.getInfo().setBirthdate(birthday);

            if(editMode){
                Application.getInstance().updateCurrentChildData(newChild);
            }else{
                Application.getInstance().setNewChild(newChild);
            }

            FragmentManager fm = getFragmentManager();
            fm.popBackStack();
        }
    }

    @Override
    public void onPause() {
        toolbarSave.setVisibility(View.INVISIBLE);
        super.onPause();
    }
}
