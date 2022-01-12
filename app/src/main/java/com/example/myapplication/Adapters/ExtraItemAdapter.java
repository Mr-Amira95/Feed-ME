package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class ExtraItemAdapter extends RecyclerView.Adapter<ExtraItemAdapter.ItemHolder>{

    private Context context;
    private List<Object> cartItemsResults;

    public ExtraItemAdapter(Context context, List<Object> cartItemsResults){
        this.context = context;
        this.cartItemsResults = cartItemsResults;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_extra, viewGroup, false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {
        /*
        CartItemsResults currentItem = cartItemsResults.get(i);
        */

        itemHolder.extra_name.setText(cartItemsResults.get(0).toString().split(",")[i]);

    }

    @Override
    public int getItemCount() {
        try {
            return cartItemsResults.get(0).toString().split(",").length;
        }catch (Exception e){
            return 0;
        }
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        TextView extra_name;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            extra_name=itemView.findViewById(R.id.extra_name);

        }
    }
}
