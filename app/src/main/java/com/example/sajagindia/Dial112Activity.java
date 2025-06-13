//package com.example.sajagindia;
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//public class Dial112Activity extends AppCompatActivity {
//
//    private static final int REQUEST_CALL_PHONE = 1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dashboard);
//
//        ImageView emergencyCallIcon = findViewById(R.id.emergencyCallIcon);
//        emergencyCallIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Check for CALL_PHONE permission
//                if (ContextCompat.checkSelfPermission(Dial112Activity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
//                    // Permission already granted, make the call
//                    makePhoneCall();
//                } else {
//                    // Request CALL_PHONE permission
//                    ActivityCompat.requestPermissions(Dial112Activity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
//                }
//            }
//        });
//    }
//
//    // Handle permission request result if needed
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        if (requestCode == REQUEST_CALL_PHONE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission granted, make the call
//                makePhoneCall();
//            } else {
//                // Permission denied, show a message or handle accordingly
//            }
//        }
//    }
//
//    private void makePhoneCall() {
//        // Emergency number to dial
//        String phoneNumber = "tel:112";
//
//        // Create an intent with ACTION_CALL to initiate a phone call
//        Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse(phoneNumber));
//
//        // Start the phone call
//        startActivity(dialIntent);
//    }
//}
