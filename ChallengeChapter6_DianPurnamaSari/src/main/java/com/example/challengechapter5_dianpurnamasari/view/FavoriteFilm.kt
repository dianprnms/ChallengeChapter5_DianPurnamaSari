package com.example.challengechapter5_dianpurnamasari.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengechapter5_dianpurnamasari.databaseFilmFavorite.FavoriteDatabase
import com.example.challengechapter5_dianpurnamasari.databinding.ActivityFavoriteFilmBinding
import com.example.challengechapter5_dianpurnamasari.view.adapter.FavAdapter
import com.example.challengechapter5_dianpurnamasari.viewmodel.FavViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFilm : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteFilmBinding
    private var favDB : FavoriteDatabase? = null
    //    lateinit var favViewModel: FavViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favVM()
        favDB = FavoriteDatabase.getInstance(this)


    }
    private fun favVM() {
        val favViewModel = ViewModelProvider(this)[FavViewModel::class.java]
        favViewModel.allFav.observe(this) {
            if (it != null) {
                binding.rvFav.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.rvFav.adapter = FavAdapter(it)
            } else {
                Log.e("LOG", "viewModel kosong")
            }
        }
    }
}