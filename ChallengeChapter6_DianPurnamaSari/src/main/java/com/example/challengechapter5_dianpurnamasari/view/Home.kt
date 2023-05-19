package com.example.challengechapter5_dianpurnamasari.view

//import com.example.challengechapter5_dianpurnamasari.ActivityHomeBinding
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengechapter5_dianpurnamasari.R
import com.example.challengechapter5_dianpurnamasari.databinding.ActivityHomeBinding
import com.example.challengechapter5_dianpurnamasari.view.adapter.AdapterFilm
import com.example.challengechapter5_dianpurnamasari.viewmodel.FilmViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : AppCompatActivity() {
    private lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDataNews()


        binding.toProfile.setOnClickListener{
            startActivity(Intent(this, Profile::class.java))

            //AMBIL DATA DARI REGISTER
            var dapatUserId = intent.getLongExtra("user_id", 0)

            //MENGIRIM DATA
                    val id : Long = 0
                    val intent = Intent(this, Profile::class.java)
                    intent.putExtra("user_id", id)
                    startActivity(intent)
        }

        binding.toFavorite.setOnClickListener {
            startActivity(Intent(this, FavoriteFilm::class.java))
        }

        binding.home.text = getString(R.string.hello_world)
        binding.usernameHome.text = getText(R.string.selamat_datang)

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

    private fun showDataNews(){
        val viewModelFilm = ViewModelProvider(this)[FilmViewModel::class.java]
        viewModelFilm.callApiFilm()
        viewModelFilm.dataFilm.observe(this) {
            if (it != null) {
                binding.rvHome.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.rvHome.adapter = AdapterFilm(it.results)
            }
        }
    }


}
