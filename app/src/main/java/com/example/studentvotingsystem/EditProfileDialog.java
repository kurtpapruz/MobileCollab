package com.example.studentvotingsystem;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class EditProfileDialog extends Dialog {
    private TextInputEditText nameInput, studentIdInput, sectionInput;
    private ImageButton changePhotoButton;
    private Button saveButton, cancelButton;
    private OnProfileUpdateListener listener;

    public interface OnProfileUpdateListener {
        void onProfileUpdated(String name);
    }

    public EditProfileDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_edit_profile);
        
        // Set dialog window attributes
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            
            // Set margins
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.9);
            window.setAttributes(params);
        }

        initializeViews();
        setupClickListeners();
        loadCurrentData();
    }

    private void initializeViews() {
        nameInput = findViewById(R.id.nameInput);
        studentIdInput = findViewById(R.id.studentIdInput);
        sectionInput = findViewById(R.id.sectionInput);
        changePhotoButton = findViewById(R.id.changePhotoButton);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);
    }

    private void setupClickListeners() {
        changePhotoButton.setOnClickListener(v -> {
            // TODO: Implement photo change functionality
            Toast.makeText(getContext(), "Change photo functionality coming soon", Toast.LENGTH_SHORT).show();
        });

        saveButton.setOnClickListener(v -> {
            String newName = nameInput.getText().toString().trim();
            if (newName.isEmpty()) {
                nameInput.setError("Name cannot be empty");
                return;
            }

            if (listener != null) {
                listener.onProfileUpdated(newName);
            }
            dismiss();
            Toast.makeText(getContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
        });

        cancelButton.setOnClickListener(v -> dismiss());
    }

    private void loadCurrentData() {
        // Load current user data
        nameInput.setText("Papruz, Kurt Russel B.");
        studentIdInput.setText("02000289857");
        sectionInput.setText("BSIT3A");
    }

    public void setOnProfileUpdateListener(OnProfileUpdateListener listener) {
        this.listener = listener;
    }
} 