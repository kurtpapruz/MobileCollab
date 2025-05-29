package com.example.studentvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

public class ProfileActivity extends AppCompatActivity {
    private TextView nameText;
    private TextView studentIdText;
    private TextView sectionText;
    private Button votingHistoryButton;
    private Button logoutButton;
    private ImageButton backButton;
    private LinearLayout homeNav, voteNav, profileNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initializeViews();
        setupClickListeners();
        updateProfileInfo();
        
        // Highlight the current tab
        profileNav.setSelected(true);
    }

    private void initializeViews() {
        nameText = findViewById(R.id.nameText);
        studentIdText = findViewById(R.id.studentIdText);
        sectionText = findViewById(R.id.sectionText);
        votingHistoryButton = findViewById(R.id.votingHistoryButton);
        logoutButton = findViewById(R.id.logoutButton);
        backButton = findViewById(R.id.backButton);
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
    }

    private void setupClickListeners() {
        // Set up back button click listener
        backButton.setOnClickListener(v -> handleBackPress());

        votingHistoryButton.setOnClickListener(v -> {
            // TODO: Implement voting history screen navigation
        });

        logoutButton.setOnClickListener(v -> {
            // Show confirmation dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Logout")
                   .setMessage("Are you sure you want to logout?")
                   .setPositiveButton("Yes", (dialog, which) -> {
                       // Navigate to login screen
                       Intent intent = new Intent(this, LoginActivity.class);
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                       startActivity(intent);
                       finish();
                   })
                   .setNegativeButton("No", (dialog, which) -> {
                       dialog.dismiss();
                   })
                   .show();
        });

        // Bottom navigation
        homeNav.setOnClickListener(v -> navigateToActivity(DashboardActivity.class));
        voteNav.setOnClickListener(v -> navigateToActivity(VoteActivity.class));
        profileNav.setOnClickListener(v -> {
            // Already in ProfileActivity, no need to navigate
        });
    }

    private void navigateToActivity(Class<?> destinationActivity) {
        if (this.getClass().equals(destinationActivity)) {
            return; // Already in the desired activity
        }
        
        Intent intent = new Intent(this, destinationActivity);
        // Add flags to prevent multiple instances in the back stack
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        overridePendingTransition(0, 0); // Disable animation for smoother transition
        finish();
    }

    private void updateProfileInfo() {
        // TODO: Get this information from a user session or database
        nameText.setText("Papruz, Kurt Russel B.");
        studentIdText.setText("02000289857");
        sectionText.setText("BSIT3A");
    }

    // Handle back button press
    private void handleBackPress() {
        navigateToActivity(DashboardActivity.class);
    }

    // Override hardware back button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handleBackPress();
    }

    // Override options menu back button (if present)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            handleBackPress();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
} 