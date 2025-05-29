package com.example.studentvotingsystem;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.imageview.ShapeableImageView;

public class CandidateInfoDialog extends Dialog {
    private final Context context;
    private TextView titleText;
    private ShapeableImageView candidateImage;
    private TextView candidateName;
    private TextView courseYear;
    private TextView platformText;
    private Button closeButton;

    public CandidateInfoDialog(Context context) {
        super(context);
        this.context = context;
        
        // Request a window without a title and set the content view
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_candidate_info);
        
        // Initialize views
        initializeViews();
        
        // Set up window attributes
        setupWindow();
        
        // Set up click listeners
        setupClickListeners();
    }

    private void setupWindow() {
        if (getWindow() != null) {
            Window window = getWindow();
            // Set transparent background
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            
            // Get screen width
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (windowManager != null) {
                android.util.DisplayMetrics displayMetrics = new android.util.DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                int screenWidth = displayMetrics.widthPixels;
                
                // Set dialog width to 90% of screen width
                WindowManager.LayoutParams params = window.getAttributes();
                params.width = (int) (screenWidth * 0.9);
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                
                // Center the dialog
                window.setGravity(android.view.Gravity.CENTER);
                
                // Enable dim background
                params.dimAmount = 0.5f;
                window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                
                // Apply the parameters
                window.setAttributes(params);
            }
        }
    }

    private void initializeViews() {
        titleText = findViewById(R.id.titleText);
        candidateImage = findViewById(R.id.candidateImage);
        candidateName = findViewById(R.id.candidateName);
        courseYear = findViewById(R.id.courseYear);
        platformText = findViewById(R.id.platformText);
        closeButton = findViewById(R.id.closeButton);
    }

    private void setupClickListeners() {
        if (closeButton != null) {
            closeButton.setOnClickListener(v -> dismiss());
        }
        
        // Prevent dialog from closing when clicking outside
        setCanceledOnTouchOutside(false);
    }

    public void setData(String position, String name, String course, String year, String platform, int imageResId) {
        if (titleText != null) {
            titleText.setText(position + " Candidate");
        }
        if (candidateName != null) {
            candidateName.setText(name);
        }
        if (courseYear != null) {
            courseYear.setText(course + " - " + year);
        }
        if (platformText != null) {
            platformText.setText(platform);
        }
        if (candidateImage != null && imageResId != 0) {
            candidateImage.setImageResource(imageResId);
        }
    }
} 