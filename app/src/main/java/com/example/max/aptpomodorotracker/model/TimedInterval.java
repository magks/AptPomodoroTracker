package com.example.max.aptpomodorotracker.model;

public interface TimedInterval  {
    long getIntervalDurationMillis();
    void setIntervalDurationMillis(long intervalDurationMillis);
    long getIntervalRemaining();
    void setIntervalRemaining(long intervalRemaining);
}
