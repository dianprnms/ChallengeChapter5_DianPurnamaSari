package com.example.challengechapter5_dianpurnamasari.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.challengechapter5_dianpurnamasari.databaseFilmFavorite.FavoriteData
import com.example.challengechapter5_dianpurnamasari.databaseFilmFavorite.FavoriteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavViewModel (app : Application): AndroidViewModel(app){
    var allFav : MutableLiveData<List<FavoriteData>> = MutableLiveData()

    init{
        getAllFav()
    }

    private fun getAllFav() {
        GlobalScope.launch {
            val favDao = FavoriteDatabase.getInstance(getApplication())!!.favoriteDao()
            val listFav = favDao.getDataFavorite()
            allFav.postValue(listFav)
        }
    }

}