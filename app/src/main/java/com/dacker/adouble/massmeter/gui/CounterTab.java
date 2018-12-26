package com.dacker.adouble.massmeter.gui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.dacker.adouble.massmeter.core.Counter;
import com.dacker.adouble.massmeter.db.Figure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CounterTab implements Tab, Serializable {

    private List<Step> steps = new ArrayList<>();

    private int index = 0;

    private Counter counter = new Counter();

    private AppCompatActivity activity;

    public CounterTab() {
        addStep(new FirstStepFragment());
        addStep(new SecondStepFragment());
        addStep(new ThirdStepFragment());
    }

    private void addStep(Step step) {
        Bundle args = new Bundle();
        args.putSerializable("tab", this);
        ((Fragment)step).setArguments(args);
        steps.add(step);
    }

    public Step getCurrentStep() {
        return steps.get(index);
    }

    public void nextStep() {
        ++index;
        show(activity);
    }

    public void setFigure(Figure figure) {
        counter.setFigure(figure);
    }

    @Override
    public void show(AppCompatActivity activity) {
        if (this.activity == null) {
            this.activity = activity;
        }
        steps.get(index).show(activity);
    }

    public boolean handleBackClick() {
        return getCurrentStep().handleBackClick();
    }
}
