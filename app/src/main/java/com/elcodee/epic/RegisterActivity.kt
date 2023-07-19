package com.elcodee.epic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.elcodee.epic.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.apply {
            btDaftar.setOnClickListener {
                val email = etEmail.text.toString()
                val pass = etPassword.text.toString()
                val confirm = etKonfirm.text.toString()

                if (email.isNotEmpty()&& pass.isNotEmpty()&& confirm.isNotEmpty()){
                    if (pass == confirm){
                        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                            if (it.isSuccessful){
                                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                                Toast.makeText(this@RegisterActivity, "Daftar berhasil", Toast.LENGTH_SHORT).show()
                                finish()
                            }else{
                                Toast.makeText(this@RegisterActivity, it.exception.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }
                    }else{
                        Toast.makeText(this@RegisterActivity, "Password salah, coba lagi", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this@RegisterActivity, "Data Erorr", Toast.LENGTH_SHORT).show()
                }
            }
            btLinklogin.setOnClickListener {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                finish()
            }
        }
    }
}