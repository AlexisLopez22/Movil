package com.example.spotify.modelos;

public class Usuario {

    private String id;
    private int role_id;
    private String name;
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
        return name;
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