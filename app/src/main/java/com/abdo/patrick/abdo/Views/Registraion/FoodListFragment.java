package com.abdo.patrick.abdo.Views.Registraion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        model = new RegistrationListController(this);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_list, container, false);


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.child_data_list);

        String foodType = getArguments().getString("foodType");

        ArrayList<Food> food = Application.getInstance().get_foodList(foodType);
        model.InitViews(recyclerView, Application.getInstance().getFoodListView(food, Application.getInstance().getCurrentChild()));

        return view;
    }

}
