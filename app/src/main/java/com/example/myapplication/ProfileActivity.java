package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    EditText editTextTextPersonnewpassword;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Definitions
        editTextTextPersonnewpassword=findViewById(R.id.confirm_password);
        confirm=findViewById(R.id.confirm);

        //Action on Confirm Button
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String newPassword = editTextTextPersonnewpassword.getText().toString();


                user.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                //Show Success Message
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),"User password updated.",Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }
        });

    }

    private void showPopup() {
        AlertDialog.Builder alert = new AlertDialog.Builder(ProfileActivity.this);
        alert.setMessage("Are you sure?")
                .setPositiveButton("Logout", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {

                        // Last step. Logout function
                        logout();
                    }
                }).setNegativeButton("Cancel", null).create();

        alert.show();
    }

    private void logout() {
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }

}