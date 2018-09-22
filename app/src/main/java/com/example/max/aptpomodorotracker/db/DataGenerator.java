package com.example.max.aptpomodorotracker.db;

import com.example.max.aptpomodorotracker.db.entity.TimedIntervalEntity;
import com.example.max.aptpomodorotracker.db.entity.TimerSequenceEntity;

public class DataGenerator {

    public static TimerSequenceEntity generateDefaultPomodoro() {
        TimerSequenceEntity defaultPomodoroSequence = new TimerSequenceEntity();
        for (int i = 0; i < 3; i++)
            defaultPomodoroSequence.addPomodoroShortBreakIntervals();
        defaultPomodoroSequence.addPomodoroLongBreakIntervals();
        defaultPomodoroSequence.setNameKey("Pomodoro Timer");
        return defaultPomodoroSequence;
    }

    /*
    public static List<CommentEntity> generateCommentsForProducts(
            final List<ProductEntity> products) {
        List<CommentEntity> comments = new ArrayList<>();
        Random rnd = new Random();

        for (Product product : products) {
            int commentsNumber = rnd.nextInt(5) + 1;
            for (int i = 0; i < commentsNumber; i++) {
                CommentEntity comment = new CommentEntity();
                comment.setProductId(product.getId());
                comment.setText(COMMENTS[i] + " for " + product.getName());
                comment.setPostedAt(new Date(System.currentTimeMillis()
                        - TimeUnit.DAYS.toMillis(commentsNumber - i) + TimeUnit.HOURS.toMillis(i)));
                comments.add(comment);
            }
        }

        return comments;
    }
       */
}
