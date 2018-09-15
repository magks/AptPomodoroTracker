package com.example.max.aptpomodorotracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.view.ViewPager
import android.view.View


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //add tabs to the view pager
        //addTabs(viewPager)
        val sliding_adapter = ViewPagerAdapter(supportFragmentManager)
        sliding_adapter.addFrag(SessionTabs(), "Current")
        sliding_adapter.addFrag(SessionTabs(), "Session History")
        viewPager.setAdapter(sliding_adapter)

        // Give the TabLayout the ViewPager
       // val tabLayout = sliding_tabs as TabLayout
        sliding_tabs.setupWithViewPager(viewPager)

        // Example of a call to a native method
        //sample_text.text = stringFromJNI()
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String


    private fun addTabs(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(SessionTabs(), "Current")
        adapter.addFrag(SessionTabs(), "Session History")
        viewPager.adapter = adapter
    }

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
