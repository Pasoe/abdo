package com.abdo.patrick.abdo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.ViewModels.ListItem;
import com.abdo.patrick.abdo.ViewModels.RegistrationListItem;

import java.util.ArrayList;

/**
 * Created by Patrick on 15-05-2017.
 */

public class RegistrationAdapter extends RecyclerView.Adapter<RegistrationAdapter.RegViewHolder>{
    private ArrayList<RegistrationListItem> list;

    public RegistrationAdapter(ArrayList<RegistrationListItem> list) {
        this.list = list;
    }

    @Override
    public RegistrationAdapter.RegViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.registration_row_element, viewGroup, false);
        return new RegistrationAdapter.RegViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RegistrationAdapter.RegViewHolder viewHolder, int i) {
        viewHolder.row_text.setText(list.get(i).getDate());
        if(Application.getInstance().getCurrentChild().getRegistration(list.get(i).getId()).getFecesId() != null){
            viewHolder.feces_icon.setVisibility(View.VISIBLE);
        }
        if((Application.getInstance().getCurrentChild().getRegistration(list.get(i).getId()).getFoods() != null &&
                (Application.getInstance().getCurrentChild().getRegistration(list.get(i).getId()).getFoods().size() > 0 ||
                        Application.getInstance().getCurrentChild().getRegistration(list.get(i).getId()).hasNoFood()))){
            viewHolder.food_icon.setVisibility(View.VISIBLE);
        }
        if(Application.getInstance().getCurrentChild().getRegistration(list.get(i).getId()).getSleepId() != null){
            viewHolder.sleep_icon.setVisibility(View.VISIBLE);
        }
        if(Application.getInstance().getCurrentChild().getRegistration(list.get(i).getId()).getMoodId() != null){
            viewHolder.mood_icon.setVisibility(View.VISIBLE);
        }
        if(Application.getInstance().getCurrentChild().getRegistration(list.get(i).getId()).getActivityId() != null){
            viewHolder.activity_icon.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public String getItemDate(int position){
        return list.get(position).getDate();
    }
    public String getItemGuid(int position) { return list.get(position).getId(); }

    public class RegViewHolder extends RecyclerView.ViewHolder{
        private TextView row_text;
        private ImageView feces_icon;
        private ImageView food_icon;
        private ImageView sleep_icon;
        private ImageView mood_icon;
        private ImageView activity_icon;
        public RegViewHolder(View view) {
            super(view);
            row_text = (TextView)view.findViewById(R.id.reg_row_text);
            feces_icon = (ImageView)view.findViewById(R.id.reg_toilet_answered);
            food_icon = (ImageView)view.findViewById(R.id.reg_food_answered);
            sleep_icon = (ImageView)view.findViewById(R.id.reg_sleep_answered);
            mood_icon = (ImageView)view.findViewById(R.id.reg_mood_answered);
            activity_icon = (ImageView)view.findViewById(R.id.reg_activity_answered);
        }
    }
}
