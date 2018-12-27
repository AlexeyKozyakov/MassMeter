package com.dacker.adouble.massmeter.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dacker.adouble.massmeter.core.Counter;
import com.dacker.adouble.massmeter.db.Preset;
import com.dacker.adouble.massmeter.db.ReferenceDataBase;

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

    public void previousStep(AppCompatActivity activity) {
        --index;
        show(activity);
    }

    public void openWithPreset(Preset preset, AppCompatActivity activity) {
        if (((SecondStepFragment)steps.get(1)).getArguments() == null) {
            ((SecondStepFragment)steps.get(1)).setArguments(new Bundle());
        }
        Counter.getCounter().setFigure(ReferenceDataBase.getInstance(activity).referenceDao()
                .getFigureById(preset.getFigure()));
        String [] vars = Counter.getCounter().getFigure().getVars().split(" ");
        String [] values = preset.getValues().split(" ");
        for (int i = 0; i < vars.length; i++) {
            ((SecondStepFragment)steps.get(1)).getArguments().putString(vars[i], values[i]);
        }
        ((SecondStepFragment)steps.get(1)).show(activity);
        if (preset.getMaterial() != -1) {
            Counter.getCounter().setMaterial(ReferenceDataBase.getInstance((activity)).referenceDao()
            .getMaterialById(preset.getMaterial()));
            index = 2;
            show(activity);
        } else {
            index = 1;
            show(activity);
        }
    }

    @Override
    public void show(AppCompatActivity activity) {
        steps.get(index).show(activity);
    }

    public boolean handleBackClick() {
        return getCurrentStep().handleBackClick();
    }
}
