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

public class VoteActivity extends AppCompatActivity {
    private LinearLayout homeNav, voteNav, profileNav;
    private ImageButton backButton;
    private Button studentGovVoteButton, bitsOfficerVoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        homeNav = findViewById(R.id.homeNav);
        homeNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VoteActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

        profileNav = findViewById(R.id.profileNav);
        profileNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VoteActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        bitsOfficerVoteButton = (Button) findViewById(R.id.bitsOfficerVoteButton);
        bitsOfficerVoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VoteActivity.this, VotingInstructionsActivity.class);
                startActivity(intent);
            }
        });

        studentGovVoteButton = findViewById(R.id.studentGovVoteButton);
        studentGovVoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VoteActivity.this, SGVotingInstruction.class);
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
                Intent intent = new Intent(VoteActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

    }
}