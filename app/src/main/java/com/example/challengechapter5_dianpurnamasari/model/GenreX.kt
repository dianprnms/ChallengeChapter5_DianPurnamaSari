package com.example.challengechapter5_dianpurnamasari.model


import com.google.gson.annotations.SerializedName

data class GenreX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)