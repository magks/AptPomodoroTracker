package com.example.max.aptpomodorotracker;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class TimerSequenceEntity {

    @PrimaryKey
    private int timerID;

    @ColumnInfo(name = "date_last_used")
    private Date dateLastUsed;

    @ColumnInfo(name = "timer_name")
    private String timerName;

    @ColumnInfo(name = "timer_sequence")
    private TimerSequence timerSequence;

    public int getTimerID() {
        return timerID;
    }

    public void setTimerID(int timerID) {
        this.timerID = timerID;
    }

    public Date getDateLastUsed() {
        return dateLastUsed;
    }

    public void setDateLastUsed(Date dateLastUsed) {
        this.dateLastUsed = dateLastUsed;
    }

    public String getTimerName() {
        return timerName;
    }

    public void setTimerName(String timerName) {
        this.timerName = timerName;
    }

    public TimerSequence getTimerSequence() {
        return timerSequence;
    }

    public void setTimerSequence(TimerSequence timerSequence) {
        this.timerSequence = timerSequence;
    }
}
