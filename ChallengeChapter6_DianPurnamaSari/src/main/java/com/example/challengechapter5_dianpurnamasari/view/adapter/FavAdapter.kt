@file:Suppress("DeferredResultUnused", "DeferredResultUnused")

package com.example.challengechapter5_dianpurnamasari.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengechapter5_dianpurnamasari.databaseFilmFavorite.FavoriteData
import com.example.challengechapter5_dianpurnamasari.databinding.ItemFavoriteBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@Suppress("DeferredResultUnused", "DeferredResultUnused")
class FavAdapter(private var listFav : List<FavoriteData>):RecyclerView.Adapter<FavAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemFavoriteBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listFav.size
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        GlobalScope.async {
            holder.binding.judulFilmFav.text = listFav[position].judul
            Glide.with(holder.itemView.context)
                .load("https://image.tmdb.org/t/p/w500${listFav[position].gambar}")
                .into(holder.binding.imgFav)
        }
    }
}