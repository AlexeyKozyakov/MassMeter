package com.dacker.adouble.massmeter.ref;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Materials")
public class Material {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "cat_id")
    private int cat_id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "density")
    private double density;

    @ColumnInfo(name = "refetence")
    private String reference;


    public Material(int id, String name, int cat_id, double density, String reference) {
        this.id = id;
        this.cat_id = cat_id;
        this.name = name;
        this.density = density;
        this.reference = reference;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
