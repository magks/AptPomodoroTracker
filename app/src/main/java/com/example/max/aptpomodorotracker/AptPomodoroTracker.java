package com.example.max.aptpomodorotracker;

import android.app.Application;

import com.example.max.aptpomodorotracker.db.TimerSequenceDatabase;


// Used to access database/repo singletons
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