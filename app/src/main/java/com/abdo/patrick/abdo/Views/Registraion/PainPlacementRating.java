package com.abdo.patrick.abdo.Views.Registraion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abdo.patrick.abdo.Controllers.ListController;
import com.abdo.patrick.abdo.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PainPlacementRating extends Fragment implements View.OnClickListener {

    final private int pain_levels = 5;

    private String pain_location;
    private TextView painLocation_header;

    private LinearLayout layout_row_1, layout_row_2, layout_row_3, layout_row_4, layout_row_5;
    private TextView text_row_1, text_row_2, text_row_3, text_row_4, text_row_5;

    public PainPlacementRating() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pain_placement_rating, container, false);

        pain_location = getArguments().getString("pain_header", "");
        painLocation_header = (TextView) view.findViewById(R.id.pain_location);
        painLocation_header.setText(pain_location);

        layout_row_1 = (LinearLayout) view.findViewById(R.id.painlevel_row_1); layout_row_1.setOnClickListener(this);
        layout_row_2 = (LinearLayout) view.findViewById(R.id.painlevel_row_2); layout_row_2.setOnClickListener(this);
        layout_row_3 = (LinearLayout) view.findViewById(R.id.painlevel_row_3); layout_row_3.setOnClickListener(this);
        layout_row_4 = (LinearLayout) view.findViewById(R.id.painlevel_row_4); layout_row_4.setOnClickListener(this);
        layout_row_5 = (LinearLayout) view.findViewById(R.id.painlevel_row_5); layout_row_5.setOnClickListener(this);

        text_row_1 = (TextView) view.findViewById(R.id.painlevel_text_row_1); text_row_1.setText(R.string.pain_level_1);
        text_row_2 = (TextView) view.findViewById(R.id.painlevel_text_row_2); text_row_2.setText(R.string.pain_level_2);
        text_row_3 = (TextView) view.findViewById(R.id.painlevel_text_row_3); text_row_3.setText(R.string.pain_level_3);
        text_row_4 = (TextView) view.findViewById(R.id.painlevel_text_row_4); text_row_4.setText(R.string.pain_level_4);
        text_row_5 = (TextView) view.findViewById(R.id.painlevel_text_row_5); text_row_5.setText(R.string.pain_level_5);

        return view;
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        String toast = "";

        switch (id)
        {
            case R.id.painlevel_row_1:
                toast = "Niveau 1";
                break;
            case R.id.painlevel_row_2:
                toast = "Niveau 2";
                break;
            case R.id.painlevel_row_3:
                toast = "Niveau 3";
                break;
            case R.id.painlevel_row_4:
                toast = "Niveau 4";
                break;
            case R.id.painlevel_row_5:
                toast = "Niveau 5";
                break;
        }

        if (!toast.isEmpty())
            Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show();
    }
}
