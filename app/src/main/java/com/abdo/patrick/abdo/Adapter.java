package com.abdo.patrick.abdo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.ViewModels.ListItem;

import java.util.ArrayList;

/**
 * Created by Patrick on 21-03-2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<ListItem> list;

    public Adapter(ArrayList<ListItem> list) {
        this.list = list;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_element, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder viewHolder, int i) {
        viewHolder.row_text.setText(list.get(i).getName());
        if(list.get(i).isSelected()){
            viewHolder.row_selected_icon.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public String getItemName(int position){
        return list.get(position).getName();
    }

    public int getId(int position){
        return list.get(position).getId();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView row_text;
        private ImageView row_selected_icon;
        public ViewHolder(View view) {
            super(view);
            row_text = (TextView)view.findViewById(R.id.row_text);
            row_selected_icon = (ImageView) view.findViewById(R.id.row_selected_icon);
        }
    }

}
