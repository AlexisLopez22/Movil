package com.example.spotify.interfaces;

import com.example.spotify.modelos.RespuestaLogin;
import com.example.spotify.modelos.SolicitudLogin;
import com.example.spotify.modelos.SolicitudRegistro;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterfazAPI {

    @POST("api/login")
    Call<RespuestaLogin> iniciarSesion(
            @Body SolicitudLogin solicitud
    );

    @POST("api/register")
    Call<RespuestaLogin> registrarUsuario(
            @Body SolicitudRegistro solicitud
    );
}