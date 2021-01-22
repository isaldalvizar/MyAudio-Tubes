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
import kotlinx.android.synthetic.main.activity_lagu3.*
import java.lang.Exception

class Dangdut3 : AppCompatActivity() {
    private var backPressedTime = 0L
    private var mp: MediaPlayer? = null
    private var currentSong = mutableListOf(R.raw.hareudang3)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lagu3)

        controlSound(currentSong[0])

    }
    private fun controlSound(id: Int){
        imageButton.setOnClickListener {
            if (mp == null){
                mp = MediaPlayer.create(this,id)
                Log.d("MainActivity","ID: ${mp!!.audioSessionId}")
                initialiseSeekBar3()
            }
            mp?.start()
            Log.d("MainActivity","Duration: ${mp!!.duration/1000}seconds")
        }

        imageView2.setOnClickListener {
            if (mp !== null) mp?.pause()
                Log.d("MainActivity", "Paused at: ${mp!!.currentPosition}seconds")
        }
        imageView4.setOnClickListener {
            if (mp !== null){
                mp?.stop()
                mp?.reset()
                mp?.release()
                mp = null
            }
        }
        seekDut3.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) mp?.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }
    private fun initialiseSeekBar3() {
        seekDut3.max = mp!!.duration

        val handler = Handler()
        handler.postDelayed(object: Runnable {
            override fun run() {
                try {
                    seekDut3.progress = mp!!.currentPosition
                    handler.postDelayed(this, 1000)
                } catch (e: Exception){
                    seekDut3.progress = 0
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