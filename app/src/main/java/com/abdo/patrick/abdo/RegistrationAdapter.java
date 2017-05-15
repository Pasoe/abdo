package com.abdo.patrick.abdo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_element, viewGroup, false);
        return new RegistrationAdapter.RegViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RegistrationAdapter.RegViewHolder viewHolder, int i) {
        viewHolder.row_text.setText(list.get(i).getDate());

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
        public RegViewHolder(View view) {
            super(view);
            row_text = (TextView)view.findViewById(R.id.row_text);
        }
    }
}
