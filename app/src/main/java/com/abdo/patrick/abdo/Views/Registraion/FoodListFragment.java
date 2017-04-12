package com.abdo.patrick.abdo.Views.Registraion;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abdo.patrick.abdo.Controllers.RegistrationListController;
import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Food;
import com.abdo.patrick.abdo.Models.FoodCategory;
import com.abdo.patrick.abdo.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodListFragment extends Fragment {


    private RegistrationListController model;

    public FoodListFragment() {
        // Required empty public constructor
    }

    private ImageView headerImage;
    private TextView headerText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        model = new RegistrationListController(this);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_list, container, false);

        headerImage = (ImageView) view.findViewById(R.id.food_list_header_icon);
        headerText = (TextView) view.findViewById(R.id.food_list_header_text);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.food_list);

        String foodType = getArguments().getString("foodType");

        setHeader(foodType);

        ArrayList<Food> food = Application.getInstance().get_foodList(foodType);
        model.InitViews(recyclerView, Application.getInstance().getFoodListView(food, Application.getInstance().getCurrentChild()));

        return view;
    }

    private void setHeader(String foodType){
        if(foodType.equals("breakfast")){
            headerImage.setImageResource(R.drawable.icon_breakfast);
            headerText.setText("Hvad har du fået at spise til morgenmad?");
        }
        if(foodType.equals("lunch")){
            headerImage.setImageResource(R.drawable.icon_lunch);
            headerText.setText("Hvad har du fået at spise til frokost?");
        }
        if(foodType.equals("dinner")){
            headerImage.setImageResource(R.drawable.icon_dinner);
            headerText.setText("Hvad har du fået at spise til aftensmad?");
        }
        if(foodType.equals("fruit")){
            headerImage.setImageResource(R.drawable.icon_fruit);
            headerText.setText("Hvilke frugter har du spist i dag?");
        }
        if(foodType.equals("candy")){
            headerImage.setImageResource(R.drawable.icon_candy);
            headerText.setText("Hvilket slik har du spist i dag?");
        }

    }

}
