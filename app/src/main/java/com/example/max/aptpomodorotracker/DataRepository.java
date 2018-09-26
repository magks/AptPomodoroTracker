package com.example.max.aptpomodorotracker;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.example.max.aptpomodorotracker.db.TimerSequenceDatabase;
import com.example.max.aptpomodorotracker.db.entity.TimedIntervalEntity;
import com.example.max.aptpomodorotracker.db.entity.TimerSequenceEntity;

import java.util.List;

// singleton handling work of database access via DAOs
public class DataRepository {

    private static DataRepository sInstance;

    private final TimerSequenceDatabase mDatabase;
    private MediatorLiveData<List<TimerSequenceEntity>> mObservableTimerSequences;

    private DataRepository(final TimerSequenceDatabase database) {
        mDatabase = database;
        mObservableTimerSequences = new MediatorLiveData<>();

        mObservableTimerSequences.addSource(mDatabase.timerSequenceDao().loadAllTimerSequences(),
                timerSequenceEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        for(TimerSequenceEntity timerSequenceEntity: timerSequenceEntities)
                        {
                            List<TimedIntervalEntity> intervals = mDatabase.timerSequenceDao().getTimedIntervalList(timerSequenceEntity.tsid);
                            timerSequenceEntity.setIntervals( intervals );
                        }

                        mObservableTimerSequences.postValue(timerSequenceEntities);
                    }
                });
    }

    public static DataRepository getInstance(final TimerSequenceDatabase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }

    /**
     * Get the list of timer sequences from the database and get notified when the data changes.
     */
    public LiveData<List<TimerSequenceEntity>> getTimerSequences() {
        return mObservableTimerSequences;
    }


    public LiveData<TimerSequenceEntity> loadTimerSequenceById(final int mTimerId) {
        return mDatabase.timerSequenceDao().loadTimerSequenceEntity(mTimerId);
    }

}
