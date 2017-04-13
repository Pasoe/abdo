package com.abdo.patrick.abdo.Views.Registraion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistraionComplete extends Fragment implements View.OnClickListener{


    public RegistraionComplete() {
        // Required empty public constructor
    }

    private LinearLayout
             answer_toilet_tile
            ,answer_food_tile
            ,answer_sleep_tile
            ,answer_mood_tile
            ,answer_activity_tile
            ,answer_pain_tile;

    private ImageView
             statusSleep
            ,statusMood
            ,statusActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registraion_complete, container, false);

        statusSleep = (ImageView) view.findViewById(R.id.answered_icon_sleep_status);
        statusMood = (ImageView) view.findViewById(R.id.answered_icon_mood_status);
        statusActivity = (ImageView) view.findViewById(R.id.answered_icon_excersize_status);

        answer_toilet_tile = (LinearLayout) view.findViewById(R.id.answer_toilet_tile);
        answer_toilet_tile.setOnClickListener(this);

        answer_food_tile = (LinearLayout) view.findViewById(R.id.answer_food_tile);
        answer_food_tile.setOnClickListener(this);

        answer_sleep_tile = (LinearLayout) view.findViewById(R.id.answer_sleep_tile);
        answer_sleep_tile.setOnClickListener(this);

        answer_mood_tile = (LinearLayout) view.findViewById(R.id.answer_mood_tile);
        answer_mood_tile.setOnClickListener(this);

        answer_activity_tile = (LinearLayout) view.findViewById(R.id.answer_exercise_tile);
        answer_activity_tile.setOnClickListener(this);

        answer_pain_tile = (LinearLayout) view.findViewById(R.id.answer_pain_tile);
        answer_pain_tile.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();

        if(v == answer_toilet_tile){
            fragment = new ToiletFragment();
        }
        if(v == answer_food_tile){
            fragment = new FoodFragment();
        }

        if(v == answer_sleep_tile){
            bundle.putString("fragment", "sleep");
            bundle.putString("rating_header", "Hvor godt har du sovet?");
        }
        if(v == answer_mood_tile){
            bundle.putString("fragment", "mood");
            bundle.putString("rating_header", "Har du været i godt humør?");
        }
        if(v == answer_activity_tile){
            bundle.putString("fragment", "activity");
            bundle.putString("rating_header", "Har du brugt kroppen i dag?");
        }
        if(v == answer_pain_tile){
            bundle.putString("fragment", "pain");
            bundle.putString("rating_header", "Hvor ondt gør det?");
        }

        if (fragment == null) fragment = new Rating();
        if (!bundle.isEmpty()) fragment.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.main_activity_reg_fragment, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onResume() {
        Log.i("onResume", Application.getInstance().getCurrentRegistration().toString());

        if (Application.getInstance().getCurrentRegistration().getSleepId() != null)
        {
            statusSleep.setImageResource(R.drawable.icon_checkmark_vector);
        }else statusSleep.setImageResource(R.drawable.icon_questionmark);

        if (Application.getInstance().getCurrentRegistration().getActivityId() != null)
        {
            statusActivity.setImageResource(R.drawable.icon_checkmark_vector);
        }else statusActivity.setImageResource(R.drawable.icon_questionmark);

        if (Application.getInstance().getCurrentRegistration().getMoodId() != null)
        {
            statusMood.setImageResource(R.drawable.icon_checkmark_vector);
        }else statusMood.setImageResource(R.drawable.icon_questionmark);


        super.onResume();
    }

}
