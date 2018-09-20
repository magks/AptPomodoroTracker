package com.example.max.aptpomodorotracker

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.view.ViewPager
import android.view.View


class MainActivity : AppCompatActivity() {



    //lateinit var timer : CountDownTimer
    //lateinit var secondsRemaining : Number
    private val padding = "   "
    private var timerSequence: TimerSequence? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val persistentStatePrefs = getSharedPreferences(getString(R.string.persistent_app_state), Context.MODE_PRIVATE) ?: return
        val defaultValue = resources.getBoolean(R.bool.first_run_default)
        STATE_FIRST_RUN = persistentStatePrefs.getBoolean(getString(R.string.state_first_run), defaultValue)
        if(STATE_FIRST_RUN) {
            createPersistentDefaultTimerSequence()
            with (persistentStatePrefs.edit()) {
                putBoolean(getString(R.string.state_first_run), false)
                apply()
            }
        }

        //add tabs to the view pager
        //addTabs(viewPager)
        val sliding_adapter = ViewPagerAdapter(supportFragmentManager)
        sliding_adapter.addFrag(SessionTabs(), "Current")
        sliding_adapter.addFrag(SessionTabs(), "Session History")
        viewPager.adapter = sliding_adapter

        // Give the TabLayout the ViewPager
       // val tabLayout = sliding_tabs as TabLayout
        sliding_tabs.setupWithViewPager(viewPager)

        // Start Timer flow

        leftButton.setOnClickListener {
            leftButton.text = if ( leftButton.text == "Start" ) "Pause" else "Resume"
            countdownTextView.text = padding + "25:00"
        }
    }

    private fun createPersistentDefaultTimerSequence()
    {
        timerSequence = TimerSequence(applicationContext)
        val sharedPref = getSharedPreferences(getString(R.string.timer_sequences_file_key), Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putLong(getString(R.string.default_timer_sequence_key), 0)
            apply()
        }

    }

    override fun onStart()
    {
        super.onStart()
        getMostRecentTimerSequenceOrDefault()
    }

    fun getMostRecentTimerSequenceOrDefault()
    {
        if(timerSequence == null) // then this is not first run
            timerSequence = loadTopTimerSequenceFromDisk()

    }

    private fun loadTopTimerSequenceFromDisk(): TimerSequence {

    }

    fun updateCountdownTextView()
    {

    }




    //onStart
        // load last used timer
    //onResume
        // get state, updateUI
    //onPause
        //
    //onStop




    fun startTimer(view: View)
    {

    }

    private fun addTabs(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(SessionTabs(), "Current")
        adapter.addFrag(SessionTabs(), "Session History")
        viewPager.adapter = adapter
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String


    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }

        var STATE_FIRST_RUN = true
        var STATE_TIMER = TimedInterval()
        val STATE_TIMER_SEQUENCE = TimerSequence()
        val STATE_CURRENT = "default current session"
        val STATE_HISTORY = "default session history"
        var STATE_SECONDS_REMAINING = 0
    }
}
