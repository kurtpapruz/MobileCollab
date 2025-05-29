package com.example.studentvotingsystem;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;

public class VotingHistoryDialog extends Dialog {

    private Context context;

    public VotingHistoryDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_voting_history);

        // Set up click listeners for history items
        LinearLayout bitsHistory1 = findViewById(R.id.bitsHistory1);
        LinearLayout sgHistory1 = findViewById(R.id.sgHistory1);
        LinearLayout bitsHistory2 = findViewById(R.id.bitsHistory2);
        LinearLayout sgHistory2 = findViewById(R.id.sgHistory2);

        bitsHistory1.setOnClickListener(v -> {
            Intent intent = new Intent(context, OrgHistoryDetails.class);
            intent.putExtra("category", "BITS Officer");
            intent.putExtra("date", "15/10/2024");
            context.startActivity(intent);
            dismiss();
        });

        sgHistory1.setOnClickListener(v -> {
            Intent intent = new Intent(context, SGHistoryDetails.class);
            intent.putExtra("category", "Student Government");
            intent.putExtra("date", "15/11/2024");
            context.startActivity(intent);
            dismiss();
        });

        bitsHistory2.setOnClickListener(v -> {
            Intent intent = new Intent(context, OrgHistoryDetails.class);
            intent.putExtra("category", "BITS Officer");
            intent.putExtra("date", "15/9/2023");
            context.startActivity(intent);
            dismiss();
        });

        sgHistory2.setOnClickListener(v -> {
            Intent intent = new Intent(context, SGHistoryDetails.class);
            intent.putExtra("category", "Student Government");
            intent.putExtra("date", "15/11/2023");
            context.startActivity(intent);
            dismiss();
        });
    }
} 