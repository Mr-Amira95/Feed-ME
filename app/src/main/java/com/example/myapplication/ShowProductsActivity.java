package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.Adapters.FeedbacksAdapter;
import com.example.myapplication.Adapters.OrdersAdapter;
import com.example.myapplication.Adapters.ProductItemAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ShowProductsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductItemAdapter adapter;
    private ArrayList<Object> results;
    private ArrayList<Object> ids;
    FirebaseFirestore db;
    private Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_products);

        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.recyclerview);
        results=new ArrayList<>();
        ids=new ArrayList<>();
        getdata();

        add = findViewById(R.id.button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowProductsActivity.this, AddProductActivity.class);
                startActivity(i);
            }
        });
    }

    private void getdata(){

        db.collection("Product")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                results.add(document.getData());
                                ids.add(document.getId());
                            }
                            setItems();
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }

                    }
                });
    }

    private void setItems() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductItemAdapter(this, results,ids);
        recyclerView.setAdapter(adapter);
    }

}