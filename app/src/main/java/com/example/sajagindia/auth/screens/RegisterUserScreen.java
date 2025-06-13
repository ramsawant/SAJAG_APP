package com.example.sajagindia.auth.screens;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sajagindia.Models.UserModel;
import com.example.sajagindia.constants.NetworkFunctions;
import com.example.sajagindia.databinding.ActivityRegisterUserBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterUserScreen extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private ActivityRegisterUserBinding binding;
    private boolean connectivity;
    private DatabaseReference usersReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        usersReference = FirebaseDatabase.getInstance().getReference("user").child("UserInfo");

        View v = binding.registerScreenRoot;

        binding.registerUserBtn.setOnClickListener(view -> {
            String email = binding.takeUserEmailId.getText().toString().trim();
            String password = binding.takeUserPassword.getText().toString().trim();
            String username = binding.takeUserName.getText().toString().trim();

            connectivity = new NetworkFunctions().checkNetworkStatus(this);

            if (connectivity) {
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(username)) {
                    Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() <= 7) {
                    Toast.makeText(this, "Your password must be at least 8 characters long", Toast.LENGTH_SHORT).show();
                    return;
                }

                usersReference.orderByChild("usName").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Toast.makeText(RegisterUserScreen.this, "Username already exists", Toast.LENGTH_SHORT).show();
                        } else {
                            registerNewUser(email, password, username);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(RegisterUserScreen.this, "Failed to read data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Snackbar.make(v, "Check your internet connection", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void registerNewUser(String email, String password, String username) {
        binding.progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String userId = firebaseAuth.getCurrentUser().getUid();
                        UserModel userModel = new UserModel(email, password, username);
                        usersReference.child(userId).setValue(userModel)
                                .addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()) {
                                        Toast.makeText(RegisterUserScreen.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegisterUserScreen.this, LoginUserScreen.class));
                                        finish();
                                    } else {
                                        Toast.makeText(RegisterUserScreen.this, "Failed to save user data", Toast.LENGTH_SHORT).show();
                                    }
                                    binding.progressBar.setVisibility(View.GONE);
                                });
                    } else {
                        Toast.makeText(RegisterUserScreen.this, "Failed to register user: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        binding.progressBar.setVisibility(View.GONE);
                    }
                });
    }
}
