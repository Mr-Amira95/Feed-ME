package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ItemHolder>{

    private Context context;
    private ArrayList<Object> expensesResults;

    public ExpensesAdapter(Context context, ArrayList<Object> expensesResults){
        this.context = context;
        this.expensesResults = expensesResults;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_expenses, viewGroup, false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {

        itemHolder.amount.setText(((HashMap) expensesResults.get(i)).get("Value").toString());
        itemHolder.Date.setText(((HashMap) expensesResults.get(i)).get("Date").toString());
        itemHolder.description.setText(((HashMap) expensesResults.get(i)).get("Description").toString());

    }

    @Override
    public int getItemCount() {
        try {
            return expensesResults.size();

        }catch (Exception e){
            return 0;
        }
    }
    public class ItemHolder extends RecyclerView.ViewHolder{

        TextView Date,amount,description;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            Date=itemView.findViewById(R.id.Date);
            amount=itemView.findViewById(R.id.amount);
            description=itemView.findViewById(R.id.description);

        }
    }
}
