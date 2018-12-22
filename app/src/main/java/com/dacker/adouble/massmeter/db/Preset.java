package com.dacker.adouble.massmeter.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Presets")
public class Preset {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "figure")
    private int figure;

    @ColumnInfo(name = "material")
    private int material;

    @ColumnInfo(name = "variables")
    private String values;

    public String getValues() { return values; }

    public void setValues(String values) { this.values = values; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFigure() {
        return figure;
    }

    public void setFigure(int figure) {
        this.figure = figure;
    }

    public int getMaterial() {
        return material;
    }

    public void setMaterial(int material) {
        this.material = material;
    }
}
