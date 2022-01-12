package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class OrdersItemAdapter extends RecyclerView.Adapter<OrdersItemAdapter.ItemHolder>{

    private Context context;
    private List<Object> ordersResults;

    public OrdersItemAdapter(Context context, List<Object> ordersResults){
        this.context = context;
        this.ordersResults = ordersResults;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_order_item, viewGroup, false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {
        /*
        CartItemsResults currentItem = cartItemsResults.get(i);
        */
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ItemHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
