package com.example.max.aptpomodorotracker;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

public interface TimerSequenceEntityDao {
    @Query("SELECT * FROM timersequenceentity")
    List<TimerSequenceEntity> getAll();

    @Query("SELECT * FROM timersequenceentity WHERE timerID IN (:timerIDs)")
    List<TimerSequenceEntity> loadAllByIds(int[] timerIDs);

    @Query("SELECT * FROM timersequenceentity " +
            "WHERE timer_name LIKE :timerSequenceName LIMIT 1")
    TimerSequenceEntity findByName(String timerSequenceName);

    @Query("SELECT * FROM timersequenceentity ORDER BY ")
    List<TimerSequenceEntity> loadAllByDateLastUsed(int[] timerIDs);




    @Insert
    void insertAll(TimerSequenceEntity... timerSequenceEntities);

    @Delete
    void delete(TimerSequenceEntity timerSequenceEntity);
}
