package com.example.max.aptpomodorotracker.model;

public interface TimedInterval  {
/*
    long DEFAULT_TICK = 1000 ;
    long MILLIS_IN_SEC = 1000 ;
    long SECS_IN_MIN = 60 ;
    long MILLIS_IN_MIN = MILLIS_IN_SEC*SECS_IN_MIN;
    long DEFAULT_POMODORO = 25*MILLIS_IN_MIN ; //25 minutes to millis
    long DEFAULT_SHORT_BREAK = 5*MILLIS_IN_MIN; // 5 minutes to millis
    long DEFAULT_LONG_BREAK = 15*MILLIS_IN_MIN; // 5 minutes to millis
*/
    long getIntervalDurationMillis();
    void setIntervalDurationMillis(long intervalDurationMillis);
    long getIntervalRemaining();
    void setIntervalRemaining(long intervalRemaining);
}
