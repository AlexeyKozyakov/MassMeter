package com.dacker.adouble.massmeter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private BlankFragment fragment = new BlankFragment();

    private Settings settings = null;

    private Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().
                add(R.id.fragment_container, fragment)
                .commit();
        settingsButton = findViewById(R.id.btn4);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (settings == null) {
                    settings = new Settings();
                }
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragment_container, settings)
                        .commit();
            }
        });

    }
}
