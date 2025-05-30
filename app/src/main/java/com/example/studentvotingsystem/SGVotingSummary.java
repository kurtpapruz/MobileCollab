package com.example.studentvotingsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SGVotingSummary extends AppCompatActivity {
    private LinearLayout homeNav, voteNav, profileNav;
    private Button backBtn, submitBtn;
    private ImageButton backButton;
    private TextView presidentName, vicePresidentName, secretaryName, assistantSecretaryName,
            treasurerName, assistantTreasurerName, auditorName, assistantAuditorName, businessManagerName, assistantBusinessManagerName,
            multimedia1Name, multimedia2Name, firstYearRepName, secondYearRepName, thirdYearRepName, fourthYearRepName;
    private ImageButton editPresident, editVicePresident, editSecretary, editAssistSecretary,
            editTreasurer, editAssistantTreasurer, editAuditor, editAssistantAuditor, editBusinessManager, editAssistantBusinessManager,
            editMultimedia1, editMultimedia2, editFirstYearRep, editSecondYearRep, editThirdYearRep, editFourthYearRep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgvoting_summary);

        initializeViews();
        loadSelectedCandidates();
        setupListeners();
        setupBottomNavigation();
    }

    private void initializeViews() {
        // Initialize navigation components
        homeNav = findViewById(R.id.homeNav);
        voteNav = findViewById(R.id.voteNav);
        profileNav = findViewById(R.id.profileNav);
        backBtn = findViewById(R.id.backBtn);
        submitBtn = findViewById(R.id.submitBtn);
        backButton = findViewById(R.id.backButton);

        // Initialize TextViews for candidate names
        presidentName = findViewById(R.id.presidentName);
        vicePresidentName = findViewById(R.id.vicePresidentName);
        secretaryName = findViewById(R.id.secretaryName);
        assistantSecretaryName = findViewById(R.id.assistantSecretaryName);
        treasurerName = findViewById(R.id.treasurerName);
        assistantTreasurerName = findViewById(R.id.assistantTreasurerName);
        auditorName = findViewById(R.id.auditorName);
        assistantAuditorName = findViewById(R.id.assistantAuditorName);
        businessManagerName = findViewById(R.id.businessManagerName);
        assistantBusinessManagerName = findViewById(R.id.AssistantBusinessManager);
        multimedia1Name = findViewById(R.id.multimedia1Name);
        multimedia2Name = findViewById(R.id.multimedia2Name);
        firstYearRepName = findViewById(R.id.firstYearRepName);
        secondYearRepName = findViewById(R.id.secondYearRepName);
        thirdYearRepName = findViewById(R.id.thirdYearRepName);
        fourthYearRepName = findViewById(R.id.fourthYearRepName);

        // Initialize edit buttons
        editPresident = findViewById(R.id.editPresident);
        editVicePresident = findViewById(R.id.editVicePresident);
        editSecretary = findViewById(R.id.editSecretary);
        editAssistSecretary = findViewById(R.id.editAssistSecretary);
        editTreasurer = findViewById(R.id.editTreasurer);
        editAssistantTreasurer = findViewById(R.id.editAssistantTreasurer);
        editAuditor = findViewById(R.id.editAuditor);
        editAssistantAuditor = findViewById(R.id.editAssistantAuditor);
        editBusinessManager = findViewById(R.id.editBusinessManager);
        editAssistantBusinessManager = findViewById(R.id.editAssistantBusinessManager);
        editMultimedia1 = findViewById(R.id.editMultimedia1);
        editMultimedia2 = findViewById(R.id.editMultimedia2);
        editFirstYearRep = findViewById(R.id.editFirstYearRep);
        editSecondYearRep = findViewById(R.id.editSecondYearRep);
        editThirdYearRep = findViewById(R.id.editThirdYearRep);
        editFourthYearRep = findViewById(R.id.editFourthYearRep);


        // Make all views clickable
        homeNav.setClickable(true);
        voteNav.setClickable(true);
        profileNav.setClickable(true);
    }

    private void loadSelectedCandidates() {
        SharedPreferences prefs = getSharedPreferences("VotingData", MODE_PRIVATE);
        
        // Load each selected candidate
        String president = prefs.getString("sgPresident", "No selection");
        presidentName.setText(president);

        String vicePresident = prefs.getString("sgVicePresident", "No selection");
        vicePresidentName.setText(vicePresident);

        String secretary = prefs.getString("sgSecretary", "No selection");
        secretaryName.setText(secretary);

        String assistantSecretary = prefs.getString("sgAssistantSecretary", "No selection");
        assistantSecretaryName.setText(assistantSecretary);

        String treasurer = prefs.getString("sgTreasurer", "No selection");
        treasurerName.setText(treasurer);

        String assistantTreasurer = prefs.getString("sgAssistantTreasurer", "No selection");
        assistantTreasurerName.setText(assistantTreasurer);

        String auditor = prefs.getString("sgAuditor", "No selection");
        auditorName.setText(auditor);

        String assistantAuditor = prefs.getString("sgAssistantAuditor", "No selection");
        assistantAuditorName.setText(assistantAuditor);

        String businessManager = prefs.getString("sgBusinessManager", "No selection");
        businessManagerName.setText(businessManager);

        String assistantBusinessManager = prefs.getString("sgAssistantBusinessManager", "No selection");
        assistantBusinessManagerName.setText(assistantBusinessManager);

        String multimedia1 = prefs.getString("sgMultimedia1", "No selection");
        multimedia1Name.setText(multimedia1);

        String multimedia2 = prefs.getString("sgMultimedia2", "No selection");
        multimedia2Name.setText(multimedia2);

        String firstYearRep = prefs.getString("sg1styearRepre", "No selection");
        firstYearRepName.setText(firstYearRep);

        String secondYearRep = prefs.getString("sg2ndyearRepre", "No selection");
        secondYearRepName.setText(secondYearRep);

        String thirdYearRep = prefs.getString("sg3rdyearRepre", "No selection");
        thirdYearRepName.setText(thirdYearRep);

        String fourthYearRep = prefs.getString("sg4thyearRepre", "No selection");
        fourthYearRepName.setText(fourthYearRep);

        // Check if all positions have selections
        boolean allSelected = !president.equals("No selection") &&
                            !vicePresident.equals("No selection") &&
                            !secretary.equals("No selection") &&
                            !assistantSecretary.equals("No selection") &&
                            !treasurer.equals("No selection") &&
                            !assistantTreasurer.equals("No selection") &&
                            !auditor.equals("No selection") &&
                            !assistantAuditor.equals("No selection") &&
                            !businessManager.equals("No selection") &&
                            !assistantBusinessManager.equals("No selection") &&
                            !multimedia1.equals("No selection") &&
                            !multimedia2.equals("No selection") &&
                            !firstYearRep.equals("No selection") &&
                            !secondYearRep.equals("No selection") &&
                            !thirdYearRep.equals("No selection") &&
                            !fourthYearRep.equals("No selection");

        // Enable submit button only if all positions are selected
        submitBtn.setEnabled(allSelected);
        if (!allSelected) {
            submitBtn.setAlpha(0.5f);
        } else {
            submitBtn.setAlpha(1.0f);
        }
    }

    private void setupListeners() {
        // Back button at the top
        backButton.setOnClickListener(v -> onBackPressed());

        // Back button at the bottom
        backBtn.setOnClickListener(v -> onBackPressed());

        // Submit button
        submitBtn.setOnClickListener(v -> {
            // Save the votes and show confirmation
            Toast.makeText(this, "Votes submitted successfully!", Toast.LENGTH_SHORT).show();
            
            // Clear the voting data
            SharedPreferences prefs = getSharedPreferences("VotingData", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.apply();

            // Navigate to the dashboard
            navigateToActivity(DashboardActivity.class);
        });

        // Edit buttons for each position
        editPresident.setOnClickListener(v -> navigateToActivity(SGVoting1.class));
        editVicePresident.setOnClickListener(v -> navigateToActivity(SGVoting2.class));
        editSecretary.setOnClickListener(v -> navigateToActivity(SGVoting3.class));
        editAssistSecretary.setOnClickListener(v -> navigateToActivity(SGVoting4.class));
        editTreasurer.setOnClickListener(v -> navigateToActivity(SGVoting5.class));
        editAssistantTreasurer.setOnClickListener(v -> navigateToActivity(SGVoting6.class));
        editAuditor.setOnClickListener(v -> navigateToActivity(SGVoting7.class));
        editAssistantAuditor.setOnClickListener(v -> navigateToActivity(SGVoting8.class));
        editBusinessManager.setOnClickListener(v -> navigateToActivity(SGVoting9.class));
        editAssistantBusinessManager.setOnClickListener(v -> navigateToActivity(SGVoting10.class));
        editMultimedia1.setOnClickListener(v -> navigateToActivity(SGVoting11.class));
        editMultimedia2.setOnClickListener(v -> navigateToActivity(SGVoting12.class));
        editFirstYearRep.setOnClickListener(v -> navigateToActivity(SGVoting13.class));
        editSecondYearRep.setOnClickListener(v -> navigateToActivity(SGVoting14.class));
        editThirdYearRep.setOnClickListener(v -> navigateToActivity(SGVoting15.class));
        editFourthYearRep.setOnClickListener(v -> navigateToActivity(SGVoting16.class));

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

    private void navigateToActivity(Class<?> destinationActivity) {
        if (this.getClass().equals(destinationActivity)) {
            return; // Already in the desired activity
        }
        Intent intent = new Intent(this, destinationActivity);
        // Add fromSummary flag for voting activities
        if (destinationActivity.getSimpleName().startsWith("SGVoting") && 
            !destinationActivity.getSimpleName().equals("SGVotingSummary")) {
            intent.putExtra("fromSummary", true);
        }
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Navigate back to the last voting screen
        Intent intent = new Intent(this, SGVoting8.class);
        startActivity(intent);
        finish();
    }
}