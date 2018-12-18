package com.dacker.adouble.massmeter.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.dacker.adouble.massmeter.R;
import com.dacker.adouble.massmeter.core.Counter;
import com.dacker.adouble.massmeter.core.Figure;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private BlankFragment fragment = new BlankFragment();

    private SettingsFragment settings = null;

    private Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        Figure rectangle = new Figure(1, "rect", "a * b * c");
        Counter counter = new Counter();
        Map<String, Double> values = new HashMap<>();
        values.put("a", 2.0);
        values.put("b", 5.0);
        values.put("c", 10.0);
        counter.setFigure(rectangle);
        counter.setValues(values);
        Button button = findViewById(R.id.btn0);
        button.setText(String.valueOf(counter.countVolume()));




        //        getSupportFragmentManager().beginTransaction().
//                add(R.id.fragment_container, fragment)
//                .commit();
//        settingsButton = findViewById(R.id.btn4);
//        settingsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (settings == null) {
//                    settings = new SettingsFragment();
//                }
//                getSupportFragmentManager().beginTransaction().
//                        replace(R.id.fragment_container, settings)
//                        .commit();
//            }
//        });

    }
}
