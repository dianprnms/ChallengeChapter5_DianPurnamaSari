package com.example.challengechapter5_dianpurnamasari.database.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class UserData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var username: String,
    var namaLengkap: String

    ): Serializable