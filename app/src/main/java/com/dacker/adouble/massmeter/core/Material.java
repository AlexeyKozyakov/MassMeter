package com.dacker.adouble.massmeter.core;

public class Material {
    private int id;
    private String name;
    private double density;

    public Material(int id, String name, double density) {
        this.id = id;
        this.name = name;
        this.density = density;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getDensity() {
        return density;
    }
}
