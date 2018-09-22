package com.example.max.aptpomodorotracker.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.CountDownTimer;

import com.example.max.aptpomodorotracker.model.TimedInterval;

@Entity(tableName = "timed_intervals")
public class TimedIntervalEntity implements TimedInterval {

    @Ignore private static final long DEFAULT_TICK = 1000 ;
    @Ignore private static final long MILLIS_IN_SEC = 1000 ;
    @Ignore private static final long SECS_IN_MIN = 60 ;
    @Ignore private static final long MILLIS_IN_MIN = MILLIS_IN_SEC*SECS_IN_MIN;
    @Ignore private static final long DEFAULT_POMODORO = 25*MILLIS_IN_MIN ; //25 minutes to millis
    @Ignore private static final long DEFAULT_SHORT_BREAK = 5*MILLIS_IN_MIN; // 5 minutes to millis
    @Ignore private static final long DEFAULT_LONG_BREAK = 15*MILLIS_IN_MIN; // 5 minutes to millis

    /**
     *  millisInFuture    The number of millis in the future from the call
     *                          to {start()} until the countdown is done and { #onFinish()}
     *                          is called.
     * countDownInterval The interval along the way to receive
     *                          {@#onTick(long)} callbacks.
     */

    @PrimaryKey
    @ColumnInfo(name= "interval_id")
    public int intervalID;

    @ColumnInfo(name = "tsid")
    public int timerSequenceID;

    private long intervalDurationMillis;
    private long intervalRemaining;

    @Ignore
    private CountDownTimer timer;

    // constructor for making a manual interval sequence
    public TimedIntervalEntity(long intervalDurationMillis) {

        timer = new CountDownTimer(intervalDurationMillis, DEFAULT_TICK) {
            @Override
            public void onTick(long millisUntilFinished) {
                intervalRemaining = millisUntilFinished;
            }

            @Override
            public void onFinish() {

            }
        };

        this.intervalDurationMillis = intervalDurationMillis;

    }

    public long getIntervalDurationMillis() {
        return intervalDurationMillis;
    }

    public void setIntervalDurationMillis(long intervalDurationMillis) {
        this.intervalDurationMillis = intervalDurationMillis;
    }

    public long getIntervalRemaining() {
        return intervalRemaining;
    }

    public void setIntervalRemaining(long intervalRemaining) {
        this.intervalRemaining = intervalRemaining;
    }
}
