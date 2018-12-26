package com.dacker.adouble.massmeter.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import com.dacker.adouble.massmeter.db.ReferenceDataBase;
import com.dacker.adouble.massmeter.util.FragmentReplacer;

public class SecondStepFragment extends Fragment implements Step{

    private ListView countView;
    private Button nextButton;
    private Button saveButton;
    private ImageButton backButton;
    private ImageButton infoButton;
    private ImageView figureImage;
    private TextView result;
    private Spinner resultUnit;

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
    }

    private void setFigureImage() {
        Figure figure = Counter.getCounter().getFigure();
        int id = figure.getId();
        FigureInfo info = ReferenceDataBase.getInstance(getActivity()).referenceDao()
                .getFigureInfo(id);
        byte [] imageBytes = info.getFigureImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        figureImage.setImageBitmap(bitmap);
    }

    private void setupCountView() {
        String [] vars = Counter.getCounter().getFigure().getVars().split(" ");
        CountAdapter adapter = new CountAdapter(getActivity(), vars);
        countView.setAdapter(adapter);
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
