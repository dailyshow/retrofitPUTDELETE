package com.example.retrofitpractiec6;

import com.google.gson.annotations.SerializedName;

public class Post {
    /*  "userId": 1,
        "id": 1,
        "title": "dsfsdf"
        "body" : "quit"
    */

    private int userId;
    private int id;
    private String title;
    @SerializedName("body")
    private String text;

    public Post(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
