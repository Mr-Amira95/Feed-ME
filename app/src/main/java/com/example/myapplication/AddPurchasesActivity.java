package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddPurchasesActivity extends AppCompatActivity {


    Button submit_btn;
    EditText editTextTextPersondate,editTextTextPersonsupName,editTextTextPersontotal,editTextTextPersonitem,editTextTextPersonprice,editTextTextPersonNamequantity;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Map<String, Object> purchasesmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purchases);

        //Definitions
        submit_btn=findViewById(R.id.submit_btn);
        editTextTextPersondate=findViewById(R.id.editTextTextPersondate);
        editTextTextPersonsupName=findViewById(R.id.editTextTextPersonsupName);
        editTextTextPersontotal=findViewById(R.id.editTextTextPersontotal);
        editTextTextPersonitem=findViewById(R.id.editTextTextPersonitem);
        editTextTextPersonprice=findViewById(R.id.editTextTextPersonprice);
        editTextTextPersonNamequantity=findViewById(R.id.editTextTextPersonNamequantity);


        purchasesmap=new HashMap<>();

        //Action on Submit Button
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Add Values in the hash map to add in the Firebase Datebase
                purchasesmap.put("Date",editTextTextPersondate.getText().toString());
                purchasesmap.put("Supplier name",editTextTextPersonsupName.getText().toString());
                purchasesmap.put("price total",editTextTextPersontotal.getText().toString());
                purchasesmap.put("item name",editTextTextPersonitem.getText().toString());
                purchasesmap.put("Item Price",editTextTextPersonprice.getText().toString());
                purchasesmap.put("Quantity",editTextTextPersonNamequantity.getText().toString());

                // Add a new Purchase in the database
                db.collection("Purchases")
                        .add(purchasesmap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {

                                //Show Success Message
                                Toast.makeText(AddPurchasesActivity.this,"Added successfully",Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                //Show Failure Message
                                Toast.makeText(AddPurchasesActivity.this, e.getMessage().toString(),Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });

    }
}