package com.elcodee.epic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashscreenActivity : AppCompatActivity() {
    private val  SPALSH_TIME_OUT : Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Handler().postDelayed({
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }, SPALSH_TIME_OUT)
    }
}