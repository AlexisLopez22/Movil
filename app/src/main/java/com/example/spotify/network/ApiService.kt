package com.example.spotify.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // -------------------------------------------------------------
    // AUTENTICACIÓN
    // -------------------------------------------------------------
    @POST("login")
    fun login(@Body credentials: JsonObject): Call<JsonObject>

    @POST("register")
    fun register(@Body userData: JsonObject): Call<JsonObject>

    // -------------------------------------------------------------
    // USUARIOS Y PERFIL
    // -------------------------------------------------------------
    @GET("user")
    fun obtenerPerfil(
        @Header("Authorization") token: String
    ): Call<JsonObject>

    // -------------------------------------------------------------
    // CATÁLOGO MUSICAL
    // -------------------------------------------------------------
    @POST("artists")
    fun crearArtista(
        @Header("Authorization") token: String,
        @Body artistData: JsonObject
    ): Call<JsonObject>

    @POST("albums")
    fun crearAlbum(
        @Header("Authorization") token: String,
        @Body albumData: JsonObject
    ): Call<JsonObject>

    @POST("songs")
    fun crearCancion(
        @Header("Authorization") token: String,
        @Body songData: JsonObject
    ): Call<JsonObject>

    @GET("songs")
    fun obtenerCanciones(
        @Header("Authorization") token: String
    ): Call<JsonObject>

    // -------------------------------------------------------------
    // BÚSQUEDA
    // -------------------------------------------------------------
    @GET("search")
    fun buscar(
        @Header("Authorization") token: String,
        @Query("query") query: String
    ): Call<JsonObject>
}