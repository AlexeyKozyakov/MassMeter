package com.dacker.adouble.massmeter.ref;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Figures")
public class Figure {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "cat_id")
    private int cat_id;

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

    public Figure(int id, String name, int cat_id, String formula, String vars, String image, String reference) {
        this.id = id;
        this.cat_id = cat_id;
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
