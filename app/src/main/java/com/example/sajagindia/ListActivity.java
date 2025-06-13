package com.example.sajagindia;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Add the ListFragment to the activity
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new ListFragment())
                .commit();
    }
}
