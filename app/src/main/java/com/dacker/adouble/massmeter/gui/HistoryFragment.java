package com.dacker.adouble.massmeter.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.dacker.adouble.massmeter.R;
import com.dacker.adouble.massmeter.db.Figure;
import com.dacker.adouble.massmeter.db.Preset;
import com.dacker.adouble.massmeter.db.ReferenceDao;
import com.dacker.adouble.massmeter.db.ReferenceDataBase;
import com.dacker.adouble.massmeter.util.FragmentReplacer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HistoryFragment extends Fragment implements Tab{

    private Button deletePresetButton;
    private Button openPresetButton;
    private ListView presetView;
    private List<Preset> presets;
    private List<String> presetsNames;
    private SingleSelectionAdapter adapter;
    private ReferenceDao referenceDao;

    @Override
    public void onCreate(Bundle saved) {
        super.onCreate(saved);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
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
        referenceDao = ReferenceDataBase.getInstance(getActivity()).referenceDao();
        presets = ReferenceDataBase.getInstance(getActivity())
                .referenceDao().getAllPresets();
        presetsNames = new ArrayList<>();
        for (Preset preset : presets) {
            int figureId = preset.getFigure();
            int materialId = preset.getMaterial();
            String figureName = referenceDao.getFigureById(figureId).getName();
            String materialName = "";
            if (materialId != -1) {
                materialName = referenceDao.getMaterialById(materialId).getName();
            }
            presetsNames.add(preset.getId()
                    + ". " + figureName
                    + ", " + materialName);
        }
        deletePresetButton = getActivity().findViewById(R.id.delete_preset);
        openPresetButton = getActivity().findViewById(R.id.open_preset);
        presetView = getView().findViewById(R.id.preset_view);
        setupPresetView();
        setupDeleteButton();
        setupOpenButton();
    }

    private void setupPresetView() {
        adapter = new SingleSelectionAdapter(getActivity());
        adapter.addAll(presetsNames);
        presetView.setAdapter(adapter);
        presetView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setPosition(position);
            }
        });
    }

    private void setupOpenButton() {
        openPresetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).openCounterWithPreset(presets.get(adapter.getPosition()));
            }
        });
    }

    private void setupDeleteButton() {
        deletePresetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.selected()) {
                    Preset selectedPreset = presets.get(adapter.getPosition());
                    referenceDao.deletePreset(selectedPreset);
                    presetsNames.remove(adapter.getPosition());
                    presets.remove(adapter.getPosition());
                    adapter.clear();
                    adapter.addAll(presetsNames);
                }
            }
        });
    }
}
