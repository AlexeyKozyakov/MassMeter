package com.dacker.adouble.massmeter.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.dacker.adouble.massmeter.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<Tab> tabs = new ArrayList<>();
    private ImageButton pressedButton;
    private int pressedButtonRes;
    ImageButton counterButton;
    ImageButton calculatorButton;
    ImageButton historyButton;
    ImageButton referenceButton;
    ImageButton settingsButton;
    Tab currentTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeTittle();
        setContentView(R.layout.activity_main);
        setupButtons();
        initTabs();
        initListeners();
        showFirstTab();
    }

    private void removeTittle() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    private void initTabs() {
        tabs.add(new CounterTab());
        tabs.add(new CalculatorFragment());
        tabs.add(new HistoryFragment());
        tabs.add(new ReferenceFragment());
        tabs.add(new SettingsFragment());
    }

    private void setupButtons() {
        counterButton = findViewById(R.id.counterButton);
        calculatorButton = findViewById(R.id.calculatorButton);
        historyButton = findViewById(R.id.historyButton);
        referenceButton = findViewById(R.id.referenceButton);
        settingsButton = findViewById(R.id.settingsButton);
    }

    private void showTab(TabsIndex index) {
        currentTab = getTab(index);
        currentTab.show(this);
    }

    private Tab getTab(TabsIndex index) {
        return tabs.get(index.ordinal());
    }

    private void initListeners() {
        counterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPressedButton(v);
                ((ImageButton)v).setImageResource(R.drawable.counter_white_icon);
                pressedButtonRes = R.drawable.counter_icon;
                showTab(TabsIndex.COUNTER);
            }
        });
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPressedButton(v);
                ((ImageButton)v).setImageResource(R.drawable.calculator_white_icon);
                pressedButtonRes = R.drawable.calculator_icon;
                showTab(TabsIndex.CALCULATOR);
            }
        });
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPressedButton(v);
                ((ImageButton)v).setImageResource(R.drawable.history_white_icon);
                pressedButtonRes = R.drawable.history_icon;
                showTab(TabsIndex.HISTORY);
            }
        });
        referenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPressedButton(v);
                ((ImageButton)v).setImageResource(R.drawable.referense_white_icon);
                pressedButtonRes = R.drawable.referense_icon;
                showTab(TabsIndex.REFERENCE);
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPressedButton(v);
                ((ImageButton)v).setImageResource(R.drawable.settings_white_icon);
                pressedButtonRes = R.drawable.settings_icon;
                showTab(TabsIndex.SETTINGS);
            }
        });
    }

    private void setPressedButton(View buttonView) {
        if (pressedButton != null && pressedButton != buttonView) {
            pressedButton.setBackgroundColor(0xFFFFFFFF);
            buttonView.setBackgroundColor(0xFF9E9E9E);
            pressedButton.setImageResource(pressedButtonRes);
        } else if (pressedButton == null) {
            buttonView.setBackgroundColor(0xFF9E9E9E);
        }
        pressedButton = (ImageButton) buttonView;
    }

    private void showFirstTab() {
        setPressedButton(counterButton);
        counterButton.setImageResource(R.drawable.counter_white_icon);
        pressedButtonRes = R.drawable.counter_icon;
        showTab(TabsIndex.COUNTER);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && currentTab.handleBackClick()) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void nextCounterStep() {
        ((CounterTab)getTab(TabsIndex.COUNTER)).nextStep(this);
    }
}
