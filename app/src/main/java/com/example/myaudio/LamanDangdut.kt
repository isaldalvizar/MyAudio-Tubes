package com.example.myaudio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_laman_dangdut.*

class LamanDangdut : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laman_dangdut)

        //proses menuju layout selanjutnya yang sudah ditentukan.
        pilih.setOnClickListener {
            val intent = Intent(this, Dangdut1::class.java)
            startActivity(intent)
        }
        pilih2.setOnClickListener {
            val intent = Intent(this, Dangdut2::class.java)
            startActivity(intent)
        }
        pilih3.setOnClickListener {
            val intent = Intent(this, Dangdut3::class.java)
            startActivity(intent)
        }
    }

}