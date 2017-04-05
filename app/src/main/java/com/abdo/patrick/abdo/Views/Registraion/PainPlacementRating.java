package com.abdo.patrick.abdo.Views.Registraion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abdo.patrick.abdo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PainPlacementRating extends Fragment {


    private String pain_location;
    private String header;
    private TextView textView;

    public PainPlacementRating() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pain_placement_rating, container, false);

        pain_location = getArguments().getString("pain_header", "");
        textView = (TextView) view.findViewById(R.id.pain_location);

        textView.setText(pain_location);

        return view;
    }

}
