package com.example.max.aptpomodorotracker.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.max.aptpomodorotracker.AptPomodoroTracker;
import com.example.max.aptpomodorotracker.db.entity.TimerSequenceEntity;
import com.example.max.aptpomodorotracker.model.TimerSequence;

import java.util.List;
import android.arch.lifecycle.AndroidViewModel;

public class TimerSequenceListViewModel extends AndroidViewModel {
    private final MediatorLiveData<List<TimerSequenceEntity>> mObservableTimerSequences;

    public TimerSequenceListViewModel(Application application) {
        super(application);

        mObservableTimerSequences = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservableTimerSequences.setValue(null);

        LiveData<List<TimerSequenceEntity>> products = ((AptPomodoroTracker) application).getRepository()
                .getTimerSequences();

        // observe the changes of the products from the database and forward them
        mObservableTimerSequences.addSource(products, mObservableTimerSequences::setValue);
    }

    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    public LiveData<List<TimerSequenceEntity>> getTimerSequences() {
        return mObservableTimerSequences;
    }
}
