package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddnewPurchasesActivity extends AppCompatActivity {

    Button buttonnewpurchases,buttonnewexpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnew_purchases);

        buttonnewpurchases=findViewById(R.id.buttonnewpurchases);

        buttonnewexpenses=findViewById(R.id.buttonnewexpenses);


        buttonnewpurchases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PurchasesActivity.class);
                startActivity(intent);
            }
        });

        buttonnewexpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),EnterExpincesActivity.class);
                startActivity(intent);

            }
        });


    }
}