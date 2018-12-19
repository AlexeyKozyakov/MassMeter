package com.dacker.adouble.massmeter.tabs;

import android.support.v7.app.AppCompatActivity;

import com.dacker.adouble.massmeter.gui.HistoryFragment;
import com.dacker.adouble.massmeter.util.FragmentReplacer;

public class HistoryTab implements Tab {

    private HistoryFragment fragment = new HistoryFragment();

    @Override
    public void show(AppCompatActivity activity) {
        FragmentReplacer.setFragment(activity, fragment);
    }

}
