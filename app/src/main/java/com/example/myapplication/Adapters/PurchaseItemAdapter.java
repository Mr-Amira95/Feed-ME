package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.HashMap;
import java.util.List;

public class PurchaseItemAdapter extends RecyclerView.Adapter<PurchaseItemAdapter.ItemHolder>{

    private Context context;
    private List<Object> purchasesItemResults;

    public PurchaseItemAdapter(Context context, List<Object> purchasesItemResults){
        this.context = context;
        this.purchasesItemResults = purchasesItemResults;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_purchase_item, viewGroup, false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {

        itemHolder.purchase_item_name.setText(((HashMap) purchasesItemResults.get(i)).get("item name").toString());
        itemHolder.purchase_item_price.setText(((HashMap) purchasesItemResults.get(i)).get("Item Price").toString());
        itemHolder.purchase_item_qty.setText(((HashMap) purchasesItemResults.get(i)).get("Quantity").toString());

    }

    @Override
    public int getItemCount() {
        return purchasesItemResults.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        TextView purchase_item_name,purchase_item_price,purchase_item_qty;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            purchase_item_name=itemView.findViewById(R.id.purchase_item_name);
            purchase_item_price=itemView.findViewById(R.id.purchase_item_price);
            purchase_item_qty=itemView.findViewById(R.id.purchase_item_qty);

        }
    }
}
