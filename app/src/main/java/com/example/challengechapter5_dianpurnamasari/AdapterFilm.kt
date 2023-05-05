package com.example.challengechapter5_dianpurnamasari

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengechapter5_dianpurnamasari.databinding.ItemHomeBinding
import com.example.challengechapter5_dianpurnamasari.model.ResponseData

class AdapterFilm(var listDataFilm: List<ResponseData>):RecyclerView.Adapter<AdapterFilm.ViewHolder>() {
    class ViewHolder(var binding : ItemHomeBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.judulFilm.text = listDataFilm[position].title
        holder.binding.tglFilm.text = listDataFilm[position].releaseDate
//        Glide.with(holder.itemView).load(listDataFilm[position].posterPath).into(holder.binding.imgHome)
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500${listDataFilm[position].posterPath}")
            .into(holder.binding.imgHome)

        holder.itemView.setOnClickListener{
            var move = Intent(it.context, Detail::class.java)
            move.putExtra("movieId", listDataFilm[position].id)
            it.context.startActivity(move)
        }

    }

    override fun getItemCount(): Int {
        return listDataFilm.size
    }

}