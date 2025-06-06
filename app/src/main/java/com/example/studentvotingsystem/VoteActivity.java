package com.example.studentvotingsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import java.util.Map;
import java.util.HashMap;

public class VoteActivity extends AppCompatActivity {
    private LinearLayout homeNav, voteNav, profileNav;
    private ImageButton backButton;
    private LinearLayout electionsContainer;
    private static final String BASE_URL = "http://10.0.2.2:8000/api/mobile/elections";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        // Navigation
        homeNav = findViewById(R.id.homeNav);
        homeNav.setOnClickListener(v -> startActivity(new Intent(this, DashboardActivity.class)));

        profileNav = findViewById(R.id.profileNav);
        profileNav.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> startActivity(new Intent(this, DashboardActivity.class)));

        ImageView notificationIcon = findViewById(R.id.notificationIcon);
        notificationIcon.setOnClickListener(v -> {
            Intent intent = new Intent(this, NotificationActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

        electionsContainer = findViewById(R.id.electionsContainer);

        fetchElections();
    }

    private void fetchElections() {
        RequestQueue queue = Volley.newRequestQueue(this);

        SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String token = prefs.getString("auth_token", null);

        if (token == null) {
            Toast.makeText(this, "You must login first", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                response -> {
                    Log.d("ELECTIONS_RESPONSE", response);
                    try {
                        JSONObject responseObject = new JSONObject(response);
                        JSONArray elections = responseObject.getJSONArray("data");

                        for (int i = 0; i < elections.length(); i++) {
                            JSONObject election = elections.getJSONObject(i);
                            String title = election.getString("title");
                            String startDate = election.getString("start_date");
                            String endDate = election.getString("end_date");
                            int id = election.getInt("election_id");

                            addElectionCard(id, title, startDate, endDate);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Parsing error", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(this, "Failed to fetch elections", Toast.LENGTH_SHORT).show();
                }
        ) {
            @Override
            public Map<String, String> getHeaders(){
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };

        queue.add(stringRequest);
    }

    private void addElectionCard(int electionId, String title, String startDate, String endDate) {
        CardView cardView = new CardView(this);
        cardView.setCardBackgroundColor(ContextCompat.getColor(this, R.color.blue));
        cardView.setRadius(32f);
        cardView.setCardElevation(8f);

        int heightInDp = 175;
        float scale = getResources().getDisplayMetrics().density;
        int heightInPx = (int) (heightInDp * scale + 0.5f);

        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                heightInPx
        );
        cardParams.setMargins(0, 0, 0, 24);
        cardView.setLayoutParams(cardParams);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(24, 24, 24, 24);

        TextView titleView = new TextView(this);
        titleView.setText(title);
        titleView.setTextSize(24);
        titleView.setTypeface(null, Typeface.BOLD);
        titleView.setTextColor(ContextCompat.getColor(this, R.color.white));
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        titleParams.setMargins(40, 24, 0, 24);
        titleView.setLayoutParams(titleParams);

        layout.addView(titleView);

        LinearLayout startAndButton = new LinearLayout(this);
        startAndButton.setOrientation(LinearLayout.HORIZONTAL);
        startAndButton.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        startAndButton.setGravity(Gravity.CENTER_VERTICAL);

        LinearLayout datesLayout = new LinearLayout(this);
        datesLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams datesLayoutParams = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
        );
        datesLayout.setLayoutParams(datesLayoutParams);

        String justDateStart = startDate.split(" ")[0];
        TextView startView = new TextView(this);
        startView.setText("Date Start: " + justDateStart);
        startView.setTextColor(ContextCompat.getColor(this, R.color.white));
        startView.setTextSize(16);
        LinearLayout.LayoutParams startParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        startParams.setMargins(40, 0, 0, 0);
        startView.setLayoutParams(startParams);

        String justDateEnd = endDate.split(" ")[0];
        TextView endView = new TextView(this);
        endView.setText("Date End: " + justDateEnd);
        endView.setTextColor(ContextCompat.getColor(this, R.color.white));
        endView.setTextSize(16);
        LinearLayout.LayoutParams endParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        endParams.setMargins(40, 0, 0, 0);
        endView.setLayoutParams(endParams);

        datesLayout.addView(startView);
        datesLayout.addView(endView);

        Button voteButton = new Button(this);
        voteButton.setText("Vote");
        voteButton.setTextColor(ContextCompat.getColor(this, R.color.black));
        voteButton.setTextSize(18);
        voteButton.setTypeface(null, Typeface.BOLD);
        voteButton.setPadding(40, 16, 40, 16);

        GradientDrawable gd = new GradientDrawable();
        gd.setColor(ContextCompat.getColor(this, R.color.yellow));
        gd.setCornerRadius(32f);
        voteButton.setBackground(gd);

        LinearLayout.LayoutParams voteButtonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        voteButtonParams.setMargins(0, 0, 40, 0);
        voteButton.setLayoutParams(voteButtonParams);

        voteButton.setOnClickListener(v -> {
            Intent intent;
            if (title.toLowerCase().contains("student")) {
                intent = new Intent(this, SGVotingInstruction.class);
            } else {
                intent = new Intent(this, VotingInstructionsActivity.class);
            }
            intent.putExtra("election_id", electionId);
            startActivity(intent);
        });

        startAndButton.addView(datesLayout);
        startAndButton.addView(voteButton);

        layout.addView(startAndButton);

        cardView.addView(layout);
        electionsContainer.addView(cardView);
    }
}