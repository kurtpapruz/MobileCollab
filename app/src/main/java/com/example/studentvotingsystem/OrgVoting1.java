package com.example.studentvotingsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrgVoting1 extends AppCompatActivity {

    private LinearLayout homeNav, profileNav;
    private LinearLayout positionsContainer;
    private int electionId;
    private int orgId;
    private String apiUrl;
    private List<JSONObject> positionList = new ArrayList<>();
    private Map<String, RadioButton> selectedCandidates = new HashMap<>(); // positionName -> selected RadioButton

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_voting1);

        homeNav = findViewById(R.id.homeNav);
        homeNav.setOnClickListener(v -> startActivity(new Intent(this, DashboardActivity.class)));

        profileNav = findViewById(R.id.profileNav);
        profileNav.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));

        findViewById(R.id.backButton).setOnClickListener(v -> startActivity(new Intent(this, DashboardActivity.class)));
        findViewById(R.id.notificationIcon).setOnClickListener(v -> {
            startActivity(new Intent(this, NotificationActivity.class));
            overridePendingTransition(0, 0);
        });

        positionsContainer = findViewById(R.id.positionsContainer);

        electionId = getIntent().getIntExtra("election_id", -1);
        orgId = getIntent().getIntExtra("org_id", -1);

        if (electionId == -1 || orgId == -1) {
            Toast.makeText(this, "Invalid election or organization ID", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        apiUrl = "http://10.0.2.2:8000/api/mobile/elections/" + electionId + "/org/" + orgId + "/positions";

        fetchCandidates();
        Button reviewVotesButton = findViewById(R.id.nextBtn);
        reviewVotesButton.setOnClickListener(v -> {
            Map<String, String> selectedVotes = getSelectedVotes();

            Bundle bundle = new Bundle();
            for (Map.Entry<String, String> entry : selectedVotes.entrySet()) {
                bundle.putString(entry.getKey(), entry.getValue());
            }

            Intent intent = new Intent(this, OrgVotingSummary.class);
            intent.putExtra("selectedVotes", bundle);
            startActivity(intent);
        });

        // Back button setup
        Button backButton = findViewById(R.id.backBtn);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, VotingInstructionsActivity.class);
            startActivity(intent);
            finish(); // optional, to close this activity
        });
    }

    private void fetchCandidates() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String token = sharedPreferences.getString("auth_token", null);

        if (token == null) {
            Toast.makeText(this, "No token found. Please log in again.", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                apiUrl,
                null,
                response -> {
                    try {
                        JSONArray positionsArray = response.getJSONArray("positions");
                        positionList.clear();

                        for (int i = 0; i < positionsArray.length(); i++) {
                            positionList.add(positionsArray.getJSONObject(i));
                        }

                        displayPositionsWithCandidates();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Parsing error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(this, "Failed to fetch positions.", Toast.LENGTH_SHORT).show();
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };

        requestQueue.add(jsonObjectRequest);
    }

    private void displayPositionsWithCandidates() throws JSONException {
        positionsContainer.removeAllViews();
        selectedCandidates.clear(); // Clear old selections when refreshing

        for (JSONObject positionObj : positionList) {
            View positionView = getLayoutInflater().inflate(R.layout.item_position_page, positionsContainer, false);

            TextView positionTitle = positionView.findViewById(R.id.positionTitle);
            LinearLayout candidatesContainer = positionView.findViewById(R.id.candidatesContainer);

            String positionName = positionObj.getString("position_name");
            positionTitle.setText(positionName);

            JSONArray candidates = positionObj.getJSONArray("candidates");

            List<RadioButton> radioButtons = new ArrayList<>();

            for (int i = 0; i < candidates.length(); i++) {
                JSONObject candidate = candidates.getJSONObject(i);
                String candidateName = candidate.getString("name");
                String imagePath = candidate.optString("image_path", "");

                LinearLayout candidateLayout = new LinearLayout(this);
                candidateLayout.setOrientation(LinearLayout.VERTICAL);
                candidateLayout.setGravity(Gravity.CENTER_HORIZONTAL);
                int paddingPx = (int) (8 * getResources().getDisplayMetrics().density);
                candidateLayout.setPadding(paddingPx, paddingPx, paddingPx, paddingPx);

                ImageView imageView = new ImageView(this);
                int size = (int) (100 * getResources().getDisplayMetrics().density);
                LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(size, size);
                imageView.setLayoutParams(imageParams);

                Glide.with(this)
                        .load("http://10.0.2.2:8000/storage/" + imagePath)
                        .placeholder(R.drawable.placeholder)
                        .centerCrop()
                        .into(imageView);

                candidateLayout.addView(imageView);

                RadioButton radioButton = new RadioButton(this);
                radioButton.setId(View.generateViewId());
                radioButton.setText(candidateName);
                radioButton.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);

                candidateLayout.addView(radioButton);

                candidatesContainer.addView(candidateLayout);
                radioButtons.add(radioButton);
            }

            // Enforce single-selection per position and update selectedCandidates map
            for (RadioButton rb : radioButtons) {
                rb.setOnClickListener(v -> {
                    for (RadioButton otherRb : radioButtons) {
                        if (otherRb != rb) otherRb.setChecked(false);
                    }
                    selectedCandidates.put(positionName, rb);
                });
            }

            positionsContainer.addView(positionView);
        }
    }
    private Map<String, String> getSelectedVotes() {
        Map<String, String> votes = new HashMap<>();
        for (JSONObject positionObj : positionList) {
            try {
                String positionName = positionObj.getString("position_name");
                RadioButton selected = selectedCandidates.get(positionName);
                if (selected != null) {
                    votes.put(positionName, selected.getText().toString());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return votes;
    }
}
