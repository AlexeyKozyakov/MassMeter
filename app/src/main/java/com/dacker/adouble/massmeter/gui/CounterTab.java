package com.dacker.adouble.massmeter.gui;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.dacker.adouble.massmeter.core.Counter;

import java.util.ArrayList;
import java.util.List;

public class CounterTab implements Tab{

    private List<Step> steps = new ArrayList<>();

    private int index = 0;

    private Counter counter = new Counter();

    public CounterTab() {
        steps.add(new FirstStepFragment());
        steps.add(new SecondStepFragment());
        steps.add(new ThirdStepFragment());
    }

    @Override
    public void show(AppCompatActivity activity) {
        steps.get(index).show(activity);
    }


}
