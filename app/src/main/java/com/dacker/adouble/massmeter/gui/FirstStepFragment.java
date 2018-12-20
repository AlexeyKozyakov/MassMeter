package com.dacker.adouble.massmeter.gui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dacker.adouble.massmeter.R;
import com.dacker.adouble.massmeter.db.Category;
import com.dacker.adouble.massmeter.db.Figure;
import com.dacker.adouble.massmeter.db.ReferenceDataBase;
import com.dacker.adouble.massmeter.util.FragmentReplacer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FirstStepFragment extends Fragment implements Step{


    List<Category> categiryesList;
    List<Figure> figureList;
    List<String> categories = new ArrayList<>();
    private SearchView searchView;
    private ListView categoryListView;
    private ListView figureListView;

    @Override
    public void onCreate(Bundle saved) {
        Log.d("mass", "First step fragment onCreate");
        super.onCreate(saved);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("mass", "First step fragment onCreateView");
        View view = inflater.inflate(R.layout.fragment_first_step, container, false);
        searchView = view.findViewById(R.id.search_figure);
        categoryListView = view.findViewById(R.id.category_view);
        figureListView = view.findViewById(R.id.figure_view);
        searchView.setIconifiedByDefault(false);
        searchView.clearFocus();
        if (categiryesList == null) {
            loadCategories();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, categories.toArray(new String[0]));
        categoryListView.setAdapter(adapter);
        categoryListView.setOnItemClickListener(categoryListener);
        return view;
    }

    @Override
    public void onPause() {
        Log.d("mass", "First step fragment onPause");
        super.onPause();
    }

    @Override
    public void attachElements(View view) {

    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public void setUserParams() {

    }

    @Override
    public void show(AppCompatActivity activity) {
        FragmentReplacer.setFragment(activity, this);
    }

    private void loadCategories() {
        categiryesList = ReferenceDataBase.getInstance(getActivity().getApplicationContext())
                .referenceDao().getFigureCategories();
        for (Category category : categiryesList) {
            categories.add(category.getName());
        }
    }

    private AdapterView.OnItemClickListener categoryListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            figureList = ReferenceDataBase.getInstance(getActivity().getApplicationContext())
                    .referenceDao().getFiguresFromCategory(position);
            List<String> choosen = new ArrayList<>();
            for (Figure figure : figureList) {
                choosen.add(figure.getName());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_list_item_1, choosen.toArray(new String[0]));
            figureListView.setAdapter(adapter);
        }
    };
}
