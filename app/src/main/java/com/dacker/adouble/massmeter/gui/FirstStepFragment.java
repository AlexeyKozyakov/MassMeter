package com.dacker.adouble.massmeter.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.dacker.adouble.massmeter.R;
import com.dacker.adouble.massmeter.db.Category;
import com.dacker.adouble.massmeter.db.Figure;
import com.dacker.adouble.massmeter.db.ReferenceDataBase;
import com.dacker.adouble.massmeter.util.FragmentReplacer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstStepFragment extends Fragment implements Step {


    private List<Category> categories;
    private List<Figure> figures;
    private List<String> categoriesNames;
    private Map<String, Figure> allFigures;
    private ArrayAdapter<String> categoryAdapter;
    private ArrayAdapter<String> figuresAdapter;
    private ReferenceDataBase dataBase;
    private ListView categoryListView;
    private ListView figureListView;
    private SearchView searchView;

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
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dataBase = ReferenceDataBase.getInstance(getActivity().getApplicationContext());
        setupViews();
        loadCategories();
        setupCategoryView();
        loadFigures();
        setupFiguresView();
        setupSearchView();

    }

    private void setupViews() {
        categoryListView = getActivity().findViewById(R.id.category_view);
        figureListView = getActivity().findViewById(R.id.figure_view);
    }

    private void setupCategoryView() {
        categoryAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1);
        categoryAdapter.addAll(categoriesNames);
        categoryListView.setAdapter(categoryAdapter);
        categoryListView.setOnItemClickListener(categoryListener);
    }

    private void loadCategories() {
        categories = ReferenceDataBase.getInstance(getActivity().getApplicationContext())
                .referenceDao().getFigureCategories();
        for (Category category : categories) {
            categoriesNames = new ArrayList<>();
            categoriesNames.add(category.getName());
        }
    }

    private void setupFiguresView() {
        figuresAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1);
        figureListView.setAdapter(figuresAdapter);
    }

    private void setupSearchView() {
        searchView = getActivity().findViewById(R.id.search_figure);
        //TO DO: search figure
    }

    private void loadFigures() {
        allFigures = new HashMap<>();
        for (Figure figure : dataBase.referenceDao().getAllFigures()) {
            allFigures.put(figure.getName(), figure);
        }
    }

    @Override
    public void onPause() {
        Log.d("mass", "First step fragment onPause");
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadCategories();
        categoryAdapter.clear();
        categoryAdapter.addAll(categoriesNames);
        figuresAdapter.notifyDataSetChanged();
        figuresAdapter.clear();
        figuresAdapter.notifyDataSetChanged();
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

    private AdapterView.OnItemClickListener categoryListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
            figures = dataBase.referenceDao().getFiguresFromCategory(categories.get((int)id).getId());
            List<String> figuresNames = new ArrayList<>();
            for (Figure figure : figures) {
                figuresNames.add(figure.getName());
            }
            figuresAdapter.clear();
            figuresAdapter.addAll(figuresNames);
            figuresAdapter.notifyDataSetChanged();
        }
    };
}
