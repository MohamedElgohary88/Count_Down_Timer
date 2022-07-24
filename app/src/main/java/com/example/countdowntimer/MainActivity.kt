package com.example.countdowntimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var timer : CountDownTimer? = null

    var START_TIME_IN_MILLIS: Long = 25 * 60 * 1000
    var remainingTime: Long = START_TIME_IN_MILLIS

    private lateinit var timer_tv: TextView
    private lateinit var start_btn: Button
    private lateinit var reset_tv: TextView
    private lateinit var title_tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        start_btn.setOnClickListener {
            startTimer()
            title_tv.text = resources.getText(R.string.keep_going)
        }
        reset_tv.setOnClickListener {
            resetTimer()
        }
    }

    private fun startTimer() {
         timer = object : CountDownTimer(START_TIME_IN_MILLIS, 1 * 1000) {

            override fun onTick(timeLeft: Long) {
                timer_tv.text = timeLeft.toString()
                remainingTime = timeLeft
                updateTimerText()
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "finish", Toast.LENGTH_LONG).show()
            }
        }.start()
    }

    private fun resetTimer(){
        timer?.cancel()
        remainingTime = START_TIME_IN_MILLIS
        updateTimerText()
        title_tv.text = resources.getText(R.string.take)
    }

    private fun updateTimerText() {
        val minute = remainingTime.div(1000).div(60)
        val second = remainingTime.div(1000) % 60
        val formattedTime = String.format("%02d:%02d", minute, second)
        timer_tv.text = formattedTime
    }

    private fun init() {
        timer_tv = findViewById(R.id.timer_tv)
        start_btn = findViewById(R.id.start_btn)
        reset_tv = findViewById(R.id.reset_tv)
        title_tv = findViewById(R.id.title_tv)
    }
}

