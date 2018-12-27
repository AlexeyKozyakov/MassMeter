package com.dacker.adouble.massmeter.db;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;


import java.util.concurrent.Executors;

@Database(entities = {Category.class, Preset.class, Figure.class, Material.class, MaterialInfo.class, FigureInfo.class}, version = 1)
public abstract class ReferenceDataBase extends RoomDatabase {

    private static ReferenceDataBase INSTANCE;
    public abstract ReferenceDao referenceDao();

    public synchronized static ReferenceDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    private static ReferenceDataBase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                ReferenceDataBase.class,
                "reference-db")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance(context).referenceDao().insertAllCategories(Category.populateData(context));
                                getInstance(context).referenceDao().insertAllFigures(Figure.populateData(context));
                                getInstance(context).referenceDao().insertAllMaterials(Material.populateData(context));
                                getInstance(context).referenceDao().insertAllMaterialINFO(MaterialInfo.populateData(context));
                                getInstance(context).referenceDao().insertAllFigureINFO(FigureInfo.populateData(context));
                            }
                        });
                    }
                })
                .allowMainThreadQueries()
                .build();
    }

}