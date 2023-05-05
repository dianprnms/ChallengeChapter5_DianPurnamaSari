package com.example.challengechapter5_dianpurnamasari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import com.example.challengechapter5_dianpurnamasari.databinding.ActivityHomeBinding
import com.example.challengechapter5_dianpurnamasari.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val crashButton = Button(this)
        crashButton.text = "Test Crash"
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(crashButton, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))


        binding.btnlogin.setOnClickListener{
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
            startActivity(Intent(this,Home::class.java))
        }

        binding.toRegister.setOnClickListener{
            startActivity(Intent(this,Register::class.java))
        }
    }
}