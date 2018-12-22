package com.dacker.adouble.massmeter;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.dacker.adouble.massmeter.db.ReferenceDataBase;

public class MainActivity extends AppCompatActivity {

    public static ReferenceDataBase dataBase;
    public static HistoryDataBase historyDataBase;

    private BlankFragment fragment = new BlankFragment();

    private Settings settings = null;

    private Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        dataBase = Room.databaseBuilder(getApplicationContext(), ReferenceDataBase.class,"refdb").build();
        historyDataBase = Room.databaseBuilder(getApplicationContext(), HistoryDataBase.class,"historydb").allowMainThreadQueries().build();


        System.out.println(getDatabasePath("hystorydb").getAbsolutePath());

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
