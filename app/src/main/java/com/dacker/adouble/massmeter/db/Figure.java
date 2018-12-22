package com.dacker.adouble.massmeter.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;


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



    public static Figure[] populateData() {
        return new Figure[] {
                new Figure(0, "Cube", 0, "x*x*x", "x"),
                new Figure(1, "Parallelipipidus", 0, "h*a*b", "h a b"),
                new Figure(2, "Figure1", 0,"4/3*pi*r", "r"),
                new Figure(3, "Figure2", 0,"4/3*pi*r", "r"),
                new Figure(4, "Figure3", 1,"4/3*pi*r", "r"),
                new Figure(5, "Figure4", 1,"4/3*pi*r", "r"),
                new Figure(6, "Figure5", 1,"4/3*pi*r", "r"),
                new Figure(7, "Figure16", 1,"4/3*pi*r", "r"),
                new Figure(8, "Figure11", 5,"4/3*pi*r", "r"),
                new Figure(9, "Figure22", 5,"4/3*pi*r", "r"),
                new Figure(10, "Figure31", 5,"4/3*pi*r", "r"),
                new Figure(11, "Figure44", 5,"4/3*pi*r", "r"),
                new Figure(12, "Figure56", 5,"4/3*pi*r", "r")
        };
    }
}