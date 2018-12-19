package com.dacker.adouble.massmeter.tabs;

import android.support.v7.app.AppCompatActivity;

import com.dacker.adouble.massmeter.gui.SettingsFragment;
import com.dacker.adouble.massmeter.util.FragmentReplacer;

public class SettingsTab implements Tab {

    private SettingsFragment fragment = new SettingsFragment();

    @Override
    public void show(AppCompatActivity activity) {
        FragmentReplacer.setFragment(activity, fragment);
    }

}
