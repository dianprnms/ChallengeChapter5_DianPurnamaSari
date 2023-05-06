package com.example.challengechapter5_dianpurnamasari.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDAO {

    @Insert
    fun insertData(userData : UserData) : Long

    @Query ("SELECT * FROM UserData WHERE id=:user_id")
    fun getDataUser(user_id : Long) : UserData?

    @Update
    fun updateData(userData: UserData)

}
