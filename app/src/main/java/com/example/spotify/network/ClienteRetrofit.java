package com.example.spotify.network;

import com.example.spotify.interfaces.AutenticacionAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClienteRetrofit {

    private static final String URL_BASE =
            "http://10.0.2.2:8000/api/";

    private static Retrofit retrofit;

    public static AutenticacionAPI obtenerAPI() {

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(AutenticacionAPI.class);
    }
}