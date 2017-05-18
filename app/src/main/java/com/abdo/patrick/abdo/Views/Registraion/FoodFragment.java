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
import android.widget.TextView;

import com.abdo.patrick.abdo.Domain.Application;
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

    ImageView breakfastStatus
            ,lunchStatus
            ,dinnerStatus
            ,fruitStatus
            ,candyStatus
            ,noFoodStatus;

    public FoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food, container, false);

        TextView toolbarTitle = (TextView) getActivity().findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Mad");

        breakfastStatus = (ImageView) view.findViewById(R.id.answered_icon_breakfast);
        lunchStatus = (ImageView) view.findViewById(R.id.answered_icon_lunch);
        dinnerStatus = (ImageView) view.findViewById(R.id.answered_icon_dinner);
        fruitStatus = (ImageView) view.findViewById(R.id.answered_icon_fruit);
        candyStatus = (ImageView) view.findViewById(R.id.answered_icon_candy);
        noFoodStatus = (ImageView) view.findViewById(R.id.answered_icon_no_food);

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
            Application.getInstance().getCurrentRegistration().setHasNoFood(!
                    Application.getInstance().getCurrentRegistration().hasNoFood());
            Application.getInstance().getCurrentRegistration().getFoods().clear();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.popBackStack();
            return;
        }

        Fragment fragment =  new FoodListFragment();;
        Bundle i = new Bundle();

        if(v == breakfast_button){
            i.putString("foodType", "Morgenmad");
            fragment.setArguments(i);
        }
        if(v == lunch_button){
            i.putString("foodType", "Frokost");
            fragment.setArguments(i);
        }
        if(v == dinner_button){
            i.putString("foodType", "Aftensmad");
            fragment.setArguments(i);
        }
        if(v == fruit_button){
            i.putString("foodType", "Frugt");
            fragment.setArguments(i);
        }
        if(v == candy_button){
            i.putString("foodType", "Sødt");
            fragment.setArguments(i);
        }

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.main_activity_reg_fragment, fragment);
        fragmentTransaction.commit();
    }

    public void onResume() {
        //Log.i("onResume, food overview", Application.getInstance().getCurrentRegistration().getFoods().toString());
        //Log.i("onResume, has food", ""+Application.getInstance().getCurrentRegistration().hasNoFood());

        int visibility =
                (
                        Application.getInstance().getCurrentRegistration().getFoods().isEmpty()
                        && Application.getInstance().getCurrentRegistration().hasNoFood()
                )
                        ?
                        View.VISIBLE : View.INVISIBLE;

        noFoodStatus.setVisibility(visibility);

        if (Application.getInstance().getCurrentRegistration().RegisteredFoodContainsCategory("Morgenmad"))
        {
            breakfastStatus.setImageResource(R.drawable.icon_checkmark_vector);
        }else breakfastStatus.setImageResource(R.drawable.icon_questionmark);

        if (Application.getInstance().getCurrentRegistration().RegisteredFoodContainsCategory("Frokost"))
        {
            lunchStatus.setImageResource(R.drawable.icon_checkmark_vector);
        }else lunchStatus.setImageResource(R.drawable.icon_questionmark);

        if (Application.getInstance().getCurrentRegistration().RegisteredFoodContainsCategory("Aftensmad"))
        {
            dinnerStatus.setImageResource(R.drawable.icon_checkmark_vector);
        }else dinnerStatus.setImageResource(R.drawable.icon_questionmark);

        if (Application.getInstance().getCurrentRegistration().RegisteredFoodContainsCategory("Frugt"))
        {
            fruitStatus.setImageResource(R.drawable.icon_checkmark_vector);
        }else fruitStatus.setImageResource(R.drawable.icon_questionmark);

        if (Application.getInstance().getCurrentRegistration().RegisteredFoodContainsCategory("Sødt"))
        {
            candyStatus.setImageResource(R.drawable.icon_checkmark_vector);
        }else candyStatus.setImageResource(R.drawable.icon_questionmark);

        super.onResume();
    }
}
