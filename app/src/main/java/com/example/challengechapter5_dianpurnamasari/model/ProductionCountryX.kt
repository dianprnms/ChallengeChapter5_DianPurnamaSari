package com.example.challengechapter5_dianpurnamasari.model


import com.google.gson.annotations.SerializedName

data class ProductionCountryX(
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("name")
    val name: String
)