package com.example.challengechapter5_dianpurnamasari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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
            startActivity(Intent(this, Profile::class.java))

//            //AMBIL DATA DARI REGISTER
//            var dapatUserId = intent.getLongExtra("user_id", 0)
//
//            //KIRIM DATA KE PROFILE
//            intent.putExtra("user_id", dapatUserId)
//            startActivity(intent)
        }

        binding.home.setText(getString(R.string.hello_world))
        binding.usernameHome.setText(getText(R.string.selamat_datang))

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.action_change_setting -> {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
                true
            }
            else -> super.onOptionsItemSelected(item)
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
