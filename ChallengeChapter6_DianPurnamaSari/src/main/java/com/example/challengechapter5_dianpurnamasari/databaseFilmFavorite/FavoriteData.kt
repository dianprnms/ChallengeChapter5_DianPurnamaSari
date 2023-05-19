package com.example.challengechapter5_dianpurnamasari.databaseFilmFavorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class FavoriteData(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "gambar") var gambar: String,
    @ColumnInfo(name = "judul")var judul:String
): Serializable
