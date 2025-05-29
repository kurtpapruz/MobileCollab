package com.example.studentvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class OrgResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_results);

        // Set up done button
        Button doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(v -> {
            Intent intent = new Intent(OrgResults.this, DashboardActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // Set up bottom navigation
        LinearLayout homeNav = findViewById(R.id.homeNav);
        LinearLayout voteNav = findViewById(R.id.voteNav);
        LinearLayout profileNav = findViewById(R.id.profileNav);

        homeNav.setOnClickListener(v -> {
            Intent intent = new Intent(OrgResults.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        });

        voteNav.setOnClickListener(v -> {
            Intent intent = new Intent(OrgResults.this, VoteActivity.class);
            startActivity(intent);
            finish();
        });

        profileNav.setOnClickListener(v -> {
            Intent intent = new Intent(OrgResults.this, ProfileActivity.class);
            startActivity(intent);
            finish();
        });
    }
}