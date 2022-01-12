package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.List;


public class resturantitemAdapter extends RecyclerView.Adapter<resturantitemAdapter.ViewHolder>  {

    private Context context;
    private View view;
    private List<String> list;
    private List<Integer> listpic;

    public resturantitemAdapter(List<String> list, List<Integer> listpic, Context context) {

        this.list = list;
        this.context=context;
        this.listpic=listpic;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try {
            Glide.with(context).load(listpic.get(position)).into(holder.resturant_image);
        } catch (Exception e) {
            e.printStackTrace();
        }


        holder.resturant_name.setText(list.get(position));


    }


    @Override
    public int getItemCount() {

        return list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView resturant_name;
        ImageView resturant_image;
        private LinearLayout category_lienear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            resturant_image=itemView.findViewById(R.id.resturant_image);
            resturant_name=itemView.findViewById(R.id.resturant_name);

        }
    }

}
