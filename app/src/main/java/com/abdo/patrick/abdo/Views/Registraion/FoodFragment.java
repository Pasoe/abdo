package com.abdo.patrick.abdo.Views.Registraion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.abdo.patrick.abdo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodFragment extends Fragment implements View.OnClickListener {


    LinearLayout breakfast_button;
    LinearLayout lunch_button;
    LinearLayout dinner_button;
    LinearLayout fruit_button;
    LinearLayout candy_button;
    LinearLayout no_food_button;

    public FoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food, container, false);

        breakfast_button = (LinearLayout) view.findViewById(R.id.food_breakfast_button);
        breakfast_button.setOnClickListener(this);

        lunch_button = (LinearLayout) view.findViewById(R.id.food_lunch_button);
        lunch_button.setOnClickListener(this);

        dinner_button = (LinearLayout) view.findViewById(R.id.food_dinner_button);
        dinner_button.setOnClickListener(this);

        fruit_button = (LinearLayout) view.findViewById(R.id.food_fruit_button);
        fruit_button.setOnClickListener(this);

        candy_button = (LinearLayout) view.findViewById(R.id.food_candy_button);
        candy_button.setOnClickListener(this);

        no_food_button = (LinearLayout) view.findViewById(R.id.food_no_food_button);
        no_food_button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        if(v == no_food_button){
            //TODO - Sæt childs foodcategory til no-food
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.popBackStack();
            return;
        }

        Fragment fragment =  new FoodListFragment();;
        Bundle i = new Bundle();

        if(v == breakfast_button){
            i.putString("foodType", "breakfast");
            fragment.setArguments(i);
        }
        if(v == lunch_button){
            i.putString("foodType", "lunch");
            fragment.setArguments(i);
        }
        if(v == dinner_button){
            i.putString("foodType", "dinner");
            fragment.setArguments(i);
        }
        if(v == fruit_button){
            i.putString("foodType", "fruit");
            fragment.setArguments(i);
        }
        if(v == candy_button){
            i.putString("foodType", "candy");
            fragment.setArguments(i);
        }

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.main_activity_reg_fragment, fragment);
        fragmentTransaction.commit();
    }
}
