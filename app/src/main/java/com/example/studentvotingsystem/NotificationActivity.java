package com.example.studentvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {
    private RecyclerView notificationRecyclerView;
    private NotificationAdapter notificationAdapter;
    private LinearLayout homeNav, voteNav, profileNav;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        initializeViews();
        setupRecyclerView();
        setupClickListeners();
    }

    private void initializeViews() {
        notificationRecyclerView = findViewById(R.id.notificationRecyclerView);
        homeNav = findViewById(R.id.homeNav);
        voteNav = findViewById(R.id.voteNav);
        profileNav = findViewById(R.id.profileNav);
        backButton = findViewById(R.id.backButton);
    }

    private void setupRecyclerView() {
        notificationAdapter = new NotificationAdapter(getNotifications());
        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        notificationRecyclerView.setAdapter(notificationAdapter);
    }

    private void setupClickListeners() {
        backButton.setOnClickListener(v -> finish());

        homeNav.setOnClickListener(v -> navigateToActivity(DashboardActivity.class));
        voteNav.setOnClickListener(v -> navigateToActivity(VoteActivity.class));
        profileNav.setOnClickListener(v -> navigateToActivity(ProfileActivity.class));
    }

    private List<NotificationItem> getNotifications() {
        List<NotificationItem> notifications = new ArrayList<>();
        
        // Most recent notifications first
        notifications.add(new NotificationItem(
            "New Vote Cast Confirmation",
            "Your vote for Student Government Election has been successfully recorded. Thank you for participating!",
            "Just now",
            NotificationItem.TYPE_REMINDER
        ));

        notifications.add(new NotificationItem(
            "COMSOC Election Now Live",
            "The Computer Society Officer Election is now open for voting. Click here to cast your vote.",
            "10 minutes ago",
            NotificationItem.TYPE_ELECTION_START
        ));

        notifications.add(new NotificationItem(
            "Student Council Results Published",
            "The results for Student Council Election 2024 are now available. Juan Dela Cruz has been elected as President.",
            "2 hours ago",
            NotificationItem.TYPE_RESULTS
        ));

        notifications.add(new NotificationItem(
            "Last Call for BITS Election",
            "Only 1 hour remaining to cast your vote for BITS Officers Election 2024. Don't miss your chance to vote!",
            "5 hours ago",
            NotificationItem.TYPE_REMINDER
        ));

        notifications.add(new NotificationItem(
            "SSC Election Closed",
            "The Supreme Student Council Election 2024 has ended. Results will be announced within 24 hours.",
            "Yesterday, 11:59 PM",
            NotificationItem.TYPE_ELECTION_END
        ));

        notifications.add(new NotificationItem(
            "Vote Verification Required",
            "Please verify your student ID to participate in upcoming Department Representative elections.",
            "Yesterday, 3:30 PM",
            NotificationItem.TYPE_REMINDER
        ));

        notifications.add(new NotificationItem(
            "BALANI Election Results",
            "Maria Santos has been elected as BALANI President with 1,234 votes (45% of total votes).",
            "2 days ago",
            NotificationItem.TYPE_RESULTS
        ));

        notifications.add(new NotificationItem(
            "Election Schedule Update",
            "The IT Department Representative election has been rescheduled to next week Monday.",
            "3 days ago",
            NotificationItem.TYPE_REMINDER
        ));

        return notifications;
    }

    private void navigateToActivity(Class<?> destinationActivity) {
        if (this.getClass().equals(destinationActivity)) {
            return;
        }
        Intent intent = new Intent(this, destinationActivity);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
} 