package com.example.myapplication.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.myapplication.ProductActivity;
import com.example.myapplication.R;
import com.example.myapplication.ShowFeedbackActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.ItemHolder>{

    private Context context;
    private ArrayList<Object> results ;
    private ArrayList<Object> ids;

    public ProductItemAdapter(Context context, ArrayList<Object> results,ArrayList<Object> ids ){
        this.context = context;
        this.results = results;
        this.ids=ids;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_product, viewGroup, false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, @SuppressLint("RecyclerView") int i) {

        itemHolder.product_price.setText(((HashMap) results.get(i)).get("price").toString());
        itemHolder.product_name.setText(((HashMap) results.get(i)).get("name").toString());

        try {
            Glide.with(context).load(((HashMap) results.get(i)).get("imageproduct").toString()).into(itemHolder.product_image);
        }catch (Exception e){
            e.printStackTrace();
        }



        itemHolder.productLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("image",String.valueOf(((HashMap) results.get(i)).get("imageproduct").toString()));
                intent.putExtra("name",((HashMap) results.get(i)).get("name").toString());
                intent.putExtra("price",((HashMap) results.get(i)).get("price").toString());
                intent.putExtra("description",((HashMap) results.get(i)).get("description").toString());
                intent.putExtra("component",((HashMap) results.get(i)).get("Components").toString());
                intent.putExtra("productid", ids.get(i).toString());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{

        LinearLayout productLayout;
        TextView product_name,product_price;
        ImageView product_image;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            productLayout = itemView.findViewById(R.id.product_layout);

            product_name=itemView.findViewById(R.id.product_name);
            product_price=itemView.findViewById(R.id.product_price);
            product_image=itemView.findViewById(R.id.product_image);
        }
    }
}
