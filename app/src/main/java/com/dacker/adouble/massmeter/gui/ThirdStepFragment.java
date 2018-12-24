package com.dacker.adouble.massmeter.gui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dacker.adouble.massmeter.R;
import com.dacker.adouble.massmeter.util.FragmentReplacer;

public class ThirdStepFragment extends Fragment implements Step{

    private CounterTab tab;

    @Override
    public void onCreate(Bundle saved) {
        super.onCreate(saved);
        tab = (CounterTab) getArguments().get("tab");
        getArguments().clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_third_step, container, false);
    }

    @Override
    public void show(AppCompatActivity activity) {
        FragmentReplacer.setFragment(activity, this);
    }

    @Override
    public boolean handleBackClick() {
        return false;
    }
}
