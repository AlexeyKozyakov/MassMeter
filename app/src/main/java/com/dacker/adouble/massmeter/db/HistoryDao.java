package com.dacker.adouble.massmeter.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface HistoryDao {
    @Insert
    public void addPreset(Preset preset);

    @Insert
    public void addSize(Size size);

    @Delete
    public void deletePreset(Preset preset);

    @Delete
    public void deleteSize(Size size);


    @Query("SELECT * FROM presets")                                                                                                     // ВОЗВРАЩАЕТ СПИСОК ВСЕХ ПРЕСЕТОВ
    public List<Preset> getAllPresets();

    @Query("SELECT * FROM Sizes INNER JOIN Presets ON Sizes.preset = Presets.id WHERE Presets.id = :id")                                // ВОЗВРАЩАЕТ СПИСОК РАЗМЕРОВ ПО ID ПРЕСЕТА
    public List<Size> getSizesFromPreset(int id);

    @Query("SELECT * FROM Presets WHERE Presets.id = :id")                                                                              // ВОЗВРАЩАЕТ ПРЕСЕТ ПО ID ПРЕСЕТА
    public Preset getPresetById(int id);
}
