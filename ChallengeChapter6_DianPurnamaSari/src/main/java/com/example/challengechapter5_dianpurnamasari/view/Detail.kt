@file:Suppress("DeferredResultUnused", "DeferredResultUnused", "DeferredResultUnused",
    "MoveLambdaOutsideParentheses", "MoveLambdaOutsideParentheses", "MoveLambdaOutsideParentheses",
    "MoveLambdaOutsideParentheses", "MoveLambdaOutsideParentheses", "MoveLambdaOutsideParentheses",
    "MoveLambdaOutsideParentheses", "MoveLambdaOutsideParentheses", "MoveLambdaOutsideParentheses",
    "MoveLambdaOutsideParentheses"
)

package com.example.challengechapter5_dianpurnamasari.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.challengechapter5_dianpurnamasari.databaseFilmFavorite.FavoriteData
import com.example.challengechapter5_dianpurnamasari.databaseFilmFavorite.FavoriteDatabase
import com.example.challengechapter5_dianpurnamasari.databinding.ActivityDetailBinding
import com.example.challengechapter5_dianpurnamasari.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@Suppress("DeferredResultUnused", "DeferredResultUnused", "DeferredResultUnused",
    "MoveLambdaOutsideParentheses", "MoveLambdaOutsideParentheses", "MoveLambdaOutsideParentheses",
    "MoveLambdaOutsideParentheses", "MoveLambdaOutsideParentheses", "MoveLambdaOutsideParentheses",
    "MoveLambdaOutsideParentheses", "MoveLambdaOutsideParentheses", "MoveLambdaOutsideParentheses",
    "MoveLambdaOutsideParentheses", "MoveLambdaOutsideParentheses", "MoveLambdaOutsideParentheses"
)
@AndroidEntryPoint
class Detail : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var favDB: FavoriteDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getMovieId = intent?.getIntExtra("movieId", 0)!!

        val viewModelDetail = ViewModelProvider(this)[DetailViewModel::class.java]
        viewModelDetail.callDetailFilm(getMovieId)
        viewModelDetail.detailFilm.observe(this, {
            if (it != null) {
                binding.judulDetail.text = it.title
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500${it.posterPath}")
                    .into(binding.imageDetail)
                binding.textDetail.text = it.overview
                favDB = FavoriteDatabase.getInstance(this)
                binding.cbHeart.setOnClickListener {view ->
                    if (binding.cbHeart.isChecked) {
                        GlobalScope.async {
                            favDB?.favoriteDao()?.insertDataFavorite(
                                FavoriteData(
                                    gambar = it.posterPath,
                                    judul = it.title
                                )
                            )
                        }
                        Toast.makeText(
                            this,
                            "Film Berhasil Ditambahkan ke Favorite",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this,
                            "Film Gagal Ditambahkan ke Favorite",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })


    }


}