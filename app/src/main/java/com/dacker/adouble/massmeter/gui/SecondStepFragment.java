package com.dacker.adouble.massmeter.gui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.dacker.adouble.massmeter.R;
import com.dacker.adouble.massmeter.core.Counter;
import com.dacker.adouble.massmeter.db.Figure;
import com.dacker.adouble.massmeter.db.FigureInfo;
import com.dacker.adouble.massmeter.db.Preset;
import com.dacker.adouble.massmeter.db.ReferenceDataBase;
import com.dacker.adouble.massmeter.util.AssetLoader;
import com.dacker.adouble.massmeter.util.FragmentReplacer;
import com.dacker.adouble.massmeter.util.UnitUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SecondStepFragment extends Fragment implements Step{

    private ListView countView;
    private Button nextButton;
    private Button saveButton;
    private ImageButton backButton;
    private ImageButton infoButton;
    private ImageView figureImage;
    private TextView result;
    private Spinner resultUnit;
    private CountAdapter adapter;
    private String [] vars;
    private View resultContainer;
    private int resultUnitPrev = UnitUtil.defaultLenUnit;

    @Override
    public void onCreate(Bundle saved) {
        super.onCreate(saved);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second_step, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        setFigureImage();
        setupCountView();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).previousCounterStep();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).nextCounterStep();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.validate()) {
                    Preset preset = new Preset();
                    preset.setFigure(Counter.getCounter().getFigure().getId());
                    preset.setId(ReferenceDataBase.getInstance(getActivity()).referenceDao().getAllPresets().size());
                    preset.setValues(Counter.getCounter().getValuesForPreset());
                    preset.setMaterial(-1);
                    ReferenceDataBase.getInstance(getActivity())
                            .referenceDao().addPreset(preset);
                }
            }
        });
    }

    private void initViews() {
        countView = getActivity().findViewById(R.id.count_view);
        nextButton = getActivity().findViewById(R.id.next_button_st2);
        saveButton = getActivity().findViewById(R.id.save_button);
        backButton = getActivity().findViewById(R.id.back_button);
        infoButton = getActivity().findViewById(R.id.info_button);
        figureImage = getActivity().findViewById(R.id.figure_image);
        result = getActivity().findViewById(R.id.volume_res);
        resultUnit = getActivity().findViewById(R.id.volume_unit);
        resultContainer = getActivity().findViewById(R.id.volum_results_layout);
    }

    private void setFigureImage() {
        Figure figure = Counter.getCounter().getFigure();
        int id = figure.getId();
        FigureInfo info = ReferenceDataBase.getInstance(getActivity()).referenceDao()
                .getFigureInfo(id);
        Bitmap bitmap = AssetLoader.loadImageForFigure(getActivity(), info);
        figureImage.setImageBitmap(bitmap);
    }

    private void setupCountView() {
        vars = Counter.getCounter().getFigure().getVars().split(" ");
        adapter = new CountAdapter(getActivity(), vars, result, resultContainer,resultUnit);
        countView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getArguments() == null) {
            setArguments(new Bundle());
        }
        adapter.saveState(getArguments());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getArguments() != null) {
            adapter.loadState(getArguments());
        }
    }


    @Override
    public void show(AppCompatActivity activity) {
        FragmentReplacer.setFragment(activity, this);
    }

    @Override
    public boolean handleBackClick() {
        ((MainActivity)getActivity()).previousCounterStep();
        return true;
    }

}
