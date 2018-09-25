package com.example.max.aptpomodorotracker.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.timer_session_activity.*

import android.support.v4.view.ViewPager
import android.view.View
import com.example.max.aptpomodorotracker.*
import com.example.max.aptpomodorotracker.db.entity.TimerSequenceEntity
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.example.max.aptpomodorotracker.databinding.TimerSessionActivityBinding
import com.example.max.aptpomodorotracker.viewmodel.TimerSessionViewModel

class MainActivity : AppCompatActivity() {

    /*
    private var timerSeqDB = Room.databaseBuilder(applicationContext,
            TimerSequenceDatabase::class.java,
            DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
            */

    //lateinit var timer : CountDownTimer
    //lateinit var secondsRemaining : Number
    private val padding = "   "
    private var timerSequence: TimerSequenceEntity? = null
    private lateinit var mToggle: ActionBarDrawerToggle
    private lateinit var mDrawerLayout : DrawerLayout
    private lateinit var mBinding: TimerSessionActivityBinding// =  DataBindingUtil.inflate<TimerSessionFragmentBinding>(layoutInflater, R.layout.timer_session_activity, , false)
    //private lateinit var

    val TAG = "MainActivityViewModel"
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.timer_session_activity)
        mBinding = DataBindingUtil.setContentView(this, R.layout.timer_session_activity)//>(inflater, R.layout.timer_session_fragment, container, false)
        navDrawerInit()
        //add tabs to the view pager
        addTabs(viewPager)
        // Give the TabLayout the ViewPager to allow user slide gesture
        sliding_tabs.setupWithViewPager(viewPager)

        val viewModel = ViewModelProviders.of(this).get(TimerSessionViewModel::class.java)

        subscribeUi(viewModel)

        leftButton.setOnClickListener {
            leftButton.text = if ( leftButton.text == "Start" ) "Pause" else "Resume"
            countdownTextView.text = padding + "99:99"
        }
    }


    private fun subscribeUi(viewModel: TimerSessionViewModel) {
        // Update the list when the data changes
        viewModel.timerSequences.observe(this, Observer<List<TimerSequenceEntity>> {
                    if (it != null) {
                        viewModel.timeRemaining = it[0].getTimeRemainingString();
                    } else {
                        mBinding.timeRemaining = "--:--"
                    }
                    // espresso does not know how to wait for data binding's loop so we execute changes
                    // sync.
                    mBinding.executePendingBindings()

        })
    }


    // Menu icons are inflated just as they were with actionbar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if( mToggle.onOptionsItemSelected(item) )
            return true
        return super.onOptionsItemSelected(item)
    }

    override fun onStart()
    {
        super.onStart()
        //getMostRecentTimerSequenceOrDefault()
//        val viewModel = ViewModelProviders.of(this).get(TimerSequenceListViewModel::class.java)

       // subscribeUi(viewModel)
    }
/* broken see below
    private fun subscribeUi(viewModel: TimerSequenceListViewModel) {
        // Update the list when the data changes
        viewModel.timerSequences.observe(this, Observer<List<ProductEntity>> {
            override fun onChanged(myProducts: List<ProductEntity>?) {
                if (myProducts != null) {
                    mBinding.setIsLoading(false)
                    mProductAdapter.setProductList(myProducts)
                } else {
                    mBinding.setIsLoading(true)
                }
                // espresso does not know how to wait for data binding's loop so we execute changes
                // sync.
                mBinding.executePendingBindings()
            }
        })
    }
*/
    /*
    private fun subscribeUi(viewModel: TimerSequenceListViewModel) {
        // Update the list when the data changes
        viewModel.timerSequences.observe(this, Observer{ myProducts ->
            if (myProducts != null) {
                mBinding.setIsLoading(false)
                mProductAdapter.setProductList(myProducts)
            } else {
                mBinding.setIsLoading(true)
            }
            // espresso does not know how to wait for data binding's loop so we execute changes
            // sync.
            mBinding.executePendingBindings()
        })
    }


    fun onShowData(temperatureData: TemperatureData) {
        view.showData(temperatureData)
    }
    */

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

    private fun navDrawerInit()
    {
        val toolbar = findViewById<Toolbar>(R.id.toolbar_main1)
        mDrawerLayout = findViewById(R.id.nav_drawer)

        mToggle = NavigationToggler(this, mDrawerLayout, toolbar,
                R.string.nav_open, R.string.nav_close)
        mDrawerLayout.addDrawerListener(mToggle)
        mToggle.syncState()
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        }
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
        //var STATE_TIMER = TimedInterval()
        //val STATE_TIMER_SEQUENCE = TimerSequence()
        val STATE_CURRENT = "default current session"
        val STATE_HISTORY = "default session history"
        val DATABASE_NAME = "timer_seq_db"
        var STATE_SECONDS_REMAINING = 0
    }
}
