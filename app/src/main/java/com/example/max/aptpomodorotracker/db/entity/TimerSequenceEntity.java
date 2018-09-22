package com.example.max.aptpomodorotracker.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.example.max.aptpomodorotracker.R;
import com.example.max.aptpomodorotracker.model.TimedInterval;
import com.example.max.aptpomodorotracker.model.TimerSequence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity(tableName = "timer_sequences")
public class TimerSequenceEntity {


    @Ignore
    private static final long DEFAULT_TICK = 1000 ;
    @Ignore private static final long MILLIS_IN_SEC = 1000 ;
    @Ignore private static final long SECS_IN_MIN = 60 ;
    @Ignore private static final long MILLIS_IN_MIN = MILLIS_IN_SEC*SECS_IN_MIN;
    @Ignore private static final long DEFAULT_POMODORO = 25*MILLIS_IN_MIN ; //25 minutes to millis
    @Ignore private static final long DEFAULT_SHORT_BREAK = 5*MILLIS_IN_MIN; // 5 minutes to millis
    @Ignore private static final long DEFAULT_LONG_BREAK = 15*MILLIS_IN_MIN; // 5 minutes to millis

    @PrimaryKey @ColumnInfo(name = "timer_seq_id")
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
        currentInterval = 0;
        numIntervals = 0;
        currentIntervalDurationMillis = 0;
        /*
        nameKey = mContext.getString(R.string.default_timer_sequence_key);
        intervals = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            addPomodoroShortBreakIntervals();
        addPomodoroLongBreakIntervals();
        numIntervals = intervals.size();
        currentInterval = 0;
        currentIntervalDurationMillis = DEFAULT_POMODORO;
        */
    }

    public void addPomodoroShortBreakIntervals()
    {
        intervals.add(new TimedIntervalEntity(DEFAULT_POMODORO));
        intervals.add(new TimedIntervalEntity(DEFAULT_SHORT_BREAK));
        ++numIntervals;
    }

    public void addPomodoroLongBreakIntervals()
    {
        intervals.add(new TimedIntervalEntity(DEFAULT_POMODORO));
        intervals.add(new TimedIntervalEntity(DEFAULT_LONG_BREAK));
        ++numIntervals;
    }

    //
    public TimerSequenceEntity(String nameKey, Collection<TimedIntervalEntity> timedIntervalCollection)
    {
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

}
