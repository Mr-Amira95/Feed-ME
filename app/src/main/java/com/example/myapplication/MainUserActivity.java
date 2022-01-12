package com.example.myapplication;


import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.ExtraItemAdapter;
import com.example.myapplication.Adapters.ProductItemAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.MutableDocument;

import java.util.ArrayList;

public class MainUserActivity extends AppCompatActivity {

    ImageView cart, feedback, logout;
    TextView follow;

    private RecyclerView productItemRV;

    FirebaseFirestore db;

    private ProductItemAdapter productItemAdapter;
    private ArrayList<Object> productItemsResults;
    private ArrayList<Object> productItemsid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        productItemRV=findViewById(R.id.products_recyclerview);
        cart=findViewById(R.id.cart_icon);
        feedback=findViewById(R.id.feedback_icon);
        follow=findViewById(R.id.follow_order);
        logout=findViewById(R.id.logout_icon);

        productItemsResults=new ArrayList<>();
        productItemsid=new ArrayList<>();

        db = FirebaseFirestore.getInstance();

        getdata();

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainUserActivity.this, CartActivity.class);
                startActivity(i);
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainUserActivity.this, WriteFeedbackActivity.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainUserActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainUserActivity.this, FollowOrderActivity.class);
                startActivity(i);
            }
        });
    }

    private void setCartItems() {
        productItemRV.setLayoutManager(new LinearLayoutManager(this));
        productItemAdapter = new ProductItemAdapter(this, productItemsResults,productItemsid);
        productItemRV.setAdapter(productItemAdapter);
    }


    private void getdata(){

        db.collection("Product").get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(TAG, document.getId() + " => " + document.getData());

                            productItemsResults.add(document.getData());
                            productItemsid.add(document.getId());
                        }
                        setCartItems();
                    } else {
                        Log.w(TAG, "Error getting Products.", task.getException());
                    }
                }
            });
    }


}