package com.dacker.adouble.massmeter.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Category.class, Figure.class, Material.class}, version = 1)
public abstract class ReferenceDataBase extends RoomDatabase {

    public abstract ReferenceDao mydao();

}
