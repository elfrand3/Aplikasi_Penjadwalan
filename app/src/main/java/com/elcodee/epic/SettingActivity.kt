package com.elcodee.epic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.elcodee.epic.databinding.ActivityAddBinding
import com.elcodee.epic.databinding.ActivitySettingBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SettingActivity : AppCompatActivity() {
    private val binding: ActivitySettingBinding by lazy {
        ActivitySettingBinding.inflate(layoutInflater)
    }
    private lateinit var databaseReference: DatabaseReference
    var hari: String? = ""
    var nm1: String? = ""
    var nm2: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        val i = intent
        hari = i.getStringExtra("hr")
        nm1 = i.getStringExtra("n1")
        nm2 = i.getStringExtra("n2")

        binding.etPilihan.setText(hari)
        binding.etNm1.setText(nm1)
        binding.etNm2.setText(nm2)
//
//
        binding.btUpdate.setOnClickListener {
            val updatedUser = User(
                hari = binding.etPilihan.text.toString(),
                nm1 = binding.etNm1.text.toString(),
                nm2 = binding.etNm2.text.toString())

            if (hari != null) {
                val userRef = databaseReference.child(hari.toString())

                userRef.setValue(updatedUser).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "update success", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@SettingActivity, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@SettingActivity, "Gagal mengupdate data: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}