package com.example.myaudio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_laman_edm.*
import kotlinx.android.synthetic.main.activity_laman_rock.*

class LamanEDM : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laman_edm)

        edm1.setOnClickListener {
            val intent = Intent(this, EDM1::class.java)
            startActivity(intent)
        }

    }
}