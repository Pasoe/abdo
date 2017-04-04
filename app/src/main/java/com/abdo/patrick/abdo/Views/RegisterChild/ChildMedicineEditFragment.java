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
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Child;
import com.abdo.patrick.abdo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChildMedicineEditFragment extends Fragment implements View.OnClickListener{


    public ChildMedicineEditFragment() {
        // Required empty public constructor
    }

    private TextView toolbarSave;
    private EditText medicineField;
    private EditText dosageField;
    private String type;
    private String dosage;
    private Bundle editParameters;
    private RelativeLayout deleteButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        editParameters = getArguments();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_child_medicine_edit, container, false);

        TextView toolbarTitle = (TextView) getActivity().findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Medicin");
        toolbarSave = (TextView) getActivity().findViewById(R.id.toolbar_save);
        toolbarSave.setVisibility(View.VISIBLE);
        toolbarSave.setOnClickListener(this);

        medicineField = (EditText) view.findViewById(R.id.medicine_type);
        dosageField = (EditText) view.findViewById(R.id.medicine_dosage);

        if(editParameters != null) {
            if (getArguments().containsKey("type") && getArguments().containsKey("dosage")) {
                medicineField.setText(getArguments().getString("type"));
                dosageField.setText(getArguments().getString("dosage"));

                deleteButton = (RelativeLayout) view.findViewById(R.id.medicine_delete_button);
                deleteButton.setOnClickListener(this);
                deleteButton.setVisibility(View.VISIBLE);
            }
        }

        medicineField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                InputMethodManager keyboard = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (focused)
                    keyboard.showSoftInput(medicineField, 0);
                else
                    keyboard.hideSoftInputFromWindow(medicineField.getWindowToken(), 0);
            }
        });

        dosageField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                InputMethodManager keyboard = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (focused)
                    keyboard.showSoftInput(medicineField, 0);
                else
                    keyboard.hideSoftInputFromWindow(medicineField.getWindowToken(), 0);
            }
        });

        return view;
    }

    public void onBackPressed(){

    }

    @Override
    public void onClick(View v) {
        if(v == toolbarSave){
            if(medicineField.getText().toString().equals("") || dosageField.getText().toString().equals("")){
                Toast.makeText(getActivity(),"Begge felter skal udfyldes", Toast.LENGTH_SHORT).show();
                return;
            }

            toolbarSave.setVisibility(View.INVISIBLE);

            Child newChild = Application.getInstance().getNewChild();

            if(editParameters != null) {
                if(getArguments().containsKey("type") && getArguments().containsKey("dosage")){
                    newChild.updateMedicine(getArguments().getString("type"), getArguments().getString("dosage"), medicineField.getText().toString(), dosageField.getText().toString());
                    deleteButton.setVisibility(View.INVISIBLE);
                }
            } else{
                newChild.addMedicine(medicineField.getText().toString(), dosageField.getText().toString());
            }

            Application.getInstance().setNewChild(newChild);
            FragmentManager fm = getFragmentManager();
            fm.popBackStack();
        }
        if(v == deleteButton){
            toolbarSave.setVisibility(View.INVISIBLE);
            deleteButton.setVisibility(View.INVISIBLE);

            Child newChild = Application.getInstance().getNewChild();
            newChild.removeMedicine(getArguments().getString("type"), getArguments().getString("dosage"));
            Application.getInstance().setNewChild(newChild);
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
