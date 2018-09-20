package com.example.max.aptpomodorotracker;

import android.os.CountDownTimer;
import java.util.ArrayList;

public class TimedInterval  {

    private static final long DEFAULT_TICK = 1000 ;
    private static final long MILLIS_IN_SEC = 1000 ;
    private static final long SECS_IN_MIN = 60 ;
    private static final long MILLIS_IN_MIN = MILLIS_IN_SEC*SECS_IN_MIN;
    private static final long DEFAULT_POMODORO = 25*MILLIS_IN_MIN ; //25 minutes to millis
    private static final long DEFAULT_SHORT_BREAK = 5*MILLIS_IN_MIN; // 5 minutes to millis
    private static final long DEFAULT_LONG_BREAK = 15*MILLIS_IN_MIN; // 5 minutes to millis

    /**
     *  millisInFuture    The number of millis in the future from the call
     *                          to {start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * countDownInterval The interval along the way to receive
     *                          {@#onTick(long)} callbacks.
     */

    private CountDownTimer timer;
    private long intervalDurationMillis;
    private long intervalRemaining;


    // constructor for making a manual interval sequence
    public TimedInterval(long millisInFuture) {

        timer = new CountDownTimer(millisInFuture, DEFAULT_TICK) {
            @Override
            public void onTick(long millisUntilFinished) {
                intervalRemaining = millisUntilFinished;
            }

            @Override
            public void onFinish() {

            }
        };

        intervalDurationMillis = millisInFuture;

    }


    public void add(CountDownTimer countdown)
    {
        intervals.add(countdown);
        ++numIntervals;
    }


    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish() {
        currentInterval = ( currentInterval + 1 ) % numIntervals;
        currentDurationMillis = intervals.get(currentInterval);

    }

    public long getIntervalDurationMillis() {
        return intervalDurationMillis;
    }





}
