package com.example.challengechapter5_dianpurnamasari.view

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.challengechapter5_dianpurnamasari.R
import com.example.challengechapter5_dianpurnamasari.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
     lateinit var binding : ActivityMainBinding
     lateinit var progressDialog : ProgressDialog
     var firebaseAuth = FirebaseAuth.getInstance()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.text = getText(R.string.login)


        val crashButton = Button(this)
        crashButton.text = "Test Crash"
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(crashButton, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Logging")
        progressDialog.setMessage("Silahkan Tunggu")

        binding.btnlogin.setOnClickListener {
            if (binding.email.text.isNotEmpty() && binding.passwordLogin.text.isNotEmpty()) {
                prosesLogin()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Silahkan isi Email dan Password terlebih dahulu",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.toRegister.setOnClickListener{
            startActivity(Intent(this, Register::class.java))
        }
    }

    fun prosesLogin(){
        val email = binding.email.text.toString()
        val password = binding.passwordLogin.text.toString()

        progressDialog.show()

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                startActivity(Intent(this@MainActivity, Home::class.java))

            }
            .addOnFailureListener {error ->
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
            }
            .addOnCompleteListener{
                progressDialog.dismiss()
            }

    }
}