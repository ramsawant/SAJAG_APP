package com.example.sajagindia;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.sajagindia.screens.HomeScreen;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WeatherActivity extends AppCompatActivity {

    private PrefManager prefManager;
    private EditText etCity, etCountry, etLat, etLon;
    private CheckBox cod;
    private FusedLocationProviderClient fusedLocationClient;
    private ProgressDialog progress;
    private Boolean checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        prefManager = new PrefManager(this);
        etLat = findViewById(R.id.etLat);
        etLon = findViewById(R.id.etLon);
        cod = findViewById(R.id.cbCod);
        etCity = findViewById(R.id.etCity);
        etCountry = findViewById(R.id.etCountry);
        Button btngeet = findViewById(R.id.btnGet);
        Button btnGps = findViewById(R.id.btnGps);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        progress = new ProgressDialog(this);
        progress.setTitle("Searching");
        progress.setMessage("Wait while Searching...");
        progress.setCancelable(false);

        checked = cod.isChecked();

        btnGps.setOnClickListener(view -> {
            progress.show();
            getLoc();
        });

        btngeet.setOnClickListener(view -> {
            if (etCity.getText().length() > 1 || (checked && etLat.getText().length() > 1)) {
                if (etCity.getText().length() > 1) {
                    String city = etCity.getText().toString().trim();
                    prefManager.putCityName(city);
                }
                if (etCountry.getText().length() > 1) {
                    String country = etCountry.getText().toString().trim();
                    prefManager.putcontry(country);
                }
                if (checked && etLat.getText().length() > 1) {
                    prefManager.putLat(etLat.getText().toString());
                    prefManager.putLon(etLon.getText().toString());
                }
                startActivity(new Intent(WeatherActivity.this, ViewWeather.class));
                finish();
            } else if (!checked) {
                Toast.makeText(getApplicationContext(), "Empty city", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Empty Coordinate", Toast.LENGTH_SHORT).show();
            }
        });

        cod.setOnClickListener(view -> {
            checked = cod.isChecked();
            if (checked) {
                etLat.setVisibility(View.VISIBLE);
                etLon.setVisibility(View.VISIBLE);
                etCity.setVisibility(View.INVISIBLE);
                etCountry.setVisibility(View.INVISIBLE);
                prefManager.putCod(true);
            } else {
                etLat.setVisibility(View.INVISIBLE);
                etLon.setVisibility(View.INVISIBLE);
                etCity.setVisibility(View.VISIBLE);
                etCountry.setVisibility(View.VISIBLE);
                prefManager.putCod(false);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_weather);
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
    }

    public void getLoc() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        fusedLocationClient.getCurrentLocation(100, null).addOnSuccessListener(this, location -> {
            progress.dismiss();
            if (location != null) {
                prefManager.putLat(String.valueOf(location.getLatitude()));
                prefManager.putLon(String.valueOf(location.getLongitude()));
                prefManager.putCod(true);
                Toast.makeText(getApplicationContext(), location.getLatitude() + "  " + location.getLongitude(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(WeatherActivity.this, ViewWeather.class));
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Bad signal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
