package com.example.studentvotingsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.textfield.TextInputEditText;

public class SGVotingConfirmation extends AppCompatActivity {
    private LinearLayout homeNav, voteNav, profileNav;
    private Button proceedButton, doneButton;
    private ImageButton backButton;
    private TextInputEditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgvoting_confirmation);
        initializeViews();
        setupListeners();
    }

    private void initializeViews() {
        // Initialize navigation components
        homeNav = findViewById(R.id.homeNav);
        voteNav = findViewById(R.id.voteNav);
        profileNav = findViewById(R.id.profileNav);
        backButton = findViewById(R.id.backButton);
        
        // Initialize buttons
        proceedButton = findViewById(R.id.proceedButton);
        doneButton = findViewById(R.id.doneButton);
        
        // Initialize password input
        passwordInput = findViewById(R.id.passwordInput);
    }

    private void setupListeners() {
        // Back button
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, SGVotingSummary.class);
            startActivity(intent);
            finish();
        });

        // Proceed button
        proceedButton.setOnClickListener(v -> {
            if (validatePassword()) {
                showConfirmationDialog();
            }
        });

        // Done button
        doneButton.setOnClickListener(v -> {
            navigateToActivity(DashboardActivity.class);
        });

        // Bottom navigation
        homeNav.setOnClickListener(v -> navigateToActivity(DashboardActivity.class));
        voteNav.setOnClickListener(v -> {
            // Already in voting process
            Toast.makeText(this, "Currently in voting process", Toast.LENGTH_SHORT).show();
        });
        profileNav.setOnClickListener(v -> navigateToActivity(ProfileActivity.class));
    }

    private boolean validatePassword() {
        String password = passwordInput.getText().toString().trim();
        if (password.isEmpty()) {
            passwordInput.setError("Password is required");
            return false;
        }
        // TODO: Add actual password validation logic
        return true;
    }

    private void showConfirmationDialog() {
        View confirmationView = getLayoutInflater().inflate(R.layout.dialog_vote_confirmed, null);
        AlertDialog dialog = new AlertDialog.Builder(this)
            .setView(confirmationView)
            .setCancelable(true)
            .create();

        // Set up close button click listener
        Button closeButton = confirmationView.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(v -> {
            dialog.dismiss();
            // After confirmation, show the done button and hide proceed button
            if (proceedButton != null && doneButton != null) {
                proceedButton.setVisibility(View.GONE);
                doneButton.setVisibility(View.VISIBLE);
            }
        });

        dialog.show();

        // Save the voting status
        SharedPreferences prefs = getSharedPreferences("VotingData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("sgVotingConfirmed", true); // Changed key to be SG specific
        editor.putLong("sgConfirmationTime", System.currentTimeMillis()); // Changed key to be SG specific
        editor.apply();
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
        Intent intent = new Intent(this, SGVotingSummary.class);
        startActivity(intent);
        finish();
    }
}