package com.abdo.patrick.abdo.Views.Registraion;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.abdo.patrick.abdo.Controllers.ImageController;
import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.R;
import com.abdo.patrick.abdo.Views.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class PainPlacement extends Fragment implements View.OnTouchListener {

    ImageView overlay;
    ImageView underlay;

    ImageController imageController;

    public PainPlacement() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pain_placement, container, false);

        imageController = new ImageController(this);

        overlay = (ImageView) view.findViewById(R.id.image_overlay);
        underlay = (ImageView) view.findViewById(R.id.image_underlay);

        overlay.setOnTouchListener(this);


        return view;
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        ImageController.Touch touch = imageController.GetTouchedPosition(overlay, motionEvent, ((MainActivity)getActivity()).getToolbarHeight());
        int color = imageController.GetPixelColor(underlay, touch);

        if (color == -1) return false;

        Bundle i = new Bundle();
        int painPlacementId;

        switch (color){

            case Color.YELLOW:
                painPlacementId = 1;
                i.putString("rating_header", "Hoved: Hvor ondt gør det?");
                break;

            case Color.RED:
                painPlacementId = 2;
                i.putString("rating_header", "Bryst: Hvor ondt gør det?");
                break;

            case Color.GREEN:
                painPlacementId = 3;
                i.putString("rating_header", "Øvre mave: Hvor ondt gør det?");
                break;

            case Color.BLUE:
                painPlacementId = 4;
                i.putString("rating_header", "Under mave: Hvor ondt gør det?");
                break;

            default:
                return false;
        }

        //Set pain placement into registration
        Application.getInstance().getCurrentRegistration().addPainPlacement(painPlacementId);

        Fragment fragment = new Rating();
        fragment.setArguments(i);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.main_activity_reg_fragment, fragment);
        fragmentTransaction.commit();

        return false;
    }
}
