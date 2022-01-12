package com.example.myapplication.Adapters;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ItemHolder>{

    private Context context;
    private List<Object> cartItemsResults;
    FirebaseFirestore db;
    Button check;
    Map<String, Object> addproductmap;

    public CartItemAdapter(Context context, List<Object> cartItemsResults,Button check){
        this.context = context;
        this.cartItemsResults = cartItemsResults;
        this.check=check;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_cart, viewGroup, false);
        db = FirebaseFirestore.getInstance();
        addproductmap=new HashMap<>();
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              addcheckout  ((String) ((HashMap) cartItemsResults).get("productid"));

            }
        });

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {

        getdata((String) ((HashMap) cartItemsResults.get(i)).get("productid"));

    }

    private void addcheckout(String orderid){

        //Add Values in the hash map to add in the Firebase Database

        addproductmap.put("orderid",orderid);

        // Add a new Product in the database
        db.collection("Order").add(addproductmap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                //Show Success Message
                Toast.makeText(context,"Checkout added successfully",Toast.LENGTH_LONG).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        //Show Failure Message
                        Toast.makeText(context,"Product not added successfully",Toast.LENGTH_LONG).show();
                    }
                });
    }



    private void getdata(String id){

       /* db.collection("Product").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                task.getResult();
            }
        });*/


        FirebaseFirestore.getInstance().collection("Product")
                .whereIn(FieldPath.documentId(), Collections.singletonList(id)).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                        /*YourClass object = document.toObject(YourClass.class);*/
                        document.getData();
                        // add to your custom list
                    }
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });


    }

    @Override
    public int getItemCount() {
        return cartItemsResults.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{

        TextView product_name,product_price;
        ImageView product_image;
        EditText product_qty;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            product_name=itemView.findViewById(R.id.product_name);
            product_price=itemView.findViewById(R.id.product_price);
            product_qty=itemView.findViewById(R.id.product_qty);
            product_image=itemView.findViewById(R.id.product_image);

        }
    }
}
