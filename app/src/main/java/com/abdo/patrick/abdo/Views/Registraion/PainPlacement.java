package com.abdo.patrick.abdo.Views.Registraion;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.abdo.patrick.abdo.Controllers.ImageController;
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

        switch (color){

            case Color.YELLOW:
//                Toast.makeText(getActivity(), "Hoved!", Toast.LENGTH_SHORT).show();
                i.putString("pain_header", "Hvor ondt har du i hovedet?");
                break;

            case Color.RED:
//                Toast.makeText(getActivity(), "Bryst!", Toast.LENGTH_SHORT).show();
                i.putString("pain_header", "Hvor ondt har du i brystet?");
                break;

            case Color.GREEN:
//                Toast.makeText(getActivity(), "Mave!", Toast.LENGTH_SHORT).show();
                i.putString("pain_header", "Hvor ondt har du i maven?");
                break;

            case Color.BLUE:
//                Toast.makeText(getActivity(), "Penis!", Toast.LENGTH_SHORT).show();
                i.putString("pain_header", "Hvor ondt har du ved dit kønsorgan");
                break;

            default:
                return false;
        }

        Fragment fragment = new PainPlacementRating();
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
