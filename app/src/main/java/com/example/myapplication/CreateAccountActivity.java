package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    EditText editTextTextName,editTextTextEmailAddress,editTextTextPassword,editTextTextConfirmPassword;

    Button createAccount_btn;
    RadioButton userType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //Definitions
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        editTextTextName = findViewById(R.id.editTextTextName);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        editTextTextConfirmPassword = findViewById(R.id.editTextTextConfirmPassword);
        userType = findViewById(R.id.userAccount);
        createAccount_btn = findViewById(R.id.createAccount_btn);

        createAccount_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextTextName.getText().toString().isEmpty()){
                    editTextTextName.setError("Required");
                }else {
                    if (editTextTextEmailAddress.getText().toString().isEmpty()){
                        editTextTextEmailAddress.setError("Required");
                    }else {
                        if (editTextTextPassword.getText().toString().isEmpty()){
                            editTextTextPassword.setError("Required");
                        }else {
                            if (editTextTextPassword.getText().toString().equals(editTextTextConfirmPassword.getText().toString())){
                                SignUpUser();
                            }else {
                                editTextTextConfirmPassword.setError("Doesn't Match");
                            }
                        }
                    }
                }
            }
        });

    }

    private void SignUpUser(){
        mAuth.createUserWithEmailAndPassword(editTextTextEmailAddress.getText().toString(), editTextTextPassword.getText().toString())
                .addOnCompleteListener(CreateAccountActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign up success, Add user's info to the Firestore database
                            Map<String ,String> users = new HashMap<>();
                            FirebaseUser user = mAuth.getCurrentUser();

                            users.put("Id",user.getUid());
                            users.put("Name",editTextTextName.getText().toString());
                            users.put("Email",editTextTextEmailAddress.getText().toString());
                            users.put("Type", userType.getText().toString());

                            // Add a new document with a generated ID
                            db.collection("User")
                                    .add(users)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {

                                            Intent intent=new Intent(CreateAccountActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                            finish();

                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                            Toast.makeText(CreateAccountActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });



                        } else {
                            // If sign up fails, display a message to the user.
                            Toast.makeText(CreateAccountActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

}