package com.example.challengechapter5_dianpurnamasari.databaseFilmFavorite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDAO {
        @Insert
        fun insertDataFavorite(favoriteData: FavoriteData)

        @Query("SELECT * FROM FavoriteData ORDER BY id DESC")
        fun getDataFavorite() : List<FavoriteData>

    }