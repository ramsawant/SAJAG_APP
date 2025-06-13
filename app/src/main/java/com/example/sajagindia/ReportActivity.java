package com.example.sajagindia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sajagindia.screens.HomeScreen;
import com.example.sajagindia.user.settings.AboutScreen;
import com.example.sajagindia.user.settings.FeedbackScreen;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_report);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId(); // Get the item's ID

            if (itemId == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), dashboard.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (itemId == R.id.bottom_alert) {
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (itemId == R.id.bottom_weather) {
                startActivity(new Intent(getApplicationContext(), WeatherActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (itemId == R.id.bottom_report) {
                // ReportActivity is already selected, no action needed.
                return true;
            } else if (itemId == R.id.bottom_profile) {
                startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }

            return false;
        });

        // Get references to the buttons
        Button reportButton = findViewById(R.id.clothingName);
        Button viewButton = findViewById(R.id.elecName);
        Button feedbackButton = findViewById(R.id.homeName);
        Button contactButton = findViewById(R.id.beautyName);

        // Set click event handlers for the buttons
        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the "Report Complaint" button click
                // Add your code here
//                startActivity(new Intent(ReportActivity.this, UploadActivity.class));
                startActivity(new Intent(ReportActivity.this, MainActivity.class));
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the "View Complaints" button click
                // Add your code here
                startActivity(new Intent(ReportActivity.this, MainActivity2.class));
            }
        });

        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the "Feedback" button click
                // Add your code here
                startActivity(new Intent(ReportActivity.this, FeedbackScreen.class));
            }
        });

        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the "Contact Us" button click
                // Add your code here
                startActivity(new Intent(ReportActivity.this, AboutScreen.class));
            }
        });
    }
}
