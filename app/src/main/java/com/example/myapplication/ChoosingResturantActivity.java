package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ChoosingResturantActivity extends AppCompatActivity {

    Button buttonnewpurch,buttonnewproduct;

    ImageView imageView_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_resturant);

        buttonnewpurch=findViewById(R.id.buttonnewpurch);
        buttonnewproduct=findViewById(R.id.buttonnewproduct);
        imageView_settings=findViewById(R.id.imageView_settings);

        imageView_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),SettingsResturantActivity.class);
                startActivity(intent);

            }
        });


        buttonnewproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),MainResturantActivity.class);
                startActivity(intent);

            }
        });

        buttonnewpurch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),AddnewPurchasesActivity.class);
                startActivity(intent);


            }
        });


    }
}