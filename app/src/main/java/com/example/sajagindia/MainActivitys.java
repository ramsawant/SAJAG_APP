package com.example.sajagindia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sajagindia.screens.StartScreen;
// Remove Firebase imports

import java.util.concurrent.TimeUnit;

public class MainActivitys extends AppCompatActivity {
    EditText enternumber;
    Button getotpbutton;
    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitys_main);

        enternumber = findViewById(R.id.input_mobile_number); // Removed EditText
        getotpbutton = findViewById(R.id.deepak); // Removed Button
        signupButton = findViewById(R.id.omkar);

        ProgressBar progressBar=findViewById(R.id.progressbars_sending_otp);

        getotpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!enternumber.getText().toString().trim().isEmpty()){
                    if ((enternumber.getText().toString().trim()).length() ==10){

                        progressBar.setVisibility(View.VISIBLE);
                        getotpbutton.setVisibility(View.INVISIBLE);

                        // Commented out Firebase related code
                        // PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        //         "+91" + enternumber.getText().toString(),
                        //         60,
                        //         TimeUnit.SECONDS,
                        //         MainActivitys.this,
                        //         new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        //             @Override
                        //             public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        //                 progressBar.setVisibility(View.GONE);
                        //                 getotpbutton.setVisibility(View.VISIBLE);
                        //
                        //             }
                        //
                        //             @Override
                        //             public void onVerificationFailed(@NonNull FirebaseException e) {
                        //                 progressBar.setVisibility(View.GONE);
                        //                 getotpbutton.setVisibility(View.VISIBLE);
                        //                 Toast.makeText(MainActivitys.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        //
                        //             }
                        //
                        //             @Override
                        //             public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        //                 progressBar.setVisibility(View.GONE);
                        //                 getotpbutton.setVisibility(View.VISIBLE);
                        //
                        //                 Intent intent =new Intent(getApplicationContext(),verifyenterotptwo.class);
                        //                 intent.putExtra("mobile",enternumber.getText().toString());
                        //                 intent.putExtra("backendotp",backendotp);
                        //                 startActivity(intent);
                        //
                        //             }
                        //         }
                        // );

                        // Simulating OTP sending
                        simulateOTPSending();
                    }else{
                        Toast.makeText(MainActivitys.this, "Please Enter correct number",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivitys.this, "Enter mobile number",Toast.LENGTH_SHORT).show();

                }
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to SignupActivity when "Sign Up" button is clicked
                Intent intent = new Intent(MainActivitys.this, StartScreen.class);
                startActivity(intent);
            }
        });

    }

    // Simulating OTP sending method
    private void simulateOTPSending() {
        // Simulate OTP sending
        // For example, you can generate a random OTP and proceed to the verification screen
        String backendotp = "123456"; // Sample OTP
        Intent intent = new Intent(getApplicationContext(), dashboard.class);
        intent.putExtra("mobile", enternumber.getText().toString());
        intent.putExtra("backendotp", backendotp);
        startActivity(intent);
    }
}
