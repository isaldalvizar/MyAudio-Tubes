package com.example.myaudio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_laman_dangdut.*
import kotlinx.android.synthetic.main.activity_laman_rock.*

class LamanRock : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laman_rock)

        rock1.setOnClickListener {
            val intent = Intent(this, Rock1::class.java)
            startActivity(intent)
        }
        rock2.setOnClickListener {
            val intent = Intent(this, Rock2::class.java)
            startActivity(intent)
        }
    }
}
