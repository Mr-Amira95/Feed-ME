package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainResturantActivity extends AppCompatActivity {

    Button showUser, showOrders, showProducts, addProduct,
    showExpenses, addExpenses, showPurchases, addPurchases,
    showFeedback, showReports;

    FirebaseAuth auth;

    ImageView profileIcon, logoutIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_resturant);

        auth=FirebaseAuth.getInstance();

        profileIcon = findViewById(R.id.profile_icon);
        logoutIcon = findViewById(R.id.logout_icon);
        showUser = findViewById(R.id.user_interface);
        showOrders = findViewById(R.id.show_orders);
        showProducts = findViewById(R.id.show_products);
        addProduct = findViewById(R.id.add_products);
        showExpenses = findViewById(R.id.show_expenses);
        addExpenses = findViewById(R.id.add_expenses);
        showPurchases = findViewById(R.id.show_purchases);
        addPurchases = findViewById(R.id.add_purchases);
        showFeedback = findViewById(R.id.feedback);
        showReports = findViewById(R.id.reports);

        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainResturantActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });

        logoutIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent i = new Intent(MainResturantActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        addExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainResturantActivity.this, AddExpensesActivity.class);
                startActivity(i);
            }
        });

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainResturantActivity.this, AddProductActivity.class);
                startActivity(i);
            }
        });

        addPurchases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainResturantActivity.this, AddPurchasesActivity.class);
                startActivity(i);
            }
        });

        showUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainResturantActivity.this, MainUserActivity.class);
                startActivity(i);
            }
        });

        showExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainResturantActivity.this, ShowExpensesActivity.class);
                startActivity(i);
            }
        });

        showProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainResturantActivity.this, ShowProductsActivity.class);
                startActivity(i);
            }
        });

        showFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainResturantActivity.this, ShowFeedbackActivity.class);
                startActivity(i);
            }
        });

        showPurchases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainResturantActivity.this, ShowPurchasesActivity.class);
                startActivity(i);
            }
        });

        showOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainResturantActivity.this, ShowOrdersActivity.class);
                startActivity(i);
            }
        });

        showReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainResturantActivity.this, ShowReportsActivity.class);
                startActivity(i);
            }
        });

    }

}