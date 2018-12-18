package com.dacker.adouble.massmeter.core;

public class Figure {
    private int id;
    private String name;
    private String formula;

    public Figure(int id, String name, String formula) {
        this.id = id;
        this.name = name;
        this.formula = formula;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFormula() {
        return formula;
    }
}
