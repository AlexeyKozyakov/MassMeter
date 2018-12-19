package com.dacker.adouble.massmeter.tabs;

import android.support.v7.app.AppCompatActivity;

import com.dacker.adouble.massmeter.R;
import com.dacker.adouble.massmeter.gui.CounterFragment;
import com.dacker.adouble.massmeter.util.FragmentReplacer;

public class CounterTab implements Tab {

    private CounterFragment fragment = new CounterFragment();

    @Override
    public void show(AppCompatActivity activity) {
        FragmentReplacer.setFragment(activity, fragment);
    }

}
