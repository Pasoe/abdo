package com.abdo.patrick.abdo.Views.Registraion;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

        underlay.setOnTouchListener(this);



        return view;
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        ImageController.Touch touch = imageController.GetTouchedPosition(underlay, motionEvent, ((MainActivity)getActivity()).getToolbarHeight());
        int color = imageController.GetPixelColor(underlay, touch);

        switch (color){

            case Color.YELLOW:
                Toast.makeText(getActivity(), "Head!", Toast.LENGTH_SHORT).show();
                break;

            case Color.RED:
                Toast.makeText(getActivity(), "Breast!", Toast.LENGTH_SHORT).show();
                break;

            case Color.GREEN:
                Toast.makeText(getActivity(), "Stomach!", Toast.LENGTH_SHORT).show();
                break;

            case Color.BLUE:
                Toast.makeText(getActivity(), "Genitals!", Toast.LENGTH_SHORT).show();
                break;
        }

        return false;
    }
}
