package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PurchasesAdapter extends RecyclerView.Adapter<PurchasesAdapter.ItemHolder>{

    private Context context;
    private List<Object> purchasesResults;
    private ArrayList<Object> itemsResults;

    public PurchasesAdapter(Context context, List<Object> purchasesResults, ArrayList<Object> itemsResults){
        this.context = context;
        this.purchasesResults = purchasesResults;
        this.itemsResults = itemsResults;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_purchase, viewGroup, false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {

        itemHolder.Date.setText(((HashMap) itemsResults.get(i)).get("Date").toString());
        itemHolder.amount.setText(((HashMap) itemsResults.get(i)).get("price total").toString());
        itemHolder.suppliername.setText(((HashMap) itemsResults.get(i)).get("Supplier name").toString());


        setItems(itemHolder.recyclerView);
    }

    private void setItems(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        PurchaseItemAdapter adapter = new PurchaseItemAdapter(context, itemsResults);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return purchasesResults.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;

        TextView Date,amount,suppliername;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            recyclerView = itemView.findViewById(R.id.purchase_item_recyclerview);

            Date=itemView.findViewById(R.id.Date);
            amount=itemView.findViewById(R.id.amount);
            suppliername=itemView.findViewById(R.id.suppliername);

        }
    }
}
