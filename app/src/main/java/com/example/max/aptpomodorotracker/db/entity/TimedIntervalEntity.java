package com.example.max.aptpomodorotracker.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.CountDownTimer;

import com.example.max.aptpomodorotracker.Constants;
import com.example.max.aptpomodorotracker.model.TimedInterval;
@Entity(tableName = "timed_intervals")
public class TimedIntervalEntity implements TimedInterval {

    /**
     *  millisInFuture    The number of millis in the future from the call
     *                          to {start()} until the countdown is done and { #onFinish()}
     *                          is called.
     * countDownInterval The interval along the way to receive
     *                          {@#onTick(long)} callbacks.
     */

    @PrimaryKey(autoGenerate = true)
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
        intervalID = 0;
        timer = new CountDownTimer(intervalDurationMillis, Constants.DEFAULT_TICK) {
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
