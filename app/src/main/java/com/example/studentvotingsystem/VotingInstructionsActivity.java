package com.example.studentvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class VotingInstructionsActivity extends AppCompatActivity {
    private LinearLayout homeNav, voteNav ,profileNav;
    private Button nextButton;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_instructions);

        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VotingInstructionsActivity.this, OrgVoting1.class);
                startActivity(intent);
            }
        });

        homeNav = findViewById(R.id.homeNav);
        homeNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VotingInstructionsActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
        voteNav = findViewById(R.id.voteNav);
        voteNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VotingInstructionsActivity.this, VoteActivity.class);
                startActivity(intent);
            }
        });
        profileNav = findViewById(R.id.profileNav);
        profileNav = findViewById(R.id.profileNav);
        profileNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VotingInstructionsActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        ImageView notificationIcon = findViewById(R.id.notificationIcon);
        notificationIcon.setOnClickListener(v -> {
            Intent intent = new Intent(this, NotificationActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });
    }
}