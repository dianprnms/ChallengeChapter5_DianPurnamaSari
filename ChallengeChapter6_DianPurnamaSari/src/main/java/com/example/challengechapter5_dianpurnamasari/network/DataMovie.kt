package com.example.challengechapter5_dianpurnamasari.network

import androidx.room.Entity
import com.example.challengechapter5_dianpurnamasari.model.ResponseData

@Entity
data class DataMovie (
    val results: List<ResponseData>

)