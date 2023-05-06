package com.example.challengechapter5_dianpurnamasari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.challengechapter5_dianpurnamasari.databinding.ActivityMainBinding
import com.example.challengechapter5_dianpurnamasari.databinding.ActivityProfileBinding
import com.example.challengechapter5_dianpurnamasari.user.UserData
import com.example.challengechapter5_dianpurnamasari.user.UserDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class Profile : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    var userDB : UserDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.profile.setText(getText(R.string.profil))

        userDB = UserDatabase.getInstance(this)

        //mengambil data dari register
        var getUserId = intent.getLongExtra("user_id", 0)

        var userData : UserData? = null

        runBlocking {
            var getDataJob = GlobalScope.async {
                return@async getDataUser(getUserId)
            }
            userData = getDataJob.await()
        }

        if (userData != null) {
            binding.idProfile.setText(userData?.id.toString())
            binding.usernameProfile.setText(userData?.username)
            binding.namaLengkap.setText(userData?.namaLengkap)
        }

        binding.btnUpdate.setOnClickListener {
            update()
        }

        binding.btnLogout.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    fun getDataUser(userId : Long) : UserData?{
        return userDB?.userDao()?.getDataUser(userId)

    }

    fun update(){
        var idUser =binding.idProfile.text.toString().toInt()
        var usernm = binding.usernameProfile.text.toString()
        var namaPanjang = binding.namaLengkap.text.toString()

        GlobalScope.async {
            var edit = userDB?.userDao()?.updateData(UserData(idUser, usernm, namaPanjang))
            runOnUiThread{
                Toast.makeText(this@Profile, "Data Berhasil Diupdate", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}