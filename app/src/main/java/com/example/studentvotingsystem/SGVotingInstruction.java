package com.example.studentvotingsystem;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SGVotingInstruction extends AppCompatActivity {
    private ImageView backButton;
    private Button nextButton;
    private LinearLayout homeNav, voteNav, profileNav;
    private int electionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_instructions);

        electionId = getIntent().getIntExtra("election_id", -1);
        Log.d("DEBUG", "Received election_id = " + electionId);

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {
        backButton = findViewById(R.id.backButton);
        nextButton = findViewById(R.id.nextButton);
        homeNav = findViewById(R.id.homeNav);
        voteNav = findViewById(R.id.voteNav);
        profileNav = findViewById(R.id.profileNav);
    }

    private void setupListeners() {
        backButton.setOnClickListener(v -> {
            onBackPressed();
        });
        int orgId = 2;

        nextButton.setOnClickListener(v -> {
            Log.d("DEBUG", "Next button clicked with election_id = " + electionId);
            Intent intent = new Intent(this, OrgVoting1.class);
            intent.putExtra("election_id", electionId);
            intent.putExtra("org_id", orgId);
            Log.d("DEBUG", "Passing election_id = " + electionId + ", org_id = " + orgId);
            startActivity(intent);
        });

        homeNav.setOnClickListener(v -> navigateToActivity(DashboardActivity.class));
        
        voteNav.setOnClickListener(v -> {
            Toast.makeText(this, "Currently in voting process", Toast.LENGTH_SHORT).show();
        });
        
        profileNav.setOnClickListener(v -> navigateToActivity(ProfileActivity.class));
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
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}