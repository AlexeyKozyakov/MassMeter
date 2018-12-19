package com.dacker.adouble.massmeter.tabs;

import android.support.v7.app.AppCompatActivity;

import com.dacker.adouble.massmeter.gui.ReferenceFragment;
import com.dacker.adouble.massmeter.util.FragmentReplacer;

public class ReferenceTab implements Tab {

    private ReferenceFragment fragment = new ReferenceFragment();

    @Override
    public void show(AppCompatActivity activity) {
        FragmentReplacer.setFragment(activity, fragment);
    }

}
