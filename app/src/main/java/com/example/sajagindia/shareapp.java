package com.example.sajagindia;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

public class shareapp {

    public static void setShareClickListener(final Context context, ImageView shareImageView) {
        shareImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to handle the click event, e.g., initiate a share action
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My App");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Stay informed and stay safe with SAJAG INDIA APP! Get real-time updates on disasters in your area. Download now and spread the word to help others stay prepared. #SAJAG_INDIA App \uD83C\uDF0D\uD83D\uDEA8");
                context.startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });
    }
}
