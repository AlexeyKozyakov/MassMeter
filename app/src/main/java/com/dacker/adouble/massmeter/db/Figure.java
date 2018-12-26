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


@Entity(tableName = "Figures", indices = {@Index(value = {"name"}, unique = true)})
public class Figure {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "catId")
    private int catId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "formula")
    private String formula;

    @ColumnInfo(name = "vars")
    private String vars;



    public Figure(int id, String name, int catId, String formula, String vars) {
        this.id = id;
        this.catId = catId;
        this.name = name;
        this.formula = formula;
        this.vars = vars;
    }


    public int getId() {
        return id;
    }

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

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getVars() {
        return vars;
    }

    public void setVars(String vars) {
        this.vars = vars;
    }



    public static Figure[] populateData(Context context) {
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(context.getAssets().open("figures.csv")));
            List<Figure> figureList = new ArrayList<>();
            String [] row;
            while ((row = reader.readNext()) != null) {
                figureList.add(new Figure(Integer.valueOf(row[0]), row[1], Integer.valueOf(row[2]),  row[3], row[4]));
            }
            return figureList.toArray(new Figure[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}