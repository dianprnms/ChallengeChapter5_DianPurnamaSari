package com.example.challengechapter5_dianpurnamasari.model


import com.google.gson.annotations.SerializedName

data class ProductionCompanyX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: Any,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)