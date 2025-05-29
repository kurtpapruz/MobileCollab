package com.example.studentvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SGHistoryDetails extends AppCompatActivity {
    private ImageButton backButton;
    private ImageView notificationIcon;
    private LinearLayout homeNav, voteNav, profileNav;
    private TextView electionTitle, electionDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sg_history_details);

        initializeViews();
        setupClickListeners();
        loadElectionDetails();
    }

    private void initializeViews() {
        // Initialize all views
        backButton = findViewById(R.id.backButton);
        notificationIcon = findViewById(R.id.notificationIcon);
        homeNav = findViewById(R.id.homeNav);
        voteNav = findViewById(R.id.voteNav);
        profileNav = findViewById(R.id.profileNav);
        electionTitle = findViewById(R.id.electionTitle);
        electionDate = findViewById(R.id.electionDate);
    }

    private void setupClickListeners() {
        // Back button click listener
        backButton.setOnClickListener(v -> {
            finish(); // Close this activity and return to previous screen
            overridePendingTransition(0, 0); // Disable animation
        });

        // Notification icon click listener
        notificationIcon.setOnClickListener(v -> {
            Intent intent = new Intent(this, NotificationActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

        // Bottom navigation click listeners
        homeNav.setOnClickListener(v -> navigateToActivity(DashboardActivity.class));
        voteNav.setOnClickListener(v -> navigateToActivity(VoteActivity.class));
        profileNav.setOnClickListener(v -> navigateToActivity(ProfileActivity.class));
    }

    private void navigateToActivity(Class<?> destinationActivity) {
        if (this.getClass().equals(destinationActivity)) {
            return; // Already in the desired activity
        }
        
        Intent intent = new Intent(this, destinationActivity);
        // Add flags to prevent multiple instances in the back stack
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        overridePendingTransition(0, 0); // Disable animation
        finish();
    }

    private void loadElectionDetails() {
        // Get election details from intent if passed
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString("election_title", "Student Government Election");
            String date = extras.getString("election_date", "04/12/2025");
            
            electionTitle.setText(title);
            electionDate.setText(date);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0, 0); // Disable animation
    }
}