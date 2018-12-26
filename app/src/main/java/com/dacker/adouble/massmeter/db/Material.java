package com.dacker.adouble.massmeter.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "Materials", indices = {@Index(value = {"name"}, unique = true)})
public class Material {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "catId")
    private int catId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "density")
    private double density;

    public Material(int id, String name, int catId, double density) {
        this.id = id;
        this.catId = catId;
        this.name = name;
        this.density = density;
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

    public static Material[] populateData(Context context) {
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(context.getAssets().open("materials.csv")));
            List<Material> materialList = new ArrayList<>();
            String [] row;
            while ((row = reader.readNext()) != null) {
                materialList.add(new Material(Integer.valueOf(row[0]), row[1], Integer.valueOf(row[2]), Double.valueOf(row[3])));
            }
            return materialList.toArray(new Material[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}