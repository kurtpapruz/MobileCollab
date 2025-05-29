package com.example.studentvotingsystem;

public class NotificationItem {
    public static final int TYPE_ELECTION_START = 1;
    public static final int TYPE_ELECTION_END = 2;
    public static final int TYPE_RESULTS = 3;
    public static final int TYPE_REMINDER = 4;

    private String title;
    private String message;
    private String time;
    private int type;

    public NotificationItem(String title, String message, String time, int type) {
        this.title = title;
        this.message = message;
        this.time = time;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public int getType() {
        return type;
    }
} 