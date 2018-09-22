package com.example.max.aptpomodorotracker;

import android.app.Application;

import com.example.max.aptpomodorotracker.db.TimerSequenceDatabase;
import com.example.max.aptpomodorotracker.model.TimerSequence;

public class AptPomodoroTracker extends Application {
        private AppExecutors mAppExecutors;

        @Override
        public void onCreate() {
            super.onCreate();

            mAppExecutors = new AppExecutors();
        }

        public TimerSequenceDatabase getDatabase() {
            return TimerSequenceDatabase.getInstance(this, mAppExecutors);
        }

        public DataRepository getRepository() {
            return DataRepository.getInstance(getDatabase());
        }
}