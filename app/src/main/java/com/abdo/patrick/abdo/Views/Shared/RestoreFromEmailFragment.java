package com.abdo.patrick.abdo.Views.Shared;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.abdo.patrick.abdo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestoreFromEmailFragment extends Fragment implements View.OnClickListener {


    public RestoreFromEmailFragment() {
        // Required empty public constructor
    }

    public TextView toolbarSave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restore_from_email, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        TextView toolbarTitle = (TextView) getActivity().findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getResources().getString(R.string.title_restore));

        toolbarSave = (TextView) getActivity().findViewById(R.id.toolbar_save);
        toolbarSave.setVisibility(View.VISIBLE);
        toolbarSave.setOnClickListener(this);

        final EditText editText = (EditText) view.findViewById(R.id.editTextInputEmail);

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                InputMethodManager keyboard = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (focused)
                    keyboard.showSoftInput(editText, 0);
                else
                    keyboard.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == v.findViewById(R.id.toolbar_save)){
            //SAVE CODE!

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
