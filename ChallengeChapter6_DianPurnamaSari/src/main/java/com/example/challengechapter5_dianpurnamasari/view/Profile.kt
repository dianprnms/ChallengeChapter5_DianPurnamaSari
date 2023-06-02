@file:Suppress("DeferredResultUnused")

package com.example.challengechapter5_dianpurnamasari.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.challengechapter5_dianpurnamasari.R
import com.example.challengechapter5_dianpurnamasari.databinding.ActivityProfileBinding
import com.example.challengechapter5_dianpurnamasari.database.user.UserData
import com.example.challengechapter5_dianpurnamasari.database.user.UserDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

@Suppress("DeferredResultUnused")
@AndroidEntryPoint
class Profile : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private var userDB : UserDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.profile.text = getText(R.string.profil)

        userDB = UserDatabase.getInstance(this)

        //mengambil data dari register
        val getUserId = intent.getLongExtra("user_id", 0)

        var userData : UserData? = null

        runBlocking {
            val getDataJob = GlobalScope.async {
                return@async getDataUser(getUserId)
            }
            userData = getDataJob.await()
        }

        if (userData != null) {
            binding.idProfile.text = userData?.id.toString()
            binding.usernameProfile.setText(userData?.username)
//            binding.emailProfile.setText(userData?.email)
            binding.namaLengkap.setText(userData?.namaLengkap)
        }

        binding.btnUpdate.setOnClickListener {
            update()
        }

        binding.toHome.setOnClickListener{
            startActivity(Intent(this, Home::class.java))
        }

        binding.btnLogout.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun getDataUser(userId : Long) : UserData?{
        return userDB?.userDao()?.getDataUser(userId)

    }

    private fun update(){
        val usernm = binding.usernameProfile.text.toString()
        val namaPanjang = binding.namaLengkap.text.toString()


        GlobalScope.async {
            var edit = userDB?.userDao()?.updateData(UserData(0, usernm, namaPanjang))
            runOnUiThread{
                Toast.makeText(this@Profile, "Data Berhasil Diupdate", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}