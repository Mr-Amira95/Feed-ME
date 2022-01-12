package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.Adapters.CartItemAdapter;
import com.example.myapplication.Adapters.ExtraItemAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductActivity extends AppCompatActivity {

    private RecyclerView extraItemRV;
    private ExtraItemAdapter extraItemAdapter;
    private ArrayList<Object> extraItemsResults;

    ImageView product_image;

    TextView product_name,product_price,product_desc;

    Button addToCart_btn;

    EditText qty;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> addcartmap;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Intent intent = getIntent();

        product_name=findViewById(R.id.product_name);
        product_image=findViewById(R.id.product_image);
        product_price=findViewById(R.id.product_price);
        product_desc=findViewById(R.id.product_desc);
        addToCart_btn=findViewById(R.id.addToCart_btn);
        qty=findViewById(R.id.qty);

        auth=FirebaseAuth.getInstance();
        addcartmap=new HashMap<>();

        extraItemsResults=new ArrayList<>();

        extraItemsResults.add(intent.getSerializableExtra("component"));

        product_name.setText(intent.getStringExtra("name"));
        product_desc.setText(intent.getStringExtra("description"));
        product_price.setText(intent.getStringExtra("price"));

        try {
            Glide.with(getApplicationContext()).load(intent.getStringExtra("image")).into(product_image);
        }catch (Exception e){
            e.printStackTrace();
        }

        addToCart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Quantity = qty.getText().toString();
                if (Quantity.isEmpty()){
                    qty.setError("Required");
                }else{
                    addtocart(intent.getStringExtra("productid"),qty.getText().toString());
                }
            }
        });

        //Deffenetions
        extraItemRV = findViewById(R.id.extras_recyclerview);
        setExtraItems();
    }

    private void addtocart(String product_id, String Quantity){

        //Add Values in the hash map to add in the Firebase Database

        addcartmap.put("product_id",product_id);
        addcartmap.put("Quantity",Quantity);

        // Add a new Product in the database
        db.collection("Cart").document(auth.getUid()).collection("user_products").add(addcartmap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"Product added to cart successfully",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }


    private void setExtraItems() {
        extraItemRV.setLayoutManager(new LinearLayoutManager(this));
        extraItemAdapter = new ExtraItemAdapter(this, extraItemsResults);
        extraItemRV.setAdapter(extraItemAdapter);
    }

}