package com.example.challengechapter5_dianpurnamasari.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    const val  BASE_URL ="https://api.themoviedb.org/3/"

    val instance : FilmApiService by lazy {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(FilmApiService::class.java)
    }
}