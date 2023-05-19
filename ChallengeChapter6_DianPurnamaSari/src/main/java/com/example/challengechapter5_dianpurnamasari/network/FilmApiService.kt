package com.example.challengechapter5_dianpurnamasari.network

import com.example.challengechapter5_dianpurnamasari.model.ResponseDataDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmApiService {
    @GET("movie/popular?api_key=42d39ad4e4ce9e69d693489f18ac22aa")
     fun getMovie(
     ): Call<DataMovie>


    @GET("movie/{movieId}?api_key=42d39ad4e4ce9e69d693489f18ac22aa")
    fun getMovieDetail(
        @Path("movieId") movieId:Int,
    ): Call<ResponseDataDetail>



}