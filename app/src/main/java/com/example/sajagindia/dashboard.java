package com.example.sajagindia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.sajagindia.screens.HomeScreen;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ImageSlider imageSlider = findViewById(R.id.imageslider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel (R.drawable.poster1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.poster, ScaleTypes.FIT));
        slideModels.add(new SlideModel (R.drawable.pposterr, ScaleTypes. FIT));
        slideModels.add(new SlideModel (R.drawable.pposters, ScaleTypes.FIT));
        slideModels.add(new SlideModel (R.drawable.poster5, ScaleTypes.FIT));
        imageSlider.setImageList (slideModels, ScaleTypes.FIT);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);

        ImageView rightImageView = findViewById(R.id.rightImageView);
        shareapp.setShareClickListener(this, rightImageView);

        ImageView midImageView = findViewById(R.id.midImageView);
        midImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to handle the click event, e.g., open the XML file
                Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
                startActivity(intent);
                // If you want to add transitions:
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId(); // Get the item's ID

            if (itemId == R.id.bottom_home) {
                // Handle the case for R.id.bottom_home
                return true;
            } else if (itemId == R.id.bottom_alert) {
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (itemId == R.id.bottom_weather) {
                startActivity(new Intent(getApplicationContext(), ViewWeather.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (itemId == R.id.bottom_report) {
                startActivity(new Intent(getApplicationContext(), ReportActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (itemId == R.id.bottom_profile) {
                startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            return false;
        });

        Button survivalButton = findViewById(R.id.Survival);
        Button qNameButton = findViewById(R.id.QName);
        Button clothingImageButton = findViewById(R.id.clothingName);
        Button elecImageButton = findViewById(R.id.elecName);

        // Set click event handlers for the buttons
        survivalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the "Report Complaint" button click
                // Add your code here
                startActivity(new Intent(dashboard.this, MainActivity4.class));
            }
        });

        qNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the "View Complaints" button click
                // Add your code here
                startActivity(new Intent(dashboard.this, ListActivity.class));

            }
        });

        clothingImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the "Feedback" button click
                // Add your code here
                startActivity(new Intent(dashboard.this, HomeScreen.class));

            }
        });

        elecImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the "Contact Us" button click
                // Add your code here
                startActivity(new Intent(dashboard.this, Faq1.class));
            }
        });
    }
}
