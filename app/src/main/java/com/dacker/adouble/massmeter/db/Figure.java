package com.dacker.adouble.massmeter.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "Figures")
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

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "refetence")
    private String reference;

    public Figure(int id, String name, int catId, String formula, String vars, String image, String reference) {
        this.id = id;
        this.catId = catId;
        this.name = name;
        this.formula = formula;
        this.vars = vars;
        this.image = image;
        this.reference = reference;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
