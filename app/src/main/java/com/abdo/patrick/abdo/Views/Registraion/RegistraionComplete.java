package com.abdo.patrick.abdo.Views.Registraion;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abdo.patrick.abdo.Api.OkHttp;
import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Registration;
import com.abdo.patrick.abdo.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

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

    private RelativeLayout complete_registration_button;
    private RelativeLayout save_reg_button;

    private ImageView
             sleepStatus
            ,moodStatus
            ,activityStatus
            ,toiletStatus
            ,foodStatus;

    private OkHttp okHttp;

    private Boolean editMode = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registraion_complete, container, false);

        TextView toolbarTitle = (TextView) getActivity().findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Oversigt");

        okHttp = new OkHttp();

        if(getArguments() != null){
            editMode = getArguments().getBoolean("edit", false);
        }

        if(editMode){
            RelativeLayout send_reg_button = (RelativeLayout) view.findViewById(R.id.complete_registration_button);
            send_reg_button.setVisibility(View.GONE);

            save_reg_button = (RelativeLayout) view.findViewById(R.id.save_edit_registration_button);
            save_reg_button.setOnClickListener(this);
            save_reg_button.setVisibility(View.VISIBLE);
        }

        sleepStatus = (ImageView) view.findViewById(R.id.answered_icon_sleep_status);
        moodStatus = (ImageView) view.findViewById(R.id.answered_icon_mood_status);
        activityStatus = (ImageView) view.findViewById(R.id.answered_icon_excersize_status);
        toiletStatus = (ImageView) view.findViewById(R.id.answered_icon_toilet_status);
        foodStatus = (ImageView) view.findViewById(R.id.answered_icon_food_status);

        complete_registration_button = (RelativeLayout) view.findViewById(R.id.complete_registration_button);
        complete_registration_button.setOnClickListener(this);

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
        boolean sendRegistrationClicked = false;
        boolean modifyPainPlacement = false;

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
            modifyPainPlacement = !modifyPainPlacement;
            bundle.putString("fragment", "modifiy_pain");
            Application.getInstance().editMode = true;

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragment = new PainPlacement();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.main_activity_reg_fragment, fragment);
            fragmentTransaction.commit();
        }

        if (v == complete_registration_button)
        {
            sendRegistrationClicked = !sendRegistrationClicked;
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(Html.fromHtml("<font color='#FFFFFF'>Vil du sende din registrering?</font>"))
                    .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Registration registration = Application.getInstance().getCurrentRegistration();
                            //Add GUID to registration
                            if (registration.getGuid() == null || registration.getGuid().isEmpty())
                                registration.setGuid(UUID.randomUUID().toString());
                            registration.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));

                            //Save registration locally
                            Application.getInstance().getCurrentChild().addRegistration(registration);
                            Application.getInstance().updateCurrentChildData(Application.getInstance().getCurrentChild());

                            //Call api
                            try {
                                Gson gson = new Gson();
                                String deviceId = Application.getAndroidId(Application.getInstance().getApplicationContext());
                                String childGuid = Application.getInstance().getCurrentChild().getGuid();

                                okHttp.post(getString(R.string.api_registration)+deviceId+"/"+childGuid, gson.toJson(registration));

                                Toast.makeText(getContext(), "Registrering sendt!", Toast.LENGTH_SHORT).show();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }

                            //Clear current registration
                            Application.getInstance().InitiateCurrentRegistration();

                            //Go back to pain placement
                            Fragment fragment = new PainPlacement();
                            FragmentManager fragmentManager = getFragmentManager();

                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                            fragmentTransaction.replace(R.id.main_activity_reg_fragment, fragment);
                            fragmentTransaction.commit();

                        }
                    })
                    .setNegativeButton("Nej", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            dialog.show();
            dialog.getWindow().setBackgroundDrawableResource(R.color.colorGreen);
        }

        if(v == save_reg_button){
            Application.getInstance().updateCurrentRegistration();
            Application.getInstance().InitiateCurrentRegistration();

            //TODO - UPDATE THE SERVER WITH CHANGES

            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.popBackStack();
            return;
        }

        if (!sendRegistrationClicked && !modifyPainPlacement)
        {
            if (fragment == null) fragment = new Rating();
            if (!bundle.isEmpty()) fragment.setArguments(bundle);

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.main_activity_reg_fragment, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onResume() {
        Log.i("onResume, registration", Application.getInstance().getCurrentRegistration().toString());

        if (Application.getInstance().getCurrentRegistration().getSleepId() != null)
        {
            sleepStatus.setImageResource(R.drawable.icon_checkmark_vector);
        }else sleepStatus.setImageResource(R.drawable.icon_questionmark);

        if (Application.getInstance().getCurrentRegistration().getActivityId() != null)
        {
            activityStatus.setImageResource(R.drawable.icon_checkmark_vector);
        }else activityStatus.setImageResource(R.drawable.icon_questionmark);

        if (Application.getInstance().getCurrentRegistration().getMoodId() != null)
        {
            moodStatus.setImageResource(R.drawable.icon_checkmark_vector);
        }else moodStatus.setImageResource(R.drawable.icon_questionmark);

        if (Application.getInstance().getCurrentRegistration().getFecesId() != null)
        {
            toiletStatus.setImageResource(R.drawable.icon_checkmark_vector);
        }else toiletStatus.setImageResource(R.drawable.icon_questionmark);

        if ( Application.getInstance().getCurrentRegistration().getFoods() != null &&
                (Application.getInstance().getCurrentRegistration().getFoods().size() > 0 ||
                        Application.getInstance().getCurrentRegistration().hasNoFood()))
        {
            foodStatus.setImageResource(R.drawable.icon_checkmark_vector);
        }else foodStatus.setImageResource(R.drawable.icon_questionmark);


        super.onResume();
    }

}
