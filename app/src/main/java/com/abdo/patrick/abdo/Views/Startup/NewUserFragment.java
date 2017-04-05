package com.abdo.patrick.abdo.Views.Startup;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Child;
import com.abdo.patrick.abdo.R;
import com.abdo.patrick.abdo.Views.RegisterChild.ChildOverviewFragment;
import com.abdo.patrick.abdo.Views.Shared.RestoreFromEmailFragment;
import com.abdo.patrick.abdo.Views.Shared.TypeCodeFragment;
import com.google.gson.Gson;

import java.util.UUID;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewUserFragment extends Fragment implements View.OnClickListener {


    public NewUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_user, container, false);

        RelativeLayout newChild = (RelativeLayout) view.findViewById(R.id.new_user);
        RelativeLayout typeCode = (RelativeLayout) view.findViewById(R.id.new_user_code);
        RelativeLayout restoreFromEmail = (RelativeLayout) view.findViewById(R.id.new_user_email);
        TextView toolbarTitle = (TextView) getActivity().findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getResources().getString(R.string.app_name));

        newChild.setOnClickListener(this);
        typeCode.setOnClickListener(this);
        restoreFromEmail.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;

        switch(v.getId()){
            case R.id.new_user:{
                fragment = new ChildOverviewFragment();
                Application.getInstance().setNewChild(new Child(UUID.randomUUID().toString()));
                break;
            }
            case R.id.new_user_code:{
                fragment = new TypeCodeFragment();
                break;
            }
            case R.id.new_user_email:{
                fragment = new RestoreFromEmailFragment();
                break;
            }
        }

        FragmentManager fragmentManager2 = getFragmentManager();
        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
        fragmentTransaction2.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction2.addToBackStack("wat");
        fragmentTransaction2.replace(R.id.main_activity_fragment, fragment);
        fragmentTransaction2.commit();
    }
}
