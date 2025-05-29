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

public class SGVoting10 extends AppCompatActivity {
    private LinearLayout homeNav, voteNav, profileNav;
    private Button backBtn, nextBtn;
    private ImageButton backButton;
    private RadioButton[] candidateRadioButtons;
    private TextView[] candidateMoreLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgvoting10);

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

        // Initialize "More" links
        candidateMoreLinks = new TextView[4];
        candidateMoreLinks[0] = findViewById(R.id.candidate1More);
        candidateMoreLinks[1] = findViewById(R.id.candidate2More);
        candidateMoreLinks[2] = findViewById(R.id.candidate3More);
        candidateMoreLinks[3] = findViewById(R.id.candidate4More);

        // Make all views clickable
        homeNav.setClickable(true);
        voteNav.setClickable(true);
        profileNav.setClickable(true);
        for (TextView moreLink : candidateMoreLinks) {
            if (moreLink != null) {
                moreLink.setClickable(true);
            }
        }
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

        // Set up click listeners for "More" links
        for (int i = 0; i < candidateMoreLinks.length; i++) {
            final int candidateIndex = i;
            if (candidateMoreLinks[i] != null) {
                candidateMoreLinks[i].setOnClickListener(v -> showCandidateDetails(candidateIndex));
            }
        }

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
        editor.putString("sgBusinessManager", selectedCandidate);  // Key for Business Manager position
        editor.apply();

        // Navigate to the next voting step
        Intent intent = new Intent(this, SGVoting11.class);  // Navigate to next step
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

    private void showCandidateDetails(int candidateIndex) {
        if (candidateRadioButtons[candidateIndex] != null) {
            String candidateName = candidateRadioButtons[candidateIndex].getText().toString();
            
            CandidateInfoDialog dialog = new CandidateInfoDialog(this);
            
            // Set candidate data based on index
            switch (candidateIndex) {
                case 0:
                    dialog.setData(
                        "Assistant Business Manager",
                        candidateName,
                        "BSIT",
                        "3rd Year",
                        "• Develop sustainable business strategies\n" +
                        "• Manage organization resources effectively\n" +
                        "• Create revenue-generating initiatives\n" +
                        "• Establish business partnerships\n" +
                        "• Implement cost-saving measures",
                        R.drawable.candidate_placeholder
                    );
                    dialog.show();
                    break;
                    
                case 1:
                    dialog.setData(
                        "Assistant Business Manager",
                        candidateName,
                        "BSIT",
                        "3rd Year",
                        "• Improve financial management systems\n" +
                        "• Create fundraising opportunities\n" +
                        "• Develop business relationships\n" +
                        "• Enhance resource allocation\n" +
                        "• Implement business best practices",
                        R.drawable.candidate_placeholder
                    );
                    dialog.show();
                    break;
                    
                case 2:
                    dialog.setData(
                        "Assistant Business Manager",
                        candidateName,
                        "BSIT",
                        "3rd Year",
                        "• Platform points will be added here\n" +
                        "• Second point\n" +
                        "• Third point\n" +
                        "• Fourth point\n" +
                        "• Fifth point",
                        R.drawable.candidate_placeholder
                    );
                    dialog.show();
                    break;
                    
                case 3:
                    dialog.setData(
                        "Assistant Business Manager",
                        candidateName,
                        "BSIT",
                        "3rd Year",
                        "• Platform points will be added here\n" +
                        "• Second point\n" +
                        "• Third point\n" +
                        "• Fourth point\n" +
                        "• Fifth point",
                        R.drawable.candidate_placeholder
                    );
                    dialog.show();
                    break;
            }
        }
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
        // Navigate back to the previous voting step
        Intent intent = new Intent(this, SGVoting9.class);  // Changed to previous step
        startActivity(intent);
        finish();
    }
}