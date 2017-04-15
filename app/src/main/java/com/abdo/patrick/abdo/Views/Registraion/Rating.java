package com.abdo.patrick.abdo.Views.Registraion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

        //Header
        RelativeLayout header = (RelativeLayout) view.findViewById(R.id.rating_header);
        ImageView header_icon = (ImageView) view.findViewById(R.id.rating_header_icon);
        TextView header_text = (TextView) view.findViewById(R.id.pain_location);
        header_text.setText(getArguments().getString("rating_header", ""));

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
            case "sleep":
                SleepFragment_Init();
                fragment = "sleep";
                header.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkGrey));
                header_icon.setImageResource(R.drawable.icon_sleep);

                break;
            case "mood":
                MoodFragment_Init();
                fragment = "mood";
                header.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorBlue));
                header_icon.setImageResource(R.drawable.icon_mood);
                break;
            case "activity":
                ActivityFragment_Init();
                fragment = "activity";
                header.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPurple));
                header_icon.setImageResource(R.drawable.icon_excersize);
                break;

            default:
                PainFragment_Init();
                fragment = "";
                header.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorOrange));
                header_icon.setImageResource(R.drawable.icon_pain);
                break;
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        int level = 0;

        switch (id)
        {
            case R.id.level_row_1:
                level = 1;
                break;
            case R.id.level_row_2:
                level = 2;
                break;
            case R.id.level_row_3:
                level = 3;
                break;
            case R.id.level_row_4:
                level = 4;
                break;
            case R.id.level_row_5:
                level = 5;
                break;
            default:
                break;
        }

        switch (fragment)
        {
            case "sleep":
                Application.getInstance().getCurrentRegistration().addSleep(level);
                break;
            case "mood":
                Application.getInstance().getCurrentRegistration().addMood(level);
                break;
            case "activity":
                Application.getInstance().getCurrentRegistration().addActivity(level);
                break;
            default:
                Application.getInstance().getCurrentRegistration().addPainLevel(level);
                break;
        }

        FragmentManager fragmentManager = getFragmentManager();

        if (!getArguments().getString("fragment", "").isEmpty())
        {

            if (!getArguments().getString("fragment", "").equals("modify_pain"))
                fragmentManager.popBackStack();
            else
            {
                ClearBackStack_GoHome(fragmentManager);
            }
        }
        else ClearBackStack_GoHome(fragmentManager);

    }

    private void ClearBackStack_GoHome(FragmentManager fragmentManager)
    {
        Fragment fragment;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragment = new RegistraionComplete();
        fragmentTransaction.replace(R.id.main_activity_reg_fragment, fragment);
        fragmentTransaction.commit();
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

}
