package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.Adapters.ExpensesAdapter;
import com.example.myapplication.Adapters.FeedbacksAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ShowFeedbackActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FeedbacksAdapter adapter;
    private ArrayList<Object> results;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_feedback);

        recyclerView = findViewById(R.id.recyclerview);

        results=new ArrayList<>();

        db = FirebaseFirestore.getInstance();

        getdata();

    }

    private void getdata(){

        db.collection("Feedbacks")
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
        adapter = new FeedbacksAdapter(this, results);
        recyclerView.setAdapter(adapter);
    }

}