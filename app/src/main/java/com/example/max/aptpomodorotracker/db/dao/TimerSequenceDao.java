package com.example.max.aptpomodorotracker;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

public abstract class TimerSequenceDao {

    @Insert
    public abstract void _insertTimerSequence(TimerSequence timerSequence);

    @Insert
    public abstract void _insertTimedIntervalList(List<TimedInterval> timedIntervalList);

    @Query("SELECT * FROM timer_sequences WHERE timer_seq_id =:tsid")
    public abstract TimerSequence getTimerSequence(int tsid);

    @Query("SELECT * FROM timed_intervals WHERE tsid =:tsid")
    public abstract List<TimedInterval> getTimedIntervalList(int tsid);


    @Query("SELECT * FROM timer_sequences")
    public abstract List<TimerSequence> getAll();

    @Query("SELECT * FROM timer_sequences WHERE timer_seq_id IN (:timerIDs)")
    public abstract List<TimerSequence> loadAllByIds(int[] timerIDs);

    @Query("SELECT * FROM timer_sequences " +
            "WHERE timer_name LIKE :timerSequenceName LIMIT 1")
    public abstract TimerSequence findByName(String timerSequenceName);


   // @Query("SELECT * FROM timer_sequences ORDER BY ")
    //public abstract List<TimerSequence> loadAllByDateLastUsed(int[] timerIDs);

    @Insert
    public abstract void insertAll(TimerSequence... timerSequences);

    @Delete
    public abstract void delete(TimerSequence timerSequence);

    public void insertTimerSequenceWithIntervals(TimerSequence timerSequence)
    {
        List<TimedInterval> intervals = timerSequence.getIntervals();
        for (int i = 0; i < intervals.size(); i++) {
            intervals.get(i).timerSequenceID = (timerSequence.tsid);
        }
        _insertTimedIntervalList(intervals);
        _insertTimerSequence(timerSequence);

    }

    public TimerSequence getTImerSequenceWithIntervals(int tsid)
    {
        TimerSequence timerSequence = getTimerSequence(tsid);
        List<TimedInterval> intervals = getTimedIntervalList(tsid);
        timerSequence.setIntervals(intervals);
        return timerSequence;

    }
}
