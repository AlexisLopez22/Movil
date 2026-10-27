package com.example.spotify.modelos;

import com.google.gson.annotations.SerializedName;

public class Usuario {

    private String id;
    private int role_id;
    @SerializedName("username")
    private String username;
    private String email;
    private String created_at;
    private String updated_at;

    public String getId() {
        return id;
    }

    public int getRole_id() {
        return role_id;
    }

    public String getName() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}