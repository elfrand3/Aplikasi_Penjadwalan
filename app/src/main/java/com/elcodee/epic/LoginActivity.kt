package com.elcodee.epic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.elcodee.epic.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.apply {
            btLinkdaftar.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
                finish()
            }
            btLogin.setOnClickListener {
                val email = etEmail.text.toString()
                val pass = etPassword.text.toString()

                if (email.isNotEmpty()&& pass.isNotEmpty()){
                    firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful){
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(this@LoginActivity, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this@LoginActivity, "Data Erorr", Toast.LENGTH_SHORT).show()
                }
//                if (idEmail.text.toString().equals(email)&&idPassword.text.toString().equals(pass)){
//                    Toast.makeText(this@LoginActivity, "Login berhasil", Toast.LENGTH_SHORT).show()
//                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//                    finish()
//                }else if (idEmail.text.isEmpty()){
//                    Toast.makeText(this@LoginActivity, "Email kosong silahkan masukkan email anda!", Toast.LENGTH_SHORT).show()
//                }else if (idPassword.text.isEmpty()){
//                    Toast.makeText(this@LoginActivity, "Password kosong silahkan masukkan email anda!", Toast.LENGTH_SHORT).show()
//                }else{
//                    Toast.makeText(this@LoginActivity, "email & password salah!", Toast.LENGTH_SHORT).show()
//                }
            }
        }
    }
}