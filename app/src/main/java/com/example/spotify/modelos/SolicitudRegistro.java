package com.example.spotify.modelos;

public class SolicitudRegistro {

    private String username;
    private String email;
    private String password;
    private int role_id;

    public SolicitudRegistro(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role_id = role_id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getRole_id() { return  role_id; }
}