package com.example.max.aptpomodorotracker.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.example.max.aptpomodorotracker.AppExecutors;
import com.example.max.aptpomodorotracker.db.converter.Converters;
import com.example.max.aptpomodorotracker.db.dao.TimerSequenceDao;
import com.example.max.aptpomodorotracker.db.entity.TimedIntervalEntity;
import com.example.max.aptpomodorotracker.db.entity.TimerSequenceEntity;

import java.util.List;

@Database(entities = {TimerSequenceEntity.class,TimedIntervalEntity.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class TimerSequenceDatabase extends RoomDatabase {

    private static TimerSequenceDatabase sInstance;

    @VisibleForTesting
    public static final String DATABASE_NAME = "basic-sample-db";

    public abstract TimerSequenceDao timerSequenceDao();

   // public abstract TimedIntervalDao commentDao();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static TimerSequenceDatabase getInstance(final Context context, final AppExecutors executors) {
        if (sInstance == null) {
            synchronized (TimerSequenceDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext(), executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static TimerSequenceDatabase buildDatabase(final Context appContext,
                                             final AppExecutors executors) {
        return Room.databaseBuilder(appContext, TimerSequenceDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(() -> {
                            // Add a delay to simulate a long-running operation
                            addDelay();
                            // Generate the data for pre-population
                            TimerSequenceDatabase database = TimerSequenceDatabase.getInstance(appContext, executors);
                            TimerSequenceEntity defaultSequence = DataGenerator.generateDefaultPomodoro();
                          //  List<CommentEntity> comments =
                             //       DataGenerator.generateCommentsForProducts(products);

                           //insertData(database, products, comments);
                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        });
                    }
                }).build();
    }

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    private static void insertData(final TimerSequenceDatabase database, final List<TimerSequenceEntity> timerSequences,
                                   final List<TimedIntervalEntity> comments) {
        database.runInTransaction(() -> {
            database.timerSequenceDao().insertAll(timerSequences);
            //database.commentDao().insertAll(comments);
        });
    }

    private static void insertDefaultData(final TimerSequenceDatabase database,
                                          final TimerSequenceEntity defaultPomodoro)
    {
        database.runInTransaction(() -> {
            database.timerSequenceDao().insertAll(defaultPomodoro);
            //database.commentDao().insertAll(comments);
        });
    }

    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
        }
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

}
