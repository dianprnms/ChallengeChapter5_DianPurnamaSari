package com.example.challengechapter5_dianpurnamasari.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.challengechapter5_dianpurnamasari.databinding.ActivityRegisterBinding
import com.example.challengechapter5_dianpurnamasari.database.user.UserData
import com.example.challengechapter5_dianpurnamasari.database.user.UserDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var firebaseAuth = FirebaseAuth.getInstance()
    private var userDB : UserDatabase? = null


    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDB = UserDatabase.getInstance(this)

        binding.btnRegister.setOnClickListener {
            if(binding.namaLengkap.text.isNotEmpty() && binding.emailRegister.text.isNotEmpty() && binding.passwordRegister.text.isNotEmpty() && binding.password.text.isNotEmpty()){
                if(binding.passwordRegister.text.toString() == binding.password.text.toString()){
                    //LAUNCH REGISTER
                    processRegister()

                    var id : Long? = 0

                    runBlocking {
                        //MASUK DATABASE
                        val addDataJob = GlobalScope.async{
                            return@async addData()
                        }
                        id = addDataJob.await()

                    }
                    //MENGIRIM DATA
                    val intent = Intent(this, Home::class.java)
                    intent.putExtra("user_id", id)
                    startActivity(intent)

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

    private fun processRegister(){
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
                            //startActivity(Intent(this@Register, Profile::class.java))
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

    private fun addData():Long? {
        val username = binding.username.text.toString()
//        var email = binding.emailRegister.text.toString()
        val namaLengkap = binding.namaLengkap.text.toString()
        return userDB?.userDao()?.insertData(UserData(0, username, namaLengkap))
    }
}

