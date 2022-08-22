package com.example.countdowntimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var timer: CountDownTimer? = null
    private var isTimerRunning = false

    var START_TIME_IN_MILLIS: Long = 25 * 60 * 1000
    var remainingTime: Long = START_TIME_IN_MILLIS
    var REMAINING_TIME = "remainingTime"

    private lateinit var timer_tv: TextView
    private lateinit var start_btn: Button
    private lateinit var reset_tv: TextView
    private lateinit var title_tv: TextView
    private lateinit var pb: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        start_btn.setOnClickListener {
            if (!isTimerRunning) {
                startTimer(START_TIME_IN_MILLIS)
                title_tv.text = resources.getText(R.string.keep_going)
            }
        }
        reset_tv.setOnClickListener {
            resetTimer()
        }

    }

    private fun startTimer(startTime: Long) {
        timer = object : CountDownTimer(startTime, 1 * 1000) {

            override fun onTick(timeLeft: Long) {
                timer_tv.text = timeLeft.toString()
                remainingTime = timeLeft
                updateTimerText()
                pb.progress = remainingTime.toDouble().div(START_TIME_IN_MILLIS).times(100).toInt()
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "finish", Toast.LENGTH_LONG).show()
                isTimerRunning = false
            }
        }.start()

        isTimerRunning = true

    }

    private fun resetTimer() {
        timer?.cancel()
        remainingTime = START_TIME_IN_MILLIS
        updateTimerText()
        title_tv.text = resources.getText(R.string.take)
        isTimerRunning = false
        pb.progress = 100
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
        pb = findViewById(R.id.progressBar)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(REMAINING_TIME,remainingTime)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val savedTime= savedInstanceState.getLong(REMAINING_TIME)
        if (savedTime!= START_TIME_IN_MILLIS){
            startTimer(savedTime)
        }

    }
}


