package com.example.studentvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {
    private static final String TAG = "DashboardActivity";
    private TextView userNameText;
    private Button viewResultsButton;
    private Button voteNowButton;
    private LinearLayout homeNav, voteNav, profileNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        try {
            initializeViews();
            setupClickListeners();
            
            // Set initial text
            userNameText.setText("Kurt!");
            
            // Highlight the current tab
            homeNav.setSelected(true);
        } catch (Exception e) {
            Log.e(TAG, "Error in onCreate: " + e.getMessage());
            Toast.makeText(this, "Error initializing dashboard", Toast.LENGTH_SHORT).show();
        }
    }

    private void initializeViews() {
        userNameText = findViewById(R.id.userName);
        viewResultsButton = findViewById(R.id.viewResultsButton);
        voteNowButton = findViewById(R.id.voteNowButton);
        
        // Initialize bottom navigation
        homeNav = findViewById(R.id.homeNav);
        voteNav = findViewById(R.id.voteNav);
        profileNav = findViewById(R.id.profileNav);

        // Initialize notification icon with click handler
        ImageView notificationIcon = findViewById(R.id.notificationIcon);
        notificationIcon.setOnClickListener(v -> {
            Intent intent = new Intent(this, NotificationActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

        if (userNameText == null || viewResultsButton == null || voteNowButton == null ||
            homeNav == null || voteNav == null || profileNav == null) {
            throw new IllegalStateException("Failed to initialize one or more views");
        }
    }

    private void setupClickListeners() {
        viewResultsButton.setOnClickListener(v -> {
            Toast.makeText(this, "Navigating to Results screen", Toast.LENGTH_SHORT).show();
            navigateToActivity(ResultActivity.class);
        });

        voteNowButton.setOnClickListener(v -> {
            Toast.makeText(this, "Navigating to Vote screen", Toast.LENGTH_SHORT).show();
            navigateToActivity(VoteActivity.class);
        });

        // Bottom navigation
        voteNav.setOnClickListener(v -> {
            Toast.makeText(this, "Navigating to Vote screen", Toast.LENGTH_SHORT).show();
            navigateToActivity(VoteActivity.class);
        });
        
        profileNav.setOnClickListener(v -> {
            Toast.makeText(this, "Navigating to Profile screen", Toast.LENGTH_SHORT).show();
            navigateToActivity(ProfileActivity.class);
        });
        
        homeNav.setOnClickListener(v -> {
            Toast.makeText(this, "Already on Dashboard", Toast.LENGTH_SHORT).show();
        });
    }

    private void navigateToActivity(Class<?> destinationActivity) {
        try {
            if (this.getClass().equals(destinationActivity)) {
                return; // Already in the desired activity
            }
            
            Intent intent = new Intent(DashboardActivity.this, destinationActivity);
            startActivity(intent);
            overridePendingTransition(0, 0); // Disable animation for smoother transition
        } catch (Exception e) {
            Log.e(TAG, "Error navigating to " + destinationActivity.getSimpleName() + ": " + e.getMessage());
            Toast.makeText(this, "Error navigating to next screen", Toast.LENGTH_SHORT).show();
        }
    }

    private void showVotingHistory() {
        VotingHistoryDialog dialog = new VotingHistoryDialog(this);
        dialog.show();
    }
} 