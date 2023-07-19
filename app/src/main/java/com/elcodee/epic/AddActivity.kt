package com.elcodee.epic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.elcodee.epic.databinding.ActivityAddBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddActivity : AppCompatActivity() {
    private val binding: ActivityAddBinding by lazy {
        ActivityAddBinding.inflate(layoutInflater)
    }
    private lateinit var db: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.btnSimpan.setOnClickListener {
            val hari = binding.etPilihan.text.toString()
            val nm1 = binding.etNm1.text.toString()
            val nm2 = binding.etNm2.text.toString()
            db = FirebaseDatabase.getInstance().getReference("Users")
            val users = User(hari, nm1, nm2)
            db.child(hari).setValue(users).addOnSuccessListener {
                binding.etPilihan.text.clear()
                binding.etNm1.text.clear()
                binding.etNm2.text.clear()
                Toast.makeText(this, "save success", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@AddActivity, MainActivity::class.java))
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}