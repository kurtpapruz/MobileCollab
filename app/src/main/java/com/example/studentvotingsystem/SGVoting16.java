package com.example.studentvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SGVoting16 extends AppCompatActivity {
    private LinearLayout homeNav, voteNav, profileNav;
    private Button backBtn, nextBtn;
    private ImageButton backButton;
    private RadioButton[] candidateRadioButtons;
    private TextView[] candidateMoreLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgvoting1);

        initializeViews();
        setupListeners();
        setupBottomNavigation();
    }

    private void initializeViews() {
        // Initialize navigation components
        homeNav = findViewById(R.id.homeNav);
        voteNav = findViewById(R.id.voteNav);
        profileNav = findViewById(R.id.profileNav);
        backBtn = findViewById(R.id.backBtn);
        nextBtn = findViewById(R.id.nextBtn);
        backButton = findViewById(R.id.backButton);

        // Initialize radio buttons for candidates
        candidateRadioButtons = new RadioButton[4];
        candidateRadioButtons[0] = findViewById(R.id.candidate1Radio);
        candidateRadioButtons[1] = findViewById(R.id.candidate2Radio);
        candidateRadioButtons[2] = findViewById(R.id.candidate3Radio);
        candidateRadioButtons[3] = findViewById(R.id.candidate4Radio);

        // Make all views clickable
        homeNav.setClickable(true);
        voteNav.setClickable(true);
        profileNav.setClickable(true);
    }

    private void setupListeners() {
        // Back button at the top
        backButton.setOnClickListener(v -> onBackPressed());

        // Back button at the bottom
        backBtn.setOnClickListener(v -> onBackPressed());

        // Next button
        nextBtn.setOnClickListener(v -> {
            if (validateSelection()) {
                moveToNextStep();
            } else {
                Toast.makeText(this, "Please select a candidate", Toast.LENGTH_SHORT).show();
            }
        });

        // Set up radio button group behavior
        for (int i = 0; i < candidateRadioButtons.length; i++) {
            final int currentIndex = i;
            if (candidateRadioButtons[i] != null) {
                candidateRadioButtons[i].setOnClickListener(v -> {
                    // Uncheck all other radio buttons
                    for (int j = 0; j < candidateRadioButtons.length; j++) {
                        if (j != currentIndex && candidateRadioButtons[j] != null) {
                            candidateRadioButtons[j].setChecked(false);
                        }
                    }
                });
            }
        }

        // Bottom navigation
        homeNav.setOnClickListener(v -> navigateToActivity(DashboardActivity.class));
        voteNav.setOnClickListener(v -> {
            // Already in voting process
            Toast.makeText(this, "Currently in voting process", Toast.LENGTH_SHORT).show();
        });
        profileNav.setOnClickListener(v -> navigateToActivity(ProfileActivity.class));
    }

    private void setupBottomNavigation() {
        // Set the vote nav as selected
        voteNav.setSelected(true);
    }

    private boolean validateSelection() {
        for (RadioButton radioButton : candidateRadioButtons) {
            if (radioButton != null && radioButton.isChecked()) {
                return true;
            }
        }
        return false;
    }

    private void moveToNextStep() {
        // Save the selected candidate
        String selectedCandidate = getSelectedCandidate();

        // Save to SharedPreferences
        android.content.SharedPreferences prefs = getSharedPreferences("VotingData", MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = prefs.edit();
        editor.putString("sg4thyearRepre", selectedCandidate);
        editor.apply();

        // Navigate to the next voting step
        Intent intent = new Intent(this, SGVotingSummary.class);
        startActivity(intent);
        finish();
    }

    private String getSelectedCandidate() {
        for (int i = 0; i < candidateRadioButtons.length; i++) {
            if (candidateRadioButtons[i] != null && candidateRadioButtons[i].isChecked()) {
                return candidateRadioButtons[i].getText().toString();
            }
        }
        return null;
    }

    private void navigateToActivity(Class<?> destinationActivity) {
        if (this.getClass().equals(destinationActivity)) {
            return; // Already in the desired activity
        }
        Intent intent = new Intent(this, destinationActivity);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Navigate back to the instructions screen
        Intent intent = new Intent(this, SGVoting15.class);
        startActivity(intent);
        finish();
    }
}