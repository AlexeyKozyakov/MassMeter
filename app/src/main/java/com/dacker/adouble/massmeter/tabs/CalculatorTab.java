package com.dacker.adouble.massmeter.tabs;

import android.support.v7.app.AppCompatActivity;

import com.dacker.adouble.massmeter.gui.CalculatorFragment;
import com.dacker.adouble.massmeter.util.FragmentReplacer;

public class CalculatorTab implements Tab {

    private CalculatorFragment fragment = new CalculatorFragment();

    @Override
    public void show(AppCompatActivity activity) {
        FragmentReplacer.setFragment(activity, fragment);
    }

}
