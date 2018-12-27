package com.dacker.adouble.massmeter.core;

import com.dacker.adouble.massmeter.core.mxparser.Expression;
import com.dacker.adouble.massmeter.db.Figure;
import com.dacker.adouble.massmeter.db.Material;

import java.util.Map;

public class Counter {
    private Material material;
    private Figure figure;
    private Expression expression;
    private Map<String, Double> values;
    private static Counter COUNTER = new Counter();

    private Counter() {}

    public static Counter getCounter() {
        return COUNTER;
    }

    public double countMass() {
        return expression.calculate() * material.getDensity();
    }

    public double countVolume() {
        return expression.calculate();
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public void setValues(Map<String, Double> values) {
        this.values = values;
        expression = new Expression(figure.getFormula());
        for (Map.Entry<String, Double> value : values.entrySet()) {
            expression.defineArgument(value.getKey(), value.getValue());
        }
    }

    public Material getMaterial() {
        return material;
    }

    public Figure getFigure() {
        return figure;
    }

    public Map<String, Double> getValues() {
        return values;
    }

    public boolean hasFigure() {
        return figure != null;
    }

    public boolean hasMaterial() {
        return material != null;
    }

    public boolean hasValues() {
        if (values == null)
            return false;
        return values.isEmpty();
    }

    public String getValuesForPreset() {
        String [] vars = figure.getVars().split(" ");
        StringBuilder builder = new StringBuilder();
        for (String var : vars) {
            builder.append(values.get(var) + " ");
        }
        return builder.toString().substring(0, builder.toString().length() - 1);
    }
}
