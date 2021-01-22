package com.example.myaudio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_laman_menu.*

class LamanMenu : AppCompatActivity() {
    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laman_menu)

        dangdut.setOnClickListener {
            val intent = Intent(this, LamanDangdut::class.java)
            startActivity(intent)
        }
        rock.setOnClickListener {
            val intent = Intent(this, LamanRock::class.java)
            startActivity(intent)
        }
        edm.setOnClickListener {
            val intent = Intent(this, LamanEDM::class.java)
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {    //waktu kecepatan pencet 2 kali ketika back, jika melebihi waktu tersebut makan tidak akan ter-back.
            super.onBackPressed()
            finishAffinity()  // mengakhiri layout tersebut sekaligus menutup aplikasinya.
        } else{
            Toast.makeText(applicationContext, "Press back again to Exit", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}