package com.dacker.adouble.massmeter.core;

import com.dacker.adouble.massmeter.mxparser.Expression;

import java.util.Map;

public class Counter {
    private Material material;
    private Figure figure;
    private Expression expression;

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
        expression = new Expression(figure.getFormula());
    }

    public void setValues(Map<String, Double> values) {
        for (Map.Entry<String, Double> value : values.entrySet()) {
            expression.defineArgument(value.getKey(), value.getValue());
        }
    }
}
