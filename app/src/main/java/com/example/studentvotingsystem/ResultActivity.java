package com.example.studentvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private LinearLayout homeNav, voteNav, profileNav;
    private ImageButton backButton;
    private Button studentGovResultButton, bitsOfficerResultButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        homeNav = findViewById(R.id.homeNav);
        homeNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

        profileNav = findViewById(R.id.profileNav);
        profileNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        bitsOfficerResultButton = (Button) findViewById(R.id.OrgOfficerResultButton);
        bitsOfficerResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, OrgResults.class);
                startActivity(intent);
            }
        });

        studentGovResultButton = findViewById(R.id.studentGovResultButton);
        studentGovResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, SGResults.class);
                startActivity(intent);
            }
        });

        ImageView notificationIcon = findViewById(R.id.notificationIcon);
        notificationIcon.setOnClickListener(v -> {
            Intent intent = new Intent(this, NotificationActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

        // Initialize voteNav
        voteNav = findViewById(R.id.voteNav);
        voteNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, VoteActivity.class);
                startActivity(intent);
            }
        });
    }
} 