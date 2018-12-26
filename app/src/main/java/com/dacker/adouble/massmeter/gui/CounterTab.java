package com.dacker.adouble.massmeter.gui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CounterTab implements Tab {

    private List<Step> steps = new ArrayList<>();

    private int index = 0;

    public CounterTab() {
        steps.add(new FirstStepFragment());
        steps.add(new SecondStepFragment());
        steps.add(new ThirdStepFragment());
    }

    public Step getCurrentStep() {
        return steps.get(index);
    }

    public void nextStep(AppCompatActivity activity) {
        ++index;
        show(activity);
    }

    @Override
    public void show(AppCompatActivity activity) {
        steps.get(index).show(activity);
    }

    public boolean handleBackClick() {
        return getCurrentStep().handleBackClick();
    }
}
