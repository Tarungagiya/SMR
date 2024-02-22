package com.example.sharemyride;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    ArrayList<user> list;
    String from,to;

    public MyAdapter(Context context, ArrayList<user> list, String from, String to) {
        this.context = context;
        this.list = list;
        this.from = from;
        this.to = to;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.ride_show_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        user user = list.get(position);

        holder.time.setText(user.getTime());
        holder.from.setText(user.getFrom());
        holder.to.setText(user.getTo());
        holder.person.setText(user.getPerson());
        holder.name.setText(user.getName());







    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView time,from,to,person,name;
        LinearLayout item_main;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.show_time);
            from = itemView.findViewById(R.id.show_from);
            to = itemView.findViewById(R.id.show_to);
            person = itemView.findViewById(R.id.show_person);
            name = itemView.findViewById(R.id.show_name);

            item_main = itemView.findViewById(R.id.item_main);

        }
    }

}
