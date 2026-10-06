package com.example.spotify.interfaces;

import com.example.spotify.modelos.RespuestaLogin;
import com.example.spotify.modelos.SolicitudLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AutenticacionAPI {

    @POST("login")
    Call<RespuestaLogin> iniciarSesion(
            @Body SolicitudLogin solicitud
    );
}
