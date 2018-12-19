package com.dacker.adouble.massmeter.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.dacker.adouble.massmeter.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<Tab> tabs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeTittle();
        setContentView(R.layout.activity_main);
        initTabs();
        showTab(TabsIndex.COUNTER);
        initListeners();
    }

    private void removeTittle() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    private void initTabs() {
        tabs.add(new CounterFragment());
        tabs.add(new CalculatorFragment());
        tabs.add(new HistoryFragment());
        tabs.add(new ReferenceFragment());
        tabs.add(new SettingsFragment());
    }

    private void showTab(TabsIndex index) {
        tabs.get(index.ordinal()).show(this);
    }

    private void initListeners() {
        Button counterButton = findViewById(R.id.counterButton);
        Button calculatorButton = findViewById(R.id.calculatorButton);
        Button historyButton = findViewById(R.id.historyButton);
        Button referenceButton = findViewById(R.id.referenceButton);
        Button settingsButton = findViewById(R.id.settingsButton);
        counterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTab(TabsIndex.COUNTER);
            }
        });
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTab(TabsIndex.CALCULATOR);
            }
        });
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTab(TabsIndex.HISTORY);
            }
        });
        referenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTab(TabsIndex.REFERENCE);
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTab(TabsIndex.SETTINGS);
            }
        });
    }

}
