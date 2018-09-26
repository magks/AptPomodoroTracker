package com.example.max.aptpomodorotracker.db;

import com.example.max.aptpomodorotracker.db.entity.TimerSequenceEntity;

//Populate database on first start
public class DataGenerator {

    public static TimerSequenceEntity generateDefaultPomodoro() {
        TimerSequenceEntity defaultPomodoroSequence = new TimerSequenceEntity();
        for (int i = 0; i < 3; i++)
            defaultPomodoroSequence.addPomodoroShortBreakIntervals();
        defaultPomodoroSequence.addPomodoroLongBreakIntervals();
        defaultPomodoroSequence.setNameKey("Pomodoro Timer");
        return defaultPomodoroSequence;
    }

}
