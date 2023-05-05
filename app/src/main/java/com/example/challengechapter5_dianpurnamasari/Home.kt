package com.example.challengechapter5_dianpurnamasari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.challengechapter5_dianpurnamasari.ActivityHomeBinding
import com.example.challengechapter5_dianpurnamasari.databinding.ActivityHomeBinding
import com.example.challengechapter5_dianpurnamasari.databinding.ActivityMainBinding
import com.example.challengechapter5_dianpurnamasari.network.RetrofitClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Home : AppCompatActivity() {
    lateinit var binding:ActivityHomeBinding
    lateinit var adapterFilm:AdapterFilm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDataNews()

        binding.toProfile.setOnClickListener{
            startActivity(Intent(this,Profile::class.java))
        }
    }

    fun showDataNews(){
        val viewModelFilm = ViewModelProvider(this).get(FilmViewModel::class.java)
        viewModelFilm.callApiFilm()
        viewModelFilm.dataFilm.observe(this, Observer {
            if (it != null){
                binding.rvHome.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.rvHome.adapter = AdapterFilm(it.results)
            }
        })
    }


}
