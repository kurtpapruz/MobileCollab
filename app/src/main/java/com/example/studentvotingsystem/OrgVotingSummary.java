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

public class OrgVotingSummary extends AppCompatActivity {
    private LinearLayout homeNav, voteNav, profileNav;
    private Button submitButton;
    private ImageButton backButton;
    private TextView presidentName, vicePresidentName, secretaryName, treasurerName,
            auditorName, businessManagerName, multimedia1Name, multimedia2Name,
            firstYearRepName, secondYearRepName, thirdYearRepName, fourthYearRepName;
    private ImageButton editPresident, editVicePresident, editSecretary, editTreasurer,
            editAuditor, editBusinessManager, editMultimedia1, editMultimedia2,
            editFirstYearRep, editSecondYearRep, editThirdYearRep, editFourthYearRep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_voting_summary);

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
        treasurerName = findViewById(R.id.treasurerName);
        auditorName = findViewById(R.id.auditorName);
        businessManagerName = findViewById(R.id.businessManagerName);
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
        editTreasurer = findViewById(R.id.editTreasurer);
        editAuditor = findViewById(R.id.editAuditor);
        editBusinessManager = findViewById(R.id.editBusinessManager);
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
            Intent intent = new Intent(this, OrgVoting12.class);
            startActivity(intent);
            finish();
        });

        // Submit button
        submitButton.setOnClickListener(v -> showConfirmationDialog());

        // Edit buttons
        editPresident.setOnClickListener(v -> navigateToVoting(1));
        editVicePresident.setOnClickListener(v -> navigateToVoting(2));
        editSecretary.setOnClickListener(v -> navigateToVoting(3));
        editTreasurer.setOnClickListener(v -> navigateToVoting(4));
        editAuditor.setOnClickListener(v -> navigateToVoting(5));
        editBusinessManager.setOnClickListener(v -> navigateToVoting(6));
        editMultimedia1.setOnClickListener(v -> navigateToVoting(7));
        editMultimedia2.setOnClickListener(v -> navigateToVoting(8));
        editFirstYearRep.setOnClickListener(v -> navigateToVoting(9));
        editSecondYearRep.setOnClickListener(v -> navigateToVoting(10));
        editThirdYearRep.setOnClickListener(v -> navigateToVoting(11));
        editFourthYearRep.setOnClickListener(v -> navigateToVoting(12));

        // Position row click listeners
        presidentName.setOnClickListener(v -> navigateToVoting(1));
        vicePresidentName.setOnClickListener(v -> navigateToVoting(2));
        secretaryName.setOnClickListener(v -> navigateToVoting(3));
        treasurerName.setOnClickListener(v -> navigateToVoting(4));
        auditorName.setOnClickListener(v -> navigateToVoting(5));
        businessManagerName.setOnClickListener(v -> navigateToVoting(6));
        multimedia1Name.setOnClickListener(v -> navigateToVoting(7));
        multimedia2Name.setOnClickListener(v -> navigateToVoting(8));
        firstYearRepName.setOnClickListener(v -> navigateToVoting(9));
        secondYearRepName.setOnClickListener(v -> navigateToVoting(10));
        thirdYearRepName.setOnClickListener(v -> navigateToVoting(11));
        fourthYearRepName.setOnClickListener(v -> navigateToVoting(12));

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
        
        presidentName.setText(prefs.getString("president", "No selection"));
        vicePresidentName.setText(prefs.getString("vicePresident", "No selection"));
        secretaryName.setText(prefs.getString("secretary", "No selection"));
        treasurerName.setText(prefs.getString("treasurer", "No selection"));
        auditorName.setText(prefs.getString("auditor", "No selection"));
        businessManagerName.setText(prefs.getString("businessManager", "No selection"));
        multimedia1Name.setText(prefs.getString("multimedia1", "No selection"));
        multimedia2Name.setText(prefs.getString("multimedia2", "No selection"));
        firstYearRepName.setText(prefs.getString("firstYearRep", "No selection"));
        secondYearRepName.setText(prefs.getString("secondYearRep", "No selection"));
        thirdYearRepName.setText(prefs.getString("thirdYearRep", "No selection"));
        fourthYearRepName.setText(prefs.getString("fourthYearRep", "No selection"));
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Proceed to Confirmation")
                .setMessage("Are you ready to proceed to the confirmation step?")
                .setPositiveButton("Proceed", (dialog, which) -> {
                    Intent intent = new Intent(this, OrgVotingConfirmation.class);
                    startActivity(intent);
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void navigateToVoting(int position) {
        // Save current voting data before navigating
        saveCurrentVotingState();
        
        // Navigate to the appropriate voting screen based on position
        Intent intent;
        switch (position) {
            case 1:
                intent = new Intent(this, OrgVoting1.class);
                break;
            case 2:
                intent = new Intent(this, OrgVoting2.class);
                break;
            case 3:
                intent = new Intent(this, OrgVoting3.class);
                break;
            case 4:
                intent = new Intent(this, OrgVoting4.class);
                break;
            case 5:
                intent = new Intent(this, OrgVoting5.class);
                break;
            case 6:
                intent = new Intent(this, OrgVoting6.class);
                break;
            case 7:
                intent = new Intent(this, OrgVoting7.class);
                break;
            case 8:
                intent = new Intent(this, OrgVoting8.class);
                break;
            case 9:
                intent = new Intent(this, OrgVoting9.class);
                break;
            case 10:
                intent = new Intent(this, OrgVoting10.class);
                break;
            case 11:
                intent = new Intent(this, OrgVoting11.class);
                break;
            case 12:
                intent = new Intent(this, OrgVoting12.class);
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
            editor.putString("president", presidentName.getText().toString());
        }
        if (!vicePresidentName.getText().toString().equals("No selection")) {
            editor.putString("vicePresident", vicePresidentName.getText().toString());
        }
        if (!secretaryName.getText().toString().equals("No selection")) {
            editor.putString("secretary", secretaryName.getText().toString());
        }
        if (!treasurerName.getText().toString().equals("No selection")) {
            editor.putString("treasurer", treasurerName.getText().toString());
        }
        if (!auditorName.getText().toString().equals("No selection")) {
            editor.putString("auditor", auditorName.getText().toString());
        }
        if (!businessManagerName.getText().toString().equals("No selection")) {
            editor.putString("businessManager", businessManagerName.getText().toString());
        }
        if (!multimedia1Name.getText().toString().equals("No selection")) {
            editor.putString("multimedia1", multimedia1Name.getText().toString());
        }
        if (!multimedia2Name.getText().toString().equals("No selection")) {
            editor.putString("multimedia2", multimedia2Name.getText().toString());
        }
        if (!firstYearRepName.getText().toString().equals("No selection")) {
            editor.putString("firstYearRep", firstYearRepName.getText().toString());
        }
        if (!secondYearRepName.getText().toString().equals("No selection")) {
            editor.putString("secondYearRep", secondYearRepName.getText().toString());
        }
        if (!thirdYearRepName.getText().toString().equals("No selection")) {
            editor.putString("thirdYearRep", thirdYearRepName.getText().toString());
        }
        if (!fourthYearRepName.getText().toString().equals("No selection")) {
            editor.putString("fourthYearRep", fourthYearRepName.getText().toString());
        }

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
        Intent intent = new Intent(this, OrgVoting12.class);
        startActivity(intent);
        finish();
    }
}