package com.example.max.aptpomodorotracker.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.example.max.aptpomodorotracker.Constants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity(tableName = "timer_sequences")
public class TimerSequenceEntity {

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "timer_seq_id")
    public int tsid;

    @ColumnInfo(name = "date_last_used")
    public Date dateLastUsed;

    @ColumnInfo(name = "timer_name")
    private String nameKey;

    @Ignore
    private ArrayList<TimedIntervalEntity> intervals;

    private int numIntervals;
    private int currentInterval;
    public long currentIntervalDurationMillis;

    //default pomodoro:  25/5/15
    public TimerSequenceEntity()
    {
        tsid = 0;
        currentInterval = 0;
        numIntervals = 0;
        currentIntervalDurationMillis = 0;
    }

    public void addPomodoroShortBreakIntervals()
    {
        intervals.add(new TimedIntervalEntity(Constants.DEFAULT_POMODORO));
        intervals.add(new TimedIntervalEntity(Constants.DEFAULT_SHORT_BREAK));
        ++numIntervals;
    }

    public void addPomodoroLongBreakIntervals()
    {
        intervals.add(new TimedIntervalEntity(Constants.DEFAULT_POMODORO));
        intervals.add(new TimedIntervalEntity(Constants.DEFAULT_LONG_BREAK));
        ++numIntervals;
    }

    //
    public TimerSequenceEntity(String nameKey, Collection<TimedIntervalEntity> timedIntervalCollection)
    {
        tsid = 0;
        this.nameKey = nameKey;
        intervals.addAll(timedIntervalCollection);
        numIntervals = intervals.size();
        currentInterval = 0;
        currentIntervalDurationMillis = intervals.get(0).getIntervalDurationMillis();
    }

    public ArrayList<TimedIntervalEntity> getIntervals() {
        return intervals;
    }

    public void setIntervals(Collection<TimedIntervalEntity> intervals) {
        this.intervals.addAll(intervals);
    }

    public int getCurrentInterval() {
        return currentInterval;
    }

    public void setCurrentInterval(int currentInterval) {
        this.currentInterval = currentInterval;
    }

    public int getNumIntervals() {
        return numIntervals;
    }

    public void setNumIntervals(int numIntervals) {
        this.numIntervals = numIntervals;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public int getTimerId() { return tsid; };


    public String getTimeRemainingString() {
        //convert current interval's milliseconds remaining
        // to a string with format: MM:SS
        String timeRemainingString = "";
        long currentIntervalMillis = intervals.get(currentInterval).getIntervalDurationMillis();
        long totalSeconds = currentIntervalMillis/1000;
        long minutes = totalSeconds/60;
        long seconds = totalSeconds%60;
        timeRemainingString += (minutes < 10 ? "0" : "" ) + String.valueOf(minutes);
        timeRemainingString += ":";
        timeRemainingString += (seconds < 10 ? "0" : "" ) + String.valueOf(seconds);
        return timeRemainingString;
    }

}
