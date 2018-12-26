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
import java.util.Scanner;

@Entity(tableName = "Categories", indices = {@Index(value = {"name"}, unique = true)})
public class Category {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "type")
    private String type;

    public Category(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static Category[] populateData(Context context) {
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(context.getAssets().open("categories.csv")));
            List<Category> categoryList = new ArrayList<>();
            String [] row;
            while ((row = reader.readNext()) != null) {
                categoryList.add(new Category(Integer.valueOf(row[0]), row[1], row[2]));
            }
            return categoryList.toArray(new Category[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}