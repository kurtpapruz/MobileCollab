package com.example.studentvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private TextInputEditText emailEditText;
    private TextInputEditText passwordEditText;
    private MaterialButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

            // Validate input fields
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

            // For demo purposes, we'll consider any non-empty input as successful login
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            navigateToDashboard();

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