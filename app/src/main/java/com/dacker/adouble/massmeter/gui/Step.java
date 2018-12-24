package com.dacker.adouble.massmeter.gui;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

public interface Step {
    void show(AppCompatActivity activity);
    boolean handleBackClick();
}
