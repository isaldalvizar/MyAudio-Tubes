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
import kotlinx.android.synthetic.main.activity_rock1.*

import kotlinx.android.synthetic.main.activity_rock2.*
import java.lang.Exception

class Rock1 : AppCompatActivity() {
    private var backPressedTime = 0L
    private var mp: MediaPlayer? = null
    private var currentSong = mutableListOf(R.raw.buried_alive)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rock1)

        controlSound(currentSong[0])

        rockback1.setOnClickListener {
            val intent = Intent(this, LamanRock::class.java)
            startActivity(intent)
        }
    }
    private fun controlSound(id: Int){

        rockplay1.setOnClickListener {
            if (mp == null){
                mp = MediaPlayer.create(this,id)
                Log.d("LamanAwal","ID: ${mp!!.audioSessionId}")
                initialiseSeekBar4()
            }
            mp?.start()
            Log.d("MainActivity","Duration: ${mp!!.duration/1000}seconds")
        }

        rockpause1.setOnClickListener {
            if (mp !== null) mp?.pause()
            Log.d("MainActivity","Paused at: ${mp!!.currentPosition}seconds")
        }

        rockstop1.setOnClickListener {
            if (mp !== null){
                mp?.stop()
                mp?.reset()
                mp?.release()
                mp = null
            }
        }
        seekrockone.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) mp?.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }
    private fun initialiseSeekBar4() {
        seekrockone.max = mp!!.duration

        val handler = Handler()
        handler.postDelayed(object: Runnable {
            override fun run() {
                try {
                    seekrockone.progress = mp!!.currentPosition
                    handler.postDelayed(this, 1000)
                } catch (e: Exception){
                    seekrockone.progress = 0
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