package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.Adapters.FeedbacksAdapter;
import com.example.myapplication.Adapters.OrdersAdapter;

import java.util.ArrayList;

public class ShowOrdersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrdersAdapter adapter;
    private ArrayList<Object> results;
    private ArrayList<Object> itemsResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_orders);

        recyclerView = findViewById(R.id.recyclerview);
        setItems();
    }

    private void setItems() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OrdersAdapter(this, results, itemsResults);
        recyclerView.setAdapter(adapter);
    }
}