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

public class WriteFeedbackActivity extends AppCompatActivity {

    EditText feedback_txt;

    FirebaseFirestore db;

    Map<String, Object> expincesmap;

    Button submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_feedback);

        feedback_txt=findViewById(R.id.feedback_txt);
        submit_btn=findViewById(R.id.submit_btn);

        db = FirebaseFirestore.getInstance();
        expincesmap=new HashMap<>();

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initial(feedback_txt.getText().toString());

            }
        });



    }

    private void initial(String feed){

        expincesmap.put("Feedback", feed);

        // Add a new Expense in the database
        db.collection("Feedbacks").add(expincesmap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                //Show Success Message


                Toast.makeText(WriteFeedbackActivity.this,"Added successfully",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                //Show Failure Message
                Toast.makeText(WriteFeedbackActivity.this,e.getMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });

    }


}