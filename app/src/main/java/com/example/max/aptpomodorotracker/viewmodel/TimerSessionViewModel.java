package com.example.max.aptpomodorotracker.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.example.max.aptpomodorotracker.AptPomodoroTracker;
import com.example.max.aptpomodorotracker.db.entity.TimerSequenceEntity;

import java.util.List;

public class TimerSessionViewModel extends AndroidViewModel  {

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<TimerSequenceEntity>> mObservableTimerSequences;

    public LiveData<String> timeRemaining;

    public TimerSessionViewModel(@NonNull Application application) {
        super(application);
        mObservableTimerSequences = new MediatorLiveData<>();
        //default to null until timers are loaded from the database
        mObservableTimerSequences.setValue(null);
        LiveData<List<TimerSequenceEntity>> timerSequences = ((AptPomodoroTracker) application)
                                                                .getRepository()
                                                                .getTimerSequences();
        // observe the changes of the products from the database and forward them
        mObservableTimerSequences.addSource(timerSequences, mObservableTimerSequences::setValue);


    }
    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    public LiveData<List<TimerSequenceEntity>> getTimerSequences() {
        return mObservableTimerSequences;
    }

}
