package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.util.SharedPreferencesUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    Button loginbutn;
    EditText editTextTextEmailAddress, editTextTextPassword;
    TextView forgot, createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Definitions
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        loginbutn=findViewById(R.id.loginbutn);
        editTextTextEmailAddress=findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword=findViewById(R.id.editTextTextPassword);
        forgot=findViewById(R.id.forgot);
        createAccount=findViewById(R.id.create_account);


        //Login Button Action
        loginbutn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editTextTextEmailAddress.getText().toString();
                String password = editTextTextPassword.getText().toString();

                //Check Fields are empty
                if (email.isEmpty()){
                    editTextTextEmailAddress.setError("Required");
                }else if (password.isEmpty()){
                    editTextTextPassword.setError("Required");
                }else{
                    signIn(email, password);
                }
            }
        });

        //Forgot Password Action
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(i);

            }
        });

    createAccount.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(i);
        }
    });


    }


    private void check (String id){

        //Check login info for Restaurant Owner or End-User
        db.collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        if (document.getData().get("Id").equals(id)){
                            String accountType = document.getData().get("Type").toString();
                            if (accountType.equals("User")){
                                Intent i = new Intent(LoginActivity.this, MainUserActivity.class);
                                startActivity(i);
                                finish();
                            } else if (accountType.equals("Owner")){
                                Intent i = new Intent(LoginActivity.this, MainResturantActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }
                    }
                } else {
                    // Show Error Message
                    Toast.makeText(LoginActivity.this,task.getException().toString(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void signIn(String email,String password) {

        // Sign In Method from Firebase
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            check(user.getUid().toString());
                        } else {
                            Toast.makeText(getApplicationContext(),"Please Check Info or Sign Up!!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
    }


}