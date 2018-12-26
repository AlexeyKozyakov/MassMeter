package com.dacker.adouble.massmeter.gui;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;

public interface Tab extends Serializable {

    void show(AppCompatActivity activity);
    boolean handleBackClick();
}
