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

import com.example.myapplication.Adapters.ExpensesAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class ShowExpensesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ExpensesAdapter adapter;
    private ArrayList<Object> results;

    FirebaseFirestore db;


    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_expenses);

        recyclerView = findViewById(R.id.recyclerview);
        db = FirebaseFirestore.getInstance();

        results=new ArrayList<>();

        getdata();

        add = findViewById(R.id.button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowExpensesActivity.this, AddExpensesActivity.class);
                startActivity(i);
            }
        });

    }

    private void getdata(){

        db.collection("Expenses")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                results.add(document.getData());
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
        adapter = new ExpensesAdapter(this, results);
        recyclerView.setAdapter(adapter);
    }

}