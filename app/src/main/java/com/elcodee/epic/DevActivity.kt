package com.elcodee.epic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elcodee.epic.databinding.ActivityDevBinding

class DevActivity : AppCompatActivity() {
    private val binding: ActivityDevBinding by lazy {
        ActivityDevBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            startActivity(Intent(this@DevActivity, MainActivity::class.java))
            finish()
        }
    }
}