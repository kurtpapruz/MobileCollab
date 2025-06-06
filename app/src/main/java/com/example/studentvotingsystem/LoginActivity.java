package com.example.studentvotingsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

//VOLLEY
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import org.json.JSONException;

import com.android.volley.AuthFailureError;
import java.util.Map;
import java.util.HashMap;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private TextInputEditText emailEditText;
    private TextInputEditText passwordEditText;
    private MaterialButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedPref = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String savedToken = sharedPref.getString("auth_token", null);

        if (savedToken != null) {
            // Token exists, user already logged in
            // Go directly to Dashboard or main activity
            navigateToDashboard();
            finish(); // Optional: close login activity so user can't go back to it
        }

        try {
            // Initialize views
            emailEditText = findViewById(R.id.emailEditText);
            passwordEditText = findViewById(R.id.passwordEditText);
            loginButton = findViewById(R.id.loginButton);

            if (emailEditText == null || passwordEditText == null || loginButton == null) {
                Log.e(TAG, "Failed to initialize one or more views");
                Toast.makeText(this, "Error initializing app", Toast.LENGTH_SHORT).show();
                return;
            }

            // Set up login button click listener
            loginButton.setOnClickListener(v -> attemptLogin());
            
        } catch (Exception e) {
            Log.e(TAG, "Error in onCreate: " + e.getMessage());
            Toast.makeText(this, "Error initializing app", Toast.LENGTH_SHORT).show();
        }
    }

    private void attemptLogin() {
        try {
            String email = emailEditText.getText() != null ? emailEditText.getText().toString().trim() : "";
            String password = passwordEditText.getText() != null ? passwordEditText.getText().toString().trim() : "";

            Log.d(TAG, "Attempting login with email: " + email);

            if (TextUtils.isEmpty(email)) {
                emailEditText.setError("Email is required");
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                passwordEditText.setError("Password is required");
                Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
                return;
            }

            String url = "http://10.0.2.2:8000/api/mobile/login";

            RequestQueue queue = Volley.newRequestQueue(this);

            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        try {
                            Log.d("LoginResponse", response);

                            JSONObject jsonResponse = new JSONObject(response);
                            JSONObject user = jsonResponse.getJSONObject("user");
                            String name = user.getString("name");
                            String emailResp = user.getString("email");

                            String token = jsonResponse.getString("token");

                            Log.d(TAG, "Login successful. Token: " + token);

                            SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("auth_token", token);
                            editor.apply();

                            Toast.makeText(this, "Welcome " + name, Toast.LENGTH_SHORT).show();

                            navigateToDashboard();

                        } catch (JSONException e) {
                            Log.e(TAG, "JSON parsing error: " + e.getMessage());
                            Toast.makeText(this, "Unexpected response format", Toast.LENGTH_SHORT).show();
                        }
                    },
                    error -> {
                        if (error.networkResponse != null && error.networkResponse.data != null) {
                            String errorMsg = new String(error.networkResponse.data);
                            Log.e(TAG, "Login error: " + errorMsg);
                        } else {
                            Log.e(TAG, "Login error: " + error.toString());
                        }
                        Toast.makeText(this, "Login failed. Check email or password.", Toast.LENGTH_SHORT).show();
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("email", email);
                    params.put("password", password);
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Accept", "application/json");
                    return headers;
                }
            };

            queue.add(postRequest);

        } catch (Exception e) {
            Log.e(TAG, "Error in attemptLogin: " + e.getMessage());
            Toast.makeText(this, "Login failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void navigateToDashboard() {
        try {
            Log.d(TAG, "Navigating to Dashboard");
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish(); // This prevents going back to login screen when pressing back
        } catch (Exception e) {
            Log.e(TAG, "Error navigating to Dashboard: " + e.getMessage());
            Toast.makeText(this, "Error opening Dashboard", Toast.LENGTH_LONG).show();
        }
    }
} 