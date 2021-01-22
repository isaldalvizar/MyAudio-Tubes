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
import java.lang.Exception

class Dangdut1 : AppCompatActivity() {
    private var backPressedTime = 0L
    private var mp: MediaPlayer? = null
    private var currentSong = mutableListOf(R.raw.hareudang1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lagu1)

        controlSound(currentSong[0])

        dangdutback.setOnClickListener {
            val intent = Intent(this, LamanDangdut::class.java)
            startActivity(intent)
        }

    }
    //control play, pause, stop
    private fun controlSound(id: Int){
        //for play
        dangdutplay.setOnClickListener {
            if (mp == null){
                mp = MediaPlayer.create(this,id)
                Log.d("LamanAwal","ID: ${mp!!.audioSessionId}")

                initialiseSeekBar()
            }
            mp?.start()
            Log.d("MainActivity","Duration: ${mp!!.duration/1000}seconds")
        }
        //for pause
        dangdutpause.setOnClickListener {
            if (mp !== null) mp?.pause()
            Log.d("MainActivity","Paused at: ${mp!!.currentPosition}seconds")
        }
        //for stop
        dangdutstop.setOnClickListener {
            if (mp !== null){
                mp?.stop()
                mp?.reset()
                mp?.release()
                mp = null
            }
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) mp?.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }

    private fun initialiseSeekBar() {
        seekBar.max = mp!!.duration

        val handler = Handler()
        handler.postDelayed(object: Runnable {
            override fun run() {
                try {
                    seekBar.progress = mp!!.currentPosition
                    handler.postDelayed(this, 1000)
                } catch (e: Exception){
                    seekBar.progress = 0
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
