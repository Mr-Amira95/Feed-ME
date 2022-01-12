package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class AddExpensesActivity extends AppCompatActivity {


    Button submitex_btn;
    EditText editTextTextPersondateex,editTextTextPersonsupdesc,editTextTextPersonvalue;
    FirebaseFirestore db;

    Map<String, Object> expincesmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expinces);

        //Definitions
        db = FirebaseFirestore.getInstance();
        submitex_btn=findViewById(R.id.submitex_btn);
        editTextTextPersondateex=findViewById(R.id.editTextdate);
        editTextTextPersonsupdesc=findViewById(R.id.editTextTextPersonsupdesc);
        editTextTextPersonvalue=findViewById(R.id.editTextTextPersonvalue);

        expincesmap=new HashMap<>();


        //Action on Submit Button
        submitex_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Add Values in the hash map to add in the Firebase Datebase
                expincesmap.put("Date", editTextTextPersondateex.getText().toString());
                expincesmap.put("Description", editTextTextPersonsupdesc.getText().toString());
                expincesmap.put("Value", editTextTextPersonvalue.getText().toString());

                // Add a new Expense in the database
                db.collection("Expenses").add(expincesmap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        //Show Success Message

                        startActivity(new Intent(getApplicationContext(),MainResturantActivity.class));
                        finish();

                        Toast.makeText(AddExpensesActivity.this,"Added successfully",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        //Show Failure Message
                        Toast.makeText(AddExpensesActivity.this,e.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}