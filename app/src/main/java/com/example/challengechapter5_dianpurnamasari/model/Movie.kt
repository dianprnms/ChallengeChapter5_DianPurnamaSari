package com.example.challengechapter5_dianpurnamasari.model

import com.google.gson.annotations.SerializedName

data class Movie (
        val id: Int,
        val title: String,
        val overview: String,
        @SerializedName("poster_path")
        val posterPath: String
    )