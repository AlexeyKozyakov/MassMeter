package com.dacker.adouble.massmeter.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
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
    private ListView figureSearch;
    private ArrayAdapter searchAdapter;
    private LinearLayout categoriesLayout;
    private boolean searching;
    private Figure selectedFigure;
    private CounterTab tab;
    private AppCompatButton nextButton;

    @Override
    public void onCreate(Bundle saved) {
        super.onCreate(saved);
        tab = (CounterTab) getArguments().get("tab");
        getArguments().clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_step, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dataBase = ReferenceDataBase.getInstance(getActivity().getApplicationContext());
        findViews();
        loadCategories();
        loadFigures();
        setupCategoryView();
        setupFiguresView();
        setupFiguresSearch();
        setupSearchView();
        setupNextButton();
    }

    private void findViews() {
        categoriesLayout = getActivity().findViewById(R.id.categories_layout);
        categoryListView = getActivity().findViewById(R.id.category_view);
        figureListView = getActivity().findViewById(R.id.figure_view);
        figureSearch = getActivity().findViewById(R.id.figure_search);
        searchView = getActivity().findViewById(R.id.search_figure);
        nextButton = getActivity().findViewById(R.id.next_button);
    }

    private void setupNextButton() {
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searching) {
                    String selected = (String) searchAdapter.getItem(figureSearch.getCheckedItemPosition());
                    if (selected == null) {
                        return;
                    }
                    tab.setFigure(allFigures.get(selected));
                } else {
                    if (selectedFigure == null) {
                        return;
                    }
                    tab.setFigure(selectedFigure);
                }
                tab.nextStep();
            }
        });
    }

    private void setupCategoryView() {
        categoryAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1);
        categoryAdapter.addAll(categoriesNames);
        categoryListView.setAdapter(categoryAdapter);
        categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onCategoryClick((int)id);
            }
        });
    }

    private void loadCategories() {
        categories = ReferenceDataBase.getInstance(getActivity().getApplicationContext())
                .referenceDao().getFigureCategories();
        categoriesNames = new ArrayList<>();
        for (Category category : categories) {
            categoriesNames.add(category.getName());
        }
    }

    private void setupFiguresView() {
        figuresAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1);
        figureListView.setAdapter(figuresAdapter);
        figureListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedFigure = figures.get((int)id);
            }
        });
    }

    private void setupFiguresSearch() {
        searching = false;
        figureSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchView.clearFocus();
            }
        });
        searchAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1);
        searchAdapter.addAll(allFigures.keySet());
        figureSearch.setAdapter(searchAdapter);
    }

    private void setSeaching() {
        searching = true;
        categoriesLayout.setVisibility(View.GONE);
        figureSearch.setVisibility(View.VISIBLE);
    }

    private void resetSearching() {
        searching = false;
        figureSearch.setVisibility(View.GONE);
        categoriesLayout.setVisibility(View.VISIBLE);
    }

    private void setupSearchView() {
        searchView.setIconified(false);
        searchView.clearFocus();
        searchView.setQueryHint("Type to search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchAdapter.getFilter().filter(s);
                return false;
            }
        });
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    setSeaching();
                }
            }
        });
    }

    private void loadFigures() {
        allFigures = new HashMap<>();
        for (Figure figure : dataBase.referenceDao().getAllFigures()) {
            allFigures.put(figure.getName(), figure);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        selectedFigure = null;
        loadCategories();
        loadFigures();
        categoryAdapter.clear();
        categoryAdapter.addAll(categoriesNames);
        figuresAdapter.notifyDataSetChanged();
        figuresAdapter.clear();
        figuresAdapter.notifyDataSetChanged();
    }

    @Override
    public void show(AppCompatActivity activity) {
        FragmentReplacer.setFragment(activity, this);
    }

    private void onCategoryClick(int id) {
        figures = dataBase.referenceDao().getFiguresFromCategory(categories.get(id).getId());
        List<String> figuresNames = new ArrayList<>();
        for (Figure figure : figures) {
            figuresNames.add(figure.getName());
        }
        figuresAdapter.clear();
        figuresAdapter.addAll(figuresNames);
        figuresAdapter.notifyDataSetChanged();
    }

    public boolean handleBackClick() {
        if (searching) {
            resetSearching();
            return true;
        }
        return false;
    }
}
