package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ItemHolder>{

    private Context context;
    private List<Object> ordersResults;
    private List<Object> ordersItemResults;

    public OrdersAdapter(Context context, List<Object> ordersResults, List<Object> ordersItemResults){
        this.context = context;
        this.ordersResults = ordersResults;
        this.ordersItemResults = ordersItemResults;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_order, viewGroup, false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {
        /*
        CartItemsResults currentItem = cartItemsResults.get(i);
        */

        setItems(itemHolder.recyclerView);

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    private void setItems(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        OrdersItemAdapter adapter = new OrdersItemAdapter(context, ordersItemResults);
        recyclerView.setAdapter(adapter);
    }

    public class ItemHolder extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            recyclerView = itemView.findViewById(R.id.order_item_recyclerview);

        }
    }
}
