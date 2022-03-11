package com.example.assignment_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.itemViewHolder> {

       Context context ;
       ArrayList<items> list ;
    public RecyclerViewAdapter(Context c, ArrayList<items> list_item) {
        this.context = c ;
        this.list = list_item;
    }

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_item =LayoutInflater.from(context).inflate(R.layout.item,parent ,false);
        return new itemViewHolder(view_item);
    }
    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {
        items info_user =  list.get(position);
        holder.t_name.setText(info_user.getName());
        holder.t_num.setText(info_user.getNumber());
       holder.t_address.setText(info_user.getAdress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class itemViewHolder extends RecyclerView.ViewHolder{
     TextView t_name ;
     TextView t_num;
     TextView t_address;

        public itemViewHolder(@NonNull View itemView) {
            super(itemView);
            t_name = itemView.findViewById(R.id.Name);
            t_num = itemView.findViewById(R.id.Number);
            t_address =itemView.findViewById(R.id.Address);
        }
    }

}
