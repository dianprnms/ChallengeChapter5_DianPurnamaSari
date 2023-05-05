package com.example.challengechapter5_dianpurnamasari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.challengechapter5_dianpurnamasari.databinding.ActivityDetailBinding
import com.example.challengechapter5_dianpurnamasari.databinding.ActivityHomeBinding

class Detail : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var getMovieId = intent.getSerializableExtra("movieId") as Int

        val viewModelDetail = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModelDetail.callDetailFilm(getMovieId)
        viewModelDetail.detailFilm.observe(this, Observer {
            if (it != null) {

                binding.judulDetail.setText(it.title)
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500${it.posterPath}")
                    .into(binding.imageDetail)
                binding.textDetail.setText(it.overview)
            }
        })
    }
}