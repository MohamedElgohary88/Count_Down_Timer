package com.example.countdowntimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var timer_tv: TextView
    lateinit var start_btn: Button
    lateinit var reset_tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        start_btn.setOnClickListener {
            var timer = object : CountDownTimer(25 * 60 * 1000, 1*1000) {

                override fun onTick(timer: Long) {
                    timer_tv.text = timer.toString()
                }

                override fun onFinish() {
                    Toast.makeText(this@MainActivity,"finish",Toast.LENGTH_LONG).show()
                }
            }.start()
        }
    }

    fun init() {
        timer_tv = findViewById(R.id.timer_tv)
        start_btn = findViewById(R.id.start_btn)
        reset_tv = findViewById(R.id.reset_tv)
    }
}

