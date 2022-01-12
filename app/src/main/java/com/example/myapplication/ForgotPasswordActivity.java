package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText editTextTextEmailAddress;
    Button forgot_password_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        editTextTextEmailAddress=findViewById(R.id.editTextTextEmailAddress);
        forgot_password_btn=findViewById(R.id.forgot_password_btn);

        forgot_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get Email
                String email = editTextTextEmailAddress.getText().toString();

                //Check Email Field is empty
                if (email.isEmpty()) {
                    editTextTextEmailAddress.setError("Required");
                } else {
                    ForgotPassword(email);
                }
            }
        });

    }

    private void ForgotPassword(String email) {
        // Reset Password from Firebase
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                //Firebase Success Action
                if (task.isSuccessful()) {
                    //Show Success Message
                    Toast.makeText(ForgotPasswordActivity.this,"Email Sent, check your email",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                    startActivity(i);
                } else {
                    // If Process fails, display a message to the user.
                    Toast.makeText(ForgotPasswordActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}