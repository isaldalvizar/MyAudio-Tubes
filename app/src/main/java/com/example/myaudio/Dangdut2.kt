package com.example.myaudio

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lagu1.*
import kotlinx.android.synthetic.main.activity_lagu2.*
import kotlinx.android.synthetic.main.activity_lagu2.imageButton
import kotlinx.android.synthetic.main.activity_lagu2.imageView2
import kotlinx.android.synthetic.main.activity_lagu2.imageView4
import kotlinx.android.synthetic.main.activity_lagu3.*
import java.lang.Exception

class Dangdut2 : AppCompatActivity() {
    private var backPressedTime = 0L
    private var mp: MediaPlayer? = null
    private var currentSong = mutableListOf(R.raw.hareudang2)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lagu2)

        controlSound(currentSong[0])

    }
    private fun controlSound(id: Int){
        imageButton.setOnClickListener {
            if (mp == null){
                mp = MediaPlayer.create(this,id)
                Log.d("MainActivity","ID: ${mp!!.audioSessionId}")

                initialiseSeekBar2()
            }
            mp?.start()
            Log.d("MainActivity","Duration: ${mp!!.duration/1000}seconds")
        }

        imageView2.setOnClickListener {
            if (mp !== null) mp?.pause()
            Log.d("MainActivity","Paused at: ${mp!!.currentPosition}seconds")
        }
        imageView4.setOnClickListener {
            if (mp !== null){
                mp?.stop()
                mp?.reset()
                mp?.release()
                mp = null
            }
        }
        seekDut2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(SeekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) mp?.seekTo(progress)
            }

            override fun onStartTrackingTouch(SeekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(SeekBar: SeekBar?) {
            }

        })
    }
    private fun initialiseSeekBar2() {
        seekDut2.max = mp!!.duration

        val handler = Handler()
        handler.postDelayed(object: Runnable {
            override fun run() {
                try {
                    seekDut2.progress = mp!!.currentPosition
                    handler.postDelayed(this, 1000)
                } catch (e: Exception){
                    seekDut2.progress = 0
                }
            }
        },0)
    }
    override fun onBackPressed() {
        if (backPressedTime + 1000 > System.currentTimeMillis()) {
            super.onBackPressed()
        }else{
            Toast.makeText(applicationContext, "Matikan lagu terlebih dahulu sebelum kembali, masih perbaikan ", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}