package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class EnterExpincesActivity extends AppCompatActivity {


    Button submitex_btn;

    EditText editTextTextPersondateex,editTextTextPersonsupdesc,editTextTextPersonvalue;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Map<String, Object> expincesmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterexpinces);

        submitex_btn=findViewById(R.id.submitex_btn);

        editTextTextPersondateex=findViewById(R.id.editTextTextPersondateex);
        editTextTextPersonsupdesc=findViewById(R.id.editTextTextPersonsupdesc);
        editTextTextPersonvalue=findViewById(R.id.editTextTextPersonvalue);

        submitex_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expincesmap.put("Date",editTextTextPersondateex.getText().toString());
                expincesmap.put("Description",editTextTextPersonsupdesc.getText().toString());
                expincesmap.put("Value",editTextTextPersonvalue.getText().toString());


                // Add a new document with a generated ID
                db.collection("Expenses")
                        .add(expincesmap)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {

                                Toast.makeText(getApplicationContext(),"Added successfully",Toast.LENGTH_LONG).show();


                                Log.d("1", "DocumentSnapshot added with ID: " + documentReference.getId());

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Log.w("2", "Error adding document", e);

                            }
                        });

            }
        });


    }
}