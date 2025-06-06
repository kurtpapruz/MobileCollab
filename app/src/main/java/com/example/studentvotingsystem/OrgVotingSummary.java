package com.example.studentvotingsystem;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.AbsoluteSizeSpan;
import android.graphics.Typeface;
import android.widget.Toast;


public class OrgVotingSummary extends AppCompatActivity {

    private LinearLayout summaryContainer;
    private ImageView backButton;
    private Button submitBtn;
    private LinearLayout homeNav, voteNav, profileNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_voting_summary);

        findViewById(R.id.backButton).setOnClickListener(v -> startActivity(new Intent(this, DashboardActivity.class)));
        findViewById(R.id.notificationIcon).setOnClickListener(v -> {
            startActivity(new Intent(this, NotificationActivity.class));
            overridePendingTransition(0, 0);
        });
        initializeViews();
        setupListeners();

        summaryContainer = findViewById(R.id.summaryContainer);

        Bundle selectedVotes = getIntent().getBundleExtra("selectedVotes");

        if (selectedVotes == null || selectedVotes.isEmpty()) {
            TextView emptyMsg = new TextView(this);
            emptyMsg.setText("No votes selected.");
            summaryContainer.addView(emptyMsg);
            return;
        }

        List<String> positions = new ArrayList<>(selectedVotes.keySet());

        Collections.sort(positions, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.equalsIgnoreCase("President")) return -1;
                if (o2.equalsIgnoreCase("President")) return 1;
                return o1.compareToIgnoreCase(o2);
            }
        });

        for (String position : positions) {
            String candidate = selectedVotes.getString(position);

            String fullText = position + ":        " + candidate;

            SpannableString spannable = new SpannableString(fullText);

            int positionEnd = position.length();
            spannable.setSpan(new StyleSpan(Typeface.BOLD), 0, positionEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannable.setSpan(new AbsoluteSizeSpan(18, true), 0, positionEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            spannable.setSpan(new AbsoluteSizeSpan(16, true), positionEnd, fullText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            TextView voteView = new TextView(this);
            voteView.setText(spannable);
            voteView.setTextColor(0xFF000000);

            // Add bottom margin for spacing
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            int marginInPx = (int) (12 * getResources().getDisplayMetrics().density); // ~12dp
            params.setMargins(0, 0, 0, marginInPx);
            voteView.setLayoutParams(params);

            summaryContainer.addView(voteView);
        }
    }
    private void initializeViews() {
        backButton = findViewById(R.id.backButton);
        submitBtn = findViewById(R.id.submitBtn);
        homeNav = findViewById(R.id.homeNav);
        voteNav = findViewById(R.id.voteNav);
        profileNav = findViewById(R.id.profileNav);
    }
    private void setupListeners() {
        submitBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, OrgVotingConfirmation.class);
            startActivity(intent);
        });

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, OrgVoting1.class);
            startActivity(intent);
            finish();
        });

        homeNav.setOnClickListener(v -> startActivity(new Intent(this, DashboardActivity.class)));

        profileNav.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));

        voteNav.setOnClickListener(v -> startActivity(new Intent(this, VoteActivity.class)));
    }
}
