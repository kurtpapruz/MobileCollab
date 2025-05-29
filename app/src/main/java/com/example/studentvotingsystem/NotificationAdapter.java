package com.example.studentvotingsystem;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private List<NotificationItem> notifications;

    public NotificationAdapter(List<NotificationItem> notifications) {
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        NotificationItem notification = notifications.get(position);
        holder.titleView.setText(notification.getTitle());
        holder.messageView.setText(notification.getMessage());
        holder.timeView.setText(notification.getTime());
        
        // Set background color based on notification type
        int backgroundColor;
        switch (notification.getType()) {
            case NotificationItem.TYPE_ELECTION_START:
                backgroundColor = holder.itemView.getContext().getColor(R.color.light_blue);
                break;
            case NotificationItem.TYPE_ELECTION_END:
                backgroundColor = holder.itemView.getContext().getColor(R.color.light_yellow);
                break;
            case NotificationItem.TYPE_RESULTS:
                backgroundColor = holder.itemView.getContext().getColor(R.color.light_green);
                break;
            default:
                backgroundColor = holder.itemView.getContext().getColor(R.color.white);
                break;
        }
        holder.itemView.setBackgroundColor(backgroundColor);

        // Set click listener for the entire notification item
        holder.itemView.setOnClickListener(v -> {
            Intent intent;
            switch (notification.getType()) {
                case NotificationItem.TYPE_ELECTION_START:
                case NotificationItem.TYPE_REMINDER:
                    // Navigate to Vote Activity for active elections
                    intent = new Intent(v.getContext(), VoteActivity.class);
                    break;
                case NotificationItem.TYPE_RESULTS:
                    // TODO: Navigate to Results Activity once created
                    // For now, we'll show the vote screen
                    intent = new Intent(v.getContext(), VoteActivity.class);
                    break;
                case NotificationItem.TYPE_ELECTION_END:
                    // Navigate to Dashboard for ended elections
                    intent = new Intent(v.getContext(), DashboardActivity.class);
                    break;
                default:
                    intent = new Intent(v.getContext(), DashboardActivity.class);
                    break;
            }
            // Add any extra data if needed
            intent.putExtra("notification_id", position);
            intent.putExtra("notification_type", notification.getType());
            intent.putExtra("notification_title", notification.getTitle());
            
            // Start the activity
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    static class NotificationViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;
        TextView messageView;
        TextView timeView;

        NotificationViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.notificationTitle);
            messageView = itemView.findViewById(R.id.notificationMessage);
            timeView = itemView.findViewById(R.id.notificationTime);
        }
    }
} 