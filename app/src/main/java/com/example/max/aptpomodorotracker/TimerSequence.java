package com.example.max.aptpomodorotracker;


import android.content.Context;

import java.util.ArrayList;
import java.util.Collection;

public class TimerSequence {

    private static final long DEFAULT_TICK = 1000 ;
    private static final long MILLIS_IN_SEC = 1000 ;
    private static final long SECS_IN_MIN = 60 ;
    private static final long MILLIS_IN_MIN = MILLIS_IN_SEC*SECS_IN_MIN;
    private static final long DEFAULT_POMODORO = 25*MILLIS_IN_MIN ; //25 minutes to millis
    private static final long DEFAULT_SHORT_BREAK = 5*MILLIS_IN_MIN; // 5 minutes to millis
    private static final long DEFAULT_LONG_BREAK = 15*MILLIS_IN_MIN; // 5 minutes to millis

    private ArrayList<TimedInterval> intervals;
    private int numIntervals;
    private int currentInterval;
    private long currentIntervalDurationMillis;
    private String nameKey;

    //default pomodoro:  25/5/15
    public TimerSequence(Context mContext)
    {
        nameKey = mContext.getString(R.string.default_timer_sequence_key);
        intervals = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            addPomodoroShortBreakIntervals();
        addPomodoroLongBreakIntervals();
        numIntervals = intervals.size();
        currentInterval = 0;
        currentIntervalDurationMillis = DEFAULT_POMODORO;

    }

    private void addPomodoroShortBreakIntervals()
    {
        intervals.add(new TimedInterval(DEFAULT_POMODORO));
        intervals.add(new TimedInterval(DEFAULT_SHORT_BREAK));
    }

    private void addPomodoroLongBreakIntervals()
    {
        intervals.add(new TimedInterval(DEFAULT_POMODORO));
        intervals.add(new TimedInterval(DEFAULT_LONG_BREAK));
    }

    //
    public TimerSequence(String nameKey, Collection<TimedInterval> timedIntervalCollection)
    {
        this.nameKey = nameKey;
        intervals.addAll(timedIntervalCollection);
        numIntervals = intervals.size();
        currentInterval = 0;
        currentIntervalDurationMillis = intervals.get(0).getIntervalDurationMillis();
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
