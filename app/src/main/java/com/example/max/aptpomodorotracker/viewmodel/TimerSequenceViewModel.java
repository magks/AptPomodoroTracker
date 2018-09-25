package com.example.max.aptpomodorotracker.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.max.aptpomodorotracker.DataRepository;
import com.example.max.aptpomodorotracker.db.entity.TimerSequenceEntity;
import com.example.max.aptpomodorotracker.model.TimerSequence;

public class TimerSequenceViewModel extends AndroidViewModel {
    private LiveData<TimerSequenceEntity> mObservableTimerSequence;

    public ObservableField<TimerSequenceEntity> timerSequence = new ObservableField<>();

    private  int mTimerId;

    //private  LiveData<List<CommentEntity>> mObservableComments;

    public TimerSequenceViewModel(@NonNull Application application, DataRepository repository,
                            final int productId) {
        super(application);
        mTimerId = productId;

        //mObservableComments = repository.loadComments(mTimerId);
        mObservableTimerSequence = repository.loadTimerSequenceById(mTimerId);
    }

    public TimerSequenceViewModel(@NonNull Application application)
    {
        super(application);
    }


    public void loadObservables(DataRepository repository)
    {
        //mObservableComments = repository.loadComments(mTimerId);
        mObservableTimerSequence = repository.loadTimerSequenceById(mTimerId);
    }

    /**
     * Expose the LiveData Comments query so the UI can observe it.
     */
    /*
    public LiveData<List<CommentEntity>> getComments() {
        return mObservableComments;
    }
*/
    public LiveData<TimerSequenceEntity> getObservableProduct() {
        return mObservableTimerSequence;
    }

    public void setTimerSequence(TimerSequenceEntity product) {
        this.timerSequence.set(product);
    }

    public void setTimerId(int pid) { mTimerId = pid; }
}
