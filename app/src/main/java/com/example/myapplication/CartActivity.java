package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.myapplication.Adapters.CartItemAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cartItemRV;
    private CartItemAdapter cartItemAdapter;
    private ArrayList<Object> cartItemsResults;
    FirebaseFirestore db;

    FirebaseAuth auth;

    Button checkout_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //Deffenetions
        cartItemRV = findViewById(R.id.cart_recyclerview);
        checkout_btn=findViewById(R.id.checkout_btn);

        auth=FirebaseAuth.getInstance();

        cartItemsResults=new ArrayList<>();

        db = FirebaseFirestore.getInstance();
        getdata();


    }

    private void getdata(){

        db.collection("Cart")
                .document(auth.getUid().toString()).collection("user_products").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                         if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());

                        cartItemsResults.add(document.getData());

                    }
                    setItems();
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }



    private void setItems() {
        cartItemRV.setLayoutManager(new LinearLayoutManager(this));
        cartItemAdapter = new CartItemAdapter(this, cartItemsResults,checkout_btn);
        cartItemRV.setAdapter(cartItemAdapter);
    }

}