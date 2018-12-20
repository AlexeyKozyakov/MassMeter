package com.dacker.adouble.massmeter.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Materials")
public class Material {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "catId")
    private int catId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "density")
    private double density;

    @ColumnInfo(name = "refetence")
    private String reference;


    public Material(int id, String name, int catId, double density, String reference) {
        this.id = id;
        this.catId = catId;
        this.name = name;
        this.density = density;
        this.reference = reference;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
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

    public static Material[] populateData() {
        return new Material[] {
                new Material(0, "Ash", 2, 1.25, "Ash.txt"),
                new Material(1, "Cedar", 2, 1.80, "Cedar.txt"),
                new Material(2, "Fir", 2, 2.23, "Fir.txt"),
                new Material(3, "Walnut", 2, 1.1, "Walnut.txt"),
                new Material(4, "Pine", 2, 1.5, "Pine.txt"),
                new Material(5, "Steel", 3, 5.25, "Steel.txt"),
                new Material(5, "Bronze", 3, 4.15, "Ash.txt"),
                new Material(5, "Alluminium", 3, 3.45, "Ash.txt"),
        };
    }
}
