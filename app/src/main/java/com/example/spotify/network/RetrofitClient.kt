package com.example.spotify.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://10.0.2.2:8000/api/"

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Accept", "application/json")
                .build()
            chain.proceed(request)
        }
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Nueva API general
    val apiService: ApiService = retrofit.create(ApiService::class.java)

    // API antigua para compatibilidad con MainActivity y RegistroActivity
    val autenticacionApi: com.example.spotify.interfaces.AutenticacionAPI = retrofit.create(com.example.spotify.interfaces.AutenticacionAPI::class.java)
    val interfazApi: com.example.spotify.interfaces.InterfazAPI = retrofit.create(com.example.spotify.interfaces.InterfazAPI::class.java)
}