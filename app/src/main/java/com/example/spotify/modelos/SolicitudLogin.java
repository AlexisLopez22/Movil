package com.example.spotify.modelos;

public class SolicitudLogin {

    private String email;
    private String password;

    public SolicitudLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}