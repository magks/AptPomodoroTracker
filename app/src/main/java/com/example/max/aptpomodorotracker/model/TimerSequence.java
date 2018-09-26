package com.example.max.aptpomodorotracker.model;

import java.util.ArrayList;

public interface TimerSequence {
    ArrayList<TimedInterval> getIntervals();
    //void setIntervals(Collection<TimedInterval> intervals);
    int getCurrentInterval();
    void setCurrentInterval(int currentInterval);
    int getNumIntervals();
    void setNumIntervals(int numIntervals);
    String getNameKey();
    void setNameKey(String nameKey);
    int getTimerId();
    int setTimerId();
}
