package com.example.challengechapter5_dianpurnamasari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challengechapter5_dianpurnamasari.databinding.ActivityMainBinding
import com.example.challengechapter5_dianpurnamasari.databinding.ActivityProfileBinding

class Profile : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.profile.setText(getText(R.string.profil))

    }
}