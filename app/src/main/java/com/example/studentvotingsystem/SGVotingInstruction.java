package com.example.studentvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SGVotingInstruction extends AppCompatActivity {
    private ImageView backButton;
    private Button nextButton;
    private LinearLayout homeNav, voteNav, profileNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgvoting_instruction);

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {
        // Initialize navigation components
        backButton = findViewById(R.id.backButton);
        nextButton = findViewById(R.id.nextButton);
        homeNav = findViewById(R.id.homeNav);
        voteNav = findViewById(R.id.voteNav);
        profileNav = findViewById(R.id.profileNav);
    }

    private void setupListeners() {
        // Back button - returns to previous screen
        backButton.setOnClickListener(v -> {
            onBackPressed();
        });

        // Next button - proceeds to voting screen
        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, SGVoting1.class);
            startActivity(intent);
        });

        // Bottom navigation
        homeNav.setOnClickListener(v -> navigateToActivity(DashboardActivity.class));
        
        voteNav.setOnClickListener(v -> {
            // Already in voting process
            Toast.makeText(this, "Currently in voting process", Toast.LENGTH_SHORT).show();
        });
        
        profileNav.setOnClickListener(v -> navigateToActivity(ProfileActivity.class));
    }

    private void navigateToActivity(Class<?> destinationActivity) {
        Intent intent = new Intent(this, destinationActivity);
        startActivity(intent);
        if (destinationActivity == DashboardActivity.class) {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}