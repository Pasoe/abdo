package com.abdo.patrick.abdo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.abdo.patrick.abdo.R;

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

        Button newChild = (Button) view.findViewById(R.id.btnNewChild);
        Button typeCode = (Button) view.findViewById(R.id.btnTypeCode);
        Button restoreFromEmail = (Button) view.findViewById(R.id.btnRestoreFromEmail);

        newChild.setOnClickListener(this);
        typeCode.setOnClickListener(this);
        restoreFromEmail.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnNewChild:{
                Toast.makeText(getActivity(), "New Child",
                        Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.btnTypeCode:{
                FragmentManager fragmentManager2 = getFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                TypeCodeFragment fragment2 = new TypeCodeFragment();
                fragmentTransaction2.addToBackStack("wat");
                fragmentTransaction2.replace(R.id.main_activity_fragment, fragment2);
                fragmentTransaction2.commit();
                break;
            }
            case R.id.btnRestoreFromEmail:{
                Toast.makeText(getActivity(), "Restore from email",
                        Toast.LENGTH_LONG).show();
                break;
            }
        }
    }
}
