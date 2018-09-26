package com.example.max.aptpomodorotracker.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.max.aptpomodorotracker.db.entity.TimedIntervalEntity;
import com.example.max.aptpomodorotracker.db.entity.TimerSequenceEntity;

import java.util.Collection;
import java.util.List;

@Dao
public abstract class TimerSequenceDao {

    @Insert
    public abstract void _insertTimerSequenceEntity(TimerSequenceEntity TimerSequenceEntity);

    @Insert
    public abstract void _insertTimedIntervalList(List<TimedIntervalEntity> timedIntervalList);

    @Query("SELECT * FROM timer_sequences WHERE timer_seq_id =:tsid")
    public abstract LiveData<TimerSequenceEntity> loadTimerSequenceEntity(int tsid);

    @Query("SELECT * FROM timer_sequences WHERE timer_seq_id =:tsid")
    public abstract TimerSequenceEntity getTimerSequenceEntity(int tsid);

    @Query("SELECT * FROM timed_intervals WHERE tsid =:tsid")
    public abstract List<TimedIntervalEntity> getTimedIntervalList(int tsid);


    @Query("SELECT * FROM timer_sequences")
    public abstract LiveData<List<TimerSequenceEntity>> loadAllTimerSequences();

    @Query("SELECT * FROM timer_sequences WHERE timer_seq_id IN (:timerIDs)")
    public abstract List<TimerSequenceEntity> loadAllByIds(int[] timerIDs);

    @Query("SELECT * FROM timer_sequences " +
            "WHERE timer_name LIKE :TimerSequenceEntityName LIMIT 1")
    public abstract TimerSequenceEntity findByName(String TimerSequenceEntityName);


   // @Query("SELECT * FROM timer_sequences ORDER BY ")
    //public abstract List<TimerSequenceEntity> loadAllByDateLastUsed(int[] timerIDs);

    @Insert
    public abstract void insertAll(TimerSequenceEntity... timerSequenceEntities);

    @Insert
    public abstract void insertAll(Collection<TimerSequenceEntity> timerSequenceEntities);

    @Delete
    public abstract void delete(TimerSequenceEntity TimerSequenceEntity);

    public void insertTimerSequenceEntityWithIntervals(TimerSequenceEntity timerSequenceEntity)
    {
        List<TimedIntervalEntity> intervals = timerSequenceEntity.getIntervals();
        for (int i = 0; i < intervals.size(); i++) {
            intervals.get(i).timerSequenceID = (timerSequenceEntity.tsid);
        }
        _insertTimedIntervalList(intervals);
        _insertTimerSequenceEntity(timerSequenceEntity);

    }

    public TimerSequenceEntity getTimerSequenceEntityWithIntervals(int tsid)
    {
        TimerSequenceEntity timerSequenceEntity = getTimerSequenceEntity(tsid);
        List<TimedIntervalEntity> intervals = getTimedIntervalList(tsid);
        timerSequenceEntity.setIntervals(intervals);
        return timerSequenceEntity;

    }

}
