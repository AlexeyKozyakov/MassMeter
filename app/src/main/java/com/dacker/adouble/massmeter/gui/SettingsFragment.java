package com.dacker.adouble.massmeter.gui;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dacker.adouble.massmeter.R;
import com.dacker.adouble.massmeter.util.FragmentReplacer;

import java.util.Locale;


public class SettingsFragment extends Fragment implements Tab{

    private ListView settingsView;
    private ListView languageSettings;

    @Override
    public void onCreate(Bundle saved) {
        super.onCreate(saved);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void show(AppCompatActivity activity) {
        FragmentReplacer.setFragment(activity, this);
    }

    @Override
    public boolean handleBackClick() {
        return false;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        settingsView = getActivity().findViewById(R.id.settings_view);
        languageSettings = getActivity().findViewById(R.id.language_view);
        String [] settingsNames = {
                "Language",
                "Color Scheme",
                "Customize tabs",
                "Tutorial",
                "Feedback",
                "Rate this app"
        };
        String [] languages = {
                "Russian",
                "English"
        };
        final SingleSelectionAdapter settingsAdapter = new SingleSelectionAdapter(getActivity());
        settingsAdapter.addAll(settingsNames);
        final SingleSelectionAdapter languageAdapter = new SingleSelectionAdapter(getActivity());
        languageAdapter.addAll(languages);
        settingsView.setAdapter(settingsAdapter);
        languageSettings.setAdapter(languageAdapter);
        settingsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                settingsAdapter.setPosition(position);
                if (position == 0) {
                    languageSettings.setVisibility(View.VISIBLE);
                    settingsView.setVisibility(View.GONE);
                }
            }
        });
        languageSettings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                languageAdapter.setPosition(position);
                if (position == 0) {
                    Resources res = getActivity().getResources();
                    DisplayMetrics dm = res.getDisplayMetrics();
                    android.content.res.Configuration conf = res.getConfiguration();
                    conf.setLocale(new Locale("ru")); // API 17+ only.
                    res.updateConfiguration(conf, dm);
                } else {
                    Resources res = getActivity().getResources();
                    DisplayMetrics dm = res.getDisplayMetrics();
                    android.content.res.Configuration conf = res.getConfiguration();
                    conf.setLocale(new Locale("en")); // API 17+ only.
                    res.updateConfiguration(conf, dm);
                }
            }
        });

    }


}
