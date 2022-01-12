package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsResturantActivity extends AppCompatActivity {

    TextView logout,changepassword;

    EditText editTextTextPersonnewpassword;

    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_resturant);

        logout=findViewById(R.id.logout);
        changepassword=findViewById(R.id.changepassword);
        editTextTextPersonnewpassword=findViewById(R.id.editTextTextPersonnewpassword);
        confirm=findViewById(R.id.confirm);

        editTextTextPersonnewpassword.setVisibility(View.GONE);
        confirm.setVisibility(View.GONE);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String newPassword = editTextTextPersonnewpassword.getText().toString();

                user.updatePassword(newPassword)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),"User password updated.",Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }
        });


        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editTextTextPersonnewpassword.setVisibility(View.VISIBLE);
                confirm.setVisibility(View.VISIBLE);

            }


        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPopup();

            }
        });

    }

    private void showPopup() {
        AlertDialog.Builder alert = new AlertDialog.Builder(SettingsResturantActivity.this);
        alert.setMessage("Are you sure?")
                .setPositiveButton("Logout", new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int which) {

                        logout(); // Last step. Logout function

                    }
                }).setNegativeButton("Cancel", null);

        AlertDialog alert1 = alert.create();
        alert1.show();
    }

    private void logout() {
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }

}