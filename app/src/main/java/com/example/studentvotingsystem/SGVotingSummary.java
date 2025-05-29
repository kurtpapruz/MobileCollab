package com.example.studentvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import android.content.SharedPreferences;

public class SGVotingSummary extends AppCompatActivity {
    private LinearLayout homeNav, voteNav, profileNav;
    private Button submitButton;
    private ImageButton backButton;
    private TextView presidentName, vicePresidentName, secretaryName, assistantSecretaryName,
            treasurerName, AssistanTreasurerName, auditorName, AssistantAuditorName,
            businessManagerName, AssistantBusinessManagerName, multimedia1Name, multimedia2Name,firstYearRepName, secondYearRepName,
            thirdYearRepName, fourthYearRepName;
    private ImageButton editPresident, editVicePresident, editSecretary, editAssistSecretary,
            editTreasurer, editAssistantTreasurer, editAuditor, editAssistantAuditor, editBusinessManager,
            editAssistantBusinessManager, editMultimedia1, editMultimedia2, editFirstYearRep, editSecondYearRep,
            editThirdYearRep, editFourthYearRep ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgvoting_summary);

        initializeViews();
        setupListeners();
        loadVotingData();
    }

    private void initializeViews() {
        // Initialize navigation components
        homeNav = findViewById(R.id.homeNav);
        voteNav = findViewById(R.id.voteNav);
        profileNav = findViewById(R.id.profileNav);
        backButton = findViewById(R.id.backButton);
        submitButton = findViewById(R.id.submitBtn);

        // Initialize name TextViews
        presidentName = findViewById(R.id.presidentName);
        vicePresidentName = findViewById(R.id.vicePresidentName);
        secretaryName = findViewById(R.id.secretaryName);
        assistantSecretaryName = findViewById(R.id.AssistantsSecretaryName);
        treasurerName = findViewById(R.id.treasurerName);
        AssistanTreasurerName = findViewById(R.id.AssistanTreasurerName);
        auditorName = findViewById(R.id.auditorName);
        AssistantAuditorName = findViewById(R.id.AssistantAuditorName);
        businessManagerName = findViewById(R.id.businessManagerName);
        AssistantBusinessManagerName = findViewById(R.id.AssistantBusinessManagerName);
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
        editBusinessManager = findViewById(R.id.editBusinessManager);
        editAssistantBusinessManager = findViewById(R.id.editAssistantBusinessManager);
        editMultimedia1 = findViewById(R.id.editMultimedia1);
        editMultimedia2 = findViewById(R.id.editMultimedia2);
        editFirstYearRep = findViewById(R.id.editFirstYearRep);
        editSecondYearRep = findViewById(R.id.editSecondYearRep);
        editThirdYearRep = findViewById(R.id.editThirdYearRep);
        editFourthYearRep = findViewById(R.id.editFourthYearRep);
    }

    private void setupListeners() {
        // Back button
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, SGVoting16.class);
            startActivity(intent);
            finish();
        });

        // Submit button
        submitButton.setOnClickListener(v -> showConfirmationDialog());

        // Edit buttons
        editPresident.setOnClickListener(v -> navigateToVoting(1));
        editVicePresident.setOnClickListener(v -> navigateToVoting(2));
        editSecretary.setOnClickListener(v -> navigateToVoting(3));
        editAssistSecretary.setOnClickListener(v -> navigateToVoting(4));
        editTreasurer.setOnClickListener(v -> navigateToVoting(5));
        editAssistantTreasurer.setOnClickListener(v -> navigateToVoting(6));
        editAuditor.setOnClickListener(v -> navigateToVoting(7));
        editAssistantAuditor.setOnClickListener(v -> navigateToVoting(8));
        editBusinessManager.setOnClickListener(v -> navigateToVoting(9));
        editAssistantBusinessManager.setOnClickListener(v -> navigateToVoting(10));
        editMultimedia1.setOnClickListener(v -> navigateToVoting(11));
        editMultimedia2.setOnClickListener(v -> navigateToVoting(12));
        editFirstYearRep.setOnClickListener(v -> navigateToVoting(13));
        editSecondYearRep.setOnClickListener(v -> navigateToVoting(14));
        editThirdYearRep.setOnClickListener(v -> navigateToVoting(15));
        editFourthYearRep.setOnClickListener(v -> navigateToVoting(16));


        // Position row click listeners
        presidentName.setOnClickListener(v -> navigateToVoting(1));
        vicePresidentName.setOnClickListener(v -> navigateToVoting(2));
        secretaryName.setOnClickListener(v -> navigateToVoting(3));
        assistantSecretaryName.setOnClickListener(v -> navigateToVoting(4));
        treasurerName.setOnClickListener(v -> navigateToVoting(5));
        AssistanTreasurerName.setOnClickListener(v -> navigateToVoting(6));
        auditorName.setOnClickListener(v -> navigateToVoting(7));
        AssistantAuditorName.setOnClickListener(v -> navigateToVoting(8));
        businessManagerName.setOnClickListener(v -> navigateToVoting(9));
        AssistantBusinessManagerName.setOnClickListener(v -> navigateToVoting(10));
        multimedia1Name.setOnClickListener(v -> navigateToVoting(11));
        multimedia2Name.setOnClickListener(v -> navigateToVoting(12));
        firstYearRepName.setOnClickListener(v -> navigateToVoting(13));
        secondYearRepName.setOnClickListener(v -> navigateToVoting(14));
        thirdYearRepName.setOnClickListener(v -> navigateToVoting(15));
        fourthYearRepName.setOnClickListener(v -> navigateToVoting(16));



        // Bottom navigation
        homeNav.setOnClickListener(v -> navigateToActivity(DashboardActivity.class));
        voteNav.setOnClickListener(v -> {
            // Already in voting process
            Toast.makeText(this, "Currently in voting process", Toast.LENGTH_SHORT).show();
        });
        profileNav.setOnClickListener(v -> navigateToActivity(ProfileActivity.class));
    }

    private void loadVotingData() {
        // Load the selected candidates from SharedPreferences
        android.content.SharedPreferences prefs = getSharedPreferences("VotingData", MODE_PRIVATE);
        
        presidentName.setText(prefs.getString("sgPresident", "No selection"));
        vicePresidentName.setText(prefs.getString("sgVicePresident", "No selection"));
        secretaryName.setText(prefs.getString("sgSecretary", "No selection"));
        assistantSecretaryName.setText(prefs.getString("sgAssistantSecretary", "No selection"));
        treasurerName.setText(prefs.getString("sgTreasurer", "No selection"));
        AssistanTreasurerName.setText(prefs.getString("sgAssistantTreasurer", "No selection"));
        auditorName.setText(prefs.getString("sgAuditor", "No selection"));
        AssistantAuditorName.setText(prefs.getString("sgPRO", "No selection"));
        AssistantBusinessManagerName.setText(prefs.getString("sgPIO", "No selection"));
        businessManagerName.setText(prefs.getString("sgBusinessManager", "No selection"));
        multimedia1Name.setText(prefs.getString("sgPeaceOfficer", "No selection"));
        multimedia2Name.setText(prefs.getString("sgPeaceOfficer", "No selection"));
        firstYearRepName.setText(prefs.getString("sgFirstYearRep", "No selection"));
        secondYearRepName.setText(prefs.getString("sgSecondYearRep", "No selection"));
        thirdYearRepName.setText(prefs.getString("sgThirdYearRep", "No selection"));
        fourthYearRepName.setText(prefs.getString("sgFourthYearRep", "No selection"));

    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Proceed to Confirmation")
                .setMessage("Are you ready to proceed to the confirmation step?")
                .setPositiveButton("Proceed", (dialog, which) -> {
                    Intent intent = new Intent(this, SGVotingConfirmation.class);
                    startActivity(intent);
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void navigateToVoting(int position) {
        // Save current voting data before navigating
        saveCurrentVotingState();
        
        // Navigate to the appropriate voting screen based on position
        Intent intent;
        switch (position) {
            case 1:
                intent = new Intent(this, SGVoting1.class);
                break;
            case 2:
                intent = new Intent(this, SGVoting2.class);
                break;
            case 3:
                intent = new Intent(this, SGVoting3.class);
                break;
            case 4:
                intent = new Intent(this, SGVoting4.class);
                break;
            case 5:
                intent = new Intent(this, SGVoting5.class);
                break;
            case 6:
                intent = new Intent(this, SGVoting6.class);
                break;
            case 7:
                intent = new Intent(this, SGVoting7.class);
                break;
            case 8:
                intent = new Intent(this, SGVoting8.class);
                break;
            case 9:
                intent = new Intent(this, SGVoting9.class);
                break;
            case 10:
                intent = new Intent(this, SGVoting10.class);
                break;
            case 11:
                intent = new Intent(this, SGVoting11.class);
                break;
            case 12:
                intent = new Intent(this, SGVoting12.class);
                break;
            case 13:
                intent = new Intent(this, SGVoting13.class);
                break;
            case 14:
                intent = new Intent(this, SGVoting14.class);
                break;
            case 15:
                intent = new Intent(this, SGVoting15.class);
                break;
            case 16:
                intent = new Intent(this, SGVoting16.class);
                break;
            default:
                return;
        }
        startActivity(intent);
    }

    private void saveCurrentVotingState() {
        SharedPreferences prefs = getSharedPreferences("VotingData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // Save all current selections
        if (!presidentName.getText().toString().equals("No selection")) {
            editor.putString("sgPresident", presidentName.getText().toString());
        }
        if (!vicePresidentName.getText().toString().equals("No selection")) {
            editor.putString("sgVicePresident", vicePresidentName.getText().toString());
        }
        if (!secretaryName.getText().toString().equals("No selection")) {
            editor.putString("sgSecretary", secretaryName.getText().toString());
        }
        if (!assistantSecretaryName.getText().toString().equals("No selection")) {
            editor.putString("sgAssistantSecretary", assistantSecretaryName.getText().toString());
        }
        if (!treasurerName.getText().toString().equals("No selection")) {
            editor.putString("sgTreasurer", treasurerName.getText().toString());
        }
        if (!AssistanTreasurerName.getText().toString().equals("No selection")) {
            editor.putString("sgAssistantTreasurer", AssistanTreasurerName.getText().toString());
        }
        if (!auditorName.getText().toString().equals("No selection")) {
            editor.putString("sgAuditor", auditorName.getText().toString());
        }
        if (!AssistantAuditorName.getText().toString().equals("No selection")) {
            editor.putString("sgPRO", AssistantAuditorName.getText().toString());
        }
        if (!businessManagerName.getText().toString().equals("No selection")) {
            editor.putString("sgBusinessManager", businessManagerName.getText().toString());
        }
        if (!AssistantBusinessManagerName.getText().toString().equals("No selection")) {
            editor.putString("sgPIO", AssistantBusinessManagerName.getText().toString());
        }
        if (!multimedia1Name.getText().toString().equals("No selection")) {
            editor.putString("sgPeaceOfficer", multimedia1Name.getText().toString());
        }
        if (!multimedia2Name.getText().toString().equals("No selection")) {
            editor.putString("sgPeaceOfficer", multimedia2Name.getText().toString());
        }
        if (!firstYearRepName.getText().toString().equals("No selection")) {
            editor.putString("sgFirstYearRep", firstYearRepName.getText().toString());
        }
        if (!secondYearRepName.getText().toString().equals("No selection")) {
            editor.putString("sgSecondYearRep", secondYearRepName.getText().toString());
        }
        if (!thirdYearRepName.getText().toString().equals("No selection")) {
            editor.putString("sgThirdYearRep", thirdYearRepName.getText().toString());
        }
        if (!fourthYearRepName.getText().toString().equals("No selection")) {
            editor.putString("sgFourthYearRep", fourthYearRepName.getText().toString());
        }

        editor.apply();
    }

    private void navigateToActivity(Class<?> destinationActivity) {
        Intent intent = new Intent(this, destinationActivity);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, SGVoting16.class);
        startActivity(intent);
        finish();
    }
}