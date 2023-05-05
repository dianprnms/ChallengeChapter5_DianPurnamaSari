package com.example.challengechapter5_dianpurnamasari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.challengechapter5_dianpurnamasari.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest

class Register : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
//    lateinit var editName : EditText
//    lateinit var editEmail : EditText
//    lateinit var editPassword : EditText
//    lateinit var editPasswordConf: EditText
//    lateinit var btnRegister: Button

    var firebaseAuth = FirebaseAuth.getInstance()

//    override fun onStart() {
//        super.onStart()
//        if(firebaseAuth.currentUser!=null){
//            startActivity(Intent(this, MainActivity::class.java))
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        editName = findViewById(R.id.namaLengkap)
//        editEmail = findViewById(R.id.emailRegister)
//        editPassword = findViewById(R.id.passwordRegister)
//        editPasswordConf= findViewById(R.id.password)
//        btnRegister = findViewById(R.id.btnRegister)

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this@Register, MainActivity::class.java))
            finish()
        }

        binding.btnRegister.setOnClickListener {
            if(binding.namaLengkap.text.isNotEmpty() && binding.emailRegister.text.isNotEmpty() && binding.passwordRegister.text.isNotEmpty() && binding.password.text.isNotEmpty()){
                if(binding.passwordRegister.text.toString() == binding.password.text.toString()){
                    //LAUNCH REGISTER
                    processRegister()
                }else{
                    Toast.makeText(this@Register, "Konfirmasi password harus sama",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }else{
                Toast.makeText(this@Register, "Silahkan isi semua data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun processRegister(){
        val fullname = binding.namaLengkap.text.toString()
        val email =binding.emailRegister.text.toString()
        val password = binding.password.text.toString()

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{task ->
                if(task.isSuccessful){
                    val userUpdateProfile = userProfileChangeRequest {
                        displayName = fullname
                    }
                    val user = task.result.user
                    user!!.updateProfile(userUpdateProfile)
                        .addOnCompleteListener {
                            startActivity(Intent(this@Register, MainActivity::class.java))
                        }
                        .addOnFailureListener { error2 ->
                            Toast.makeText(this@Register, error2.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                }else{
                    Toast.makeText(this@Register, "Pendaftaran Gagal", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { error ->
                Toast.makeText(this@Register, error.localizedMessage, Toast.LENGTH_SHORT).show() }
    }
}

