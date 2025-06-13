package com.example.sajagindia.user.settings;

import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.sajagindia.Models.FeedbackModel;
import com.example.sajagindia.R;
import com.example.sajagindia.databinding.ActivityFeedbackBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class FeedbackScreen extends AppCompatActivity {
    ActivityFeedbackBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFeedbackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        View v = binding.feedbackActivityRoot;

        // Initialize Firebase Auth and Database
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

        int modeFlag = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (modeFlag) {
            case Configuration.UI_MODE_NIGHT_YES:
                setUiAsNightMode();
                break;
            case Configuration.UI_MODE_NIGHT_NO:
            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                setUiAsLightMode();
                break;
        }

        binding.submitBtn.setOnClickListener(view -> {
            String feedbackText = binding.feedbackText.getText().toString();
            if (!TextUtils.isEmpty(feedbackText)) {
                FeedbackModel model = new FeedbackModel(mAuth.getUid(), feedbackText);
                mDatabase.getReference("user").child("feedbacks")
                        .push() // Generate unique key for each feedback entry
                        .setValue(model)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Snackbar.make(v, "Thanks for feedback", Snackbar.LENGTH_SHORT).show();
                                binding.feedbackText.setText("");
                            } else {
                                Snackbar.make(v, "Failed to submit feedback", Snackbar.LENGTH_SHORT).show();
                            }
                        });
            } else {
                Snackbar.make(v, "Please enter feedback", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    void setUiAsNightMode() {
        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.arrow_back);
        assert upArrow != null;
        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle("Create your account");

        binding.feedbackText.setBackgroundResource(R.drawable.search_user_detailed_contains_night_mode_theme);
    }

    void setUiAsLightMode() {
        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.arrow_back);
        assert upArrow != null;
        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>Feedback</font>"));
    }
}
