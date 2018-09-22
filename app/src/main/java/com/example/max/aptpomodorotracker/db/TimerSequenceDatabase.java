package com.example.max.aptpomodorotracker;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {TimerSequence.class,TimedInterval.class}, version = 1, exportSchema = true)
@TypeConverters({Converters.class})
public abstract class TimerSequenceDatabase extends RoomDatabase {
    public abstract TimerSequenceDao timerSequenceDaoAccess();

}
