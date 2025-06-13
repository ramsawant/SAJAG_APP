package com.example.sajagindia.screens;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.sajagindia.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitpage);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        AppBarConfiguration appbar = new AppBarConfiguration.Builder(
                R.id.home2 , R.id.search , R.id.profile
        ).build();

        NavController navcontroll = Navigation.findNavController(this , R.id.navHostFragment);
        NavigationUI.setupActionBarWithNavController(this , navcontroll , appbar);
        NavigationUI.setupWithNavController(bottomNavigation , navcontroll);

        actionbar();

    }


    void actionbar(){
        ActionBar Abar = getSupportActionBar();
        Abar.hide();
    }

}