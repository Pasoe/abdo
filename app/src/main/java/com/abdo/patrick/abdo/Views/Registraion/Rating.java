package com.abdo.patrick.abdo.Views.Registraion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.PainLevel;
import com.abdo.patrick.abdo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Rating extends Fragment implements View.OnClickListener {

    private TextView
             text_row_1
            ,text_row_2
            ,text_row_3
            ,text_row_4
            ,text_row_5;

    private String fragment = "pain";  //Default

    public Rating() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pain_placement_rating, container, false);

        TextView painLocation_header = (TextView) view.findViewById(R.id.pain_location);
        painLocation_header.setText(getArguments().getString("pain_header", ""));

        LinearLayout layout_row_1 = (LinearLayout) view.findViewById(R.id.level_row_1); layout_row_1.setOnClickListener(this);
        LinearLayout layout_row_2 = (LinearLayout) view.findViewById(R.id.level_row_2); layout_row_2.setOnClickListener(this);
        LinearLayout layout_row_3 = (LinearLayout) view.findViewById(R.id.level_row_3); layout_row_3.setOnClickListener(this);
        LinearLayout layout_row_4 = (LinearLayout) view.findViewById(R.id.level_row_4); layout_row_4.setOnClickListener(this);
        LinearLayout layout_row_5 = (LinearLayout) view.findViewById(R.id.level_row_5); layout_row_5.setOnClickListener(this);

        text_row_1 = (TextView) view.findViewById(R.id.level_text_row_1);
        text_row_2 = (TextView) view.findViewById(R.id.level_text_row_2);
        text_row_3 = (TextView) view.findViewById(R.id.level_text_row_3);
        text_row_4 = (TextView) view.findViewById(R.id.level_text_row_4);
        text_row_5 = (TextView) view.findViewById(R.id.level_text_row_5);

        switch (getArguments().getString("fragment", ""))
        {
            case "pain":
                PainFragment_Init();
                fragment = "pain";
                break;
            case "sleep":
                SleepFragment_Init();
                fragment = "sleep";
                break;
            case "mood":
                MoodFragment_Init();
                fragment = "mood";
                break;
            case "activity":
                ActivityFragment_Init();
                fragment = "activity";
                break;

            default:
                PainFragment_Init();
                fragment = "";
                break;
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        String toast = "";

        switch (id)
        {
            case R.id.level_row_1:
                //TODO: add to child object by using "fragment" value
                toast = "Niveau 1";
                break;
            case R.id.level_row_2:
                //TODO: add to child object by using "fragment" value
                toast = "Niveau 2";
                break;
            case R.id.level_row_3:
                //TODO: add to child object by using "fragment" value
                toast = "Niveau 3";
                break;
            case R.id.level_row_4:
                //TODO: add to child object by using "fragment" value
                toast = "Niveau 4";
                break;
            case R.id.level_row_5:
                //TODO: add to child object by using "fragment" value
                toast = "Niveau 5";
                break;
            default:
                break;
        }

        if (!toast.isEmpty())
            Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show();

        Fragment fragment;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);

        if (getArguments().getString("fragment", "").isEmpty())
        {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragment = new RegistraionComplete();
            fragmentTransaction.replace(R.id.main_activity_reg_fragment, fragment);
            fragmentTransaction.commit();
        }
        else
        {
            fragmentManager.popBackStack();
        }


    }


    private void PainFragment_Init(){
        text_row_1.setText(R.string.pain_level_1);
        text_row_2.setText(R.string.pain_level_2);
        text_row_3.setText(R.string.pain_level_3);
        text_row_4.setText(R.string.pain_level_4);
        text_row_5.setText(R.string.pain_level_5);
    }

    private void SleepFragment_Init(){
        text_row_1.setText(R.string.sleep_level_1);
        text_row_2.setText(R.string.sleep_level_2);
        text_row_3.setText(R.string.sleep_level_3);
        text_row_4.setText(R.string.sleep_level_4);
        text_row_5.setText(R.string.sleep_level_5);
    }

    private void MoodFragment_Init(){
        text_row_1.setText(R.string.mood_level_1);
        text_row_2.setText(R.string.mood_level_2);
        text_row_3.setText(R.string.mood_level_3);
        text_row_4.setText(R.string.mood_level_4);
        text_row_5.setText(R.string.mood_level_5);
    }

    private void ActivityFragment_Init(){
        text_row_1.setText(R.string.activity_level_1);
        text_row_2.setText(R.string.activity_level_2);
        text_row_3.setText(R.string.activity_level_3);
        text_row_4.setText(R.string.activity_level_4);
        text_row_5.setText(R.string.activity_level_5);
    }


    private void AddToRegistration(String type, int level)
    {
        if(type.isEmpty() || type.equals("pain"))
        {
            Application.getInstance().addPainLevel(new PainLevel(level, type));
        }
        else if(type.equals("sleep"))
        {

        }
        else if(type.equals("mood"))
        {

        }
        else if(type.equals("activity"))
        {

        }
    }

}
