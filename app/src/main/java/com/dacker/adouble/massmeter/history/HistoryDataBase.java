package com.dacker.adouble.massmeter.history;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Preset.class, Size.class}, version = 1)
public abstract class HistoryDataBase extends RoomDatabase {
    public abstract HistoryDao historyDao();
}
