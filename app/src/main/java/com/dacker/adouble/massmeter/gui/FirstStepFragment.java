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
import android.widget.LinearLayout;
import android.widget.ListView;
import com.dacker.adouble.massmeter.R;
import com.dacker.adouble.massmeter.core.Counter;
import com.dacker.adouble.massmeter.db.Category;
import com.dacker.adouble.massmeter.db.Figure;
import com.dacker.adouble.massmeter.db.ReferenceDataBase;
import com.dacker.adouble.massmeter.util.FragmentReplacer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstStepFragment extends Fragment implements Step {


    private Map<String, Integer> categories;
    private Map<String, Figure> figures;
    private SingleSelectionAdapter categoriesAdapter;
    private SingleSelectionAdapter figuresAdapter;
    private SingleSelectionAdapter searchAdapter;
    private ReferenceDataBase dataBase;
    private LinearLayout centerLayout;
    private ListView categoryListView;
    private ListView figureListView;
    private ListView searchListView;
    private SearchView searchView;
    private AppCompatButton nextButton;
    private boolean searching;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_step, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dataBase = ReferenceDataBase.getInstance(getActivity());
        searching = false;
        categories = new HashMap<>();
        figures = new HashMap<>();
        findViews();
        setupCategoryListView();
        setupFiguresListView();
        setupSearchListView();
        setupSearchView();
        setupNextButton();
    }

    private void findViews() {
        centerLayout = getActivity().findViewById(R.id.categories_layout);
        categoryListView = getActivity().findViewById(R.id.category_view);
        figureListView = getActivity().findViewById(R.id.figure_view);
        searchListView = getActivity().findViewById(R.id.figure_search);
        searchView = getActivity().findViewById(R.id.search_figure);
        nextButton = getActivity().findViewById(R.id.next_button);
    }

    private void openFigures(int position) {
        categoriesAdapter.setPosition(position);
        List<Figure> figureList = dataBase.referenceDao().
                getFiguresFromCategory(categories.get(categoriesAdapter.getSelectedName()));
        figuresAdapter.clear();
        for (Figure figure : figureList) {
            figuresAdapter.add(figure.getName());
        }
    }

    private void setupCategoryListView() {
        categoriesAdapter = new SingleSelectionAdapter(getActivity());
        categoryListView.setAdapter(categoriesAdapter);
        categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openFigures(position);
                figuresAdapter.setPosition(0);
            }
        });
    }

    private void setupFiguresListView() {
        figuresAdapter = new SingleSelectionAdapter(getActivity());
        figureListView.setAdapter(figuresAdapter);
        figureListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                figuresAdapter.setPosition(position);
            }
        });

    }

    private void setupSearchListView() {
        searchAdapter = new SingleSelectionAdapter(getActivity());
        searchListView.setAdapter(searchAdapter);
        searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchAdapter.setPosition(position);
                searchView.clearFocus();
            }
        });
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

    private void setupNextButton() {
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searching && searchAdapter.selected()) {
                    String selected = searchAdapter.getSelectedName();
                    Counter.getCounter().setFigure(figures.get(selected));
                    ((MainActivity)getActivity()).nextCounterStep();
                } else if (!searching && figuresAdapter.selected()) {
                    String selected = figuresAdapter.getSelectedName();
                    Counter.getCounter().setFigure(figures.get(selected));
                    ((MainActivity)getActivity()).nextCounterStep();
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getArguments() == null) {
            setArguments(new Bundle());
        }
        getArguments().putBoolean("searching", searching);
        getArguments().putInt("selectedCategory", categoriesAdapter.getPosition());
        getArguments().putInt("selectedFigure", figuresAdapter.getPosition());
        if (searching) {
            getArguments().putInt("searchedFigure", searchAdapter.getPosition());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadCategories();
        loadFigures();
        if (getArguments() != null) {
            searching = getArguments().getBoolean("searching");
            if (searching) {
                int searchPos = getArguments().getInt("searchedFigure");
                setSeaching();
                if (searchPos != -1) {
                    searchAdapter.getFilter().filter(searchView.getQuery());
                    searchAdapter.setPosition(searchPos);
                }
            }
            int catPos = getArguments().getInt("selectedCategory");
            if (catPos != -1) {
                categoriesAdapter.setPosition(catPos);
                openFigures(categoriesAdapter.getPosition());
                figuresAdapter.setPosition(getArguments().getInt("selectedFigure"));
            }
        }
    }

    private void loadCategories() {
        List<Category> categoryList = ReferenceDataBase.getInstance(getActivity())
                .referenceDao().getFigureCategories();
        for (Category category : categoryList) {
            categories.put(category.getName(), category.getId());
        }
        categoriesAdapter.clear();
        categoriesAdapter.addAll(categories.keySet());
    }

    private void setSeaching() {
        searching = true;
        centerLayout.setVisibility(View.GONE);
        searchListView.setVisibility(View.VISIBLE);
    }

    private void resetSearching() {
        searching = false;
        searchListView.setVisibility(View.GONE);
        centerLayout.setVisibility(View.VISIBLE);
    }

    private void loadFigures() {
        List<Figure> figuresList = ReferenceDataBase.getInstance(getActivity())
                .referenceDao().getAllFigures();
        for (Figure figure : figuresList) {
            figures.put(figure.getName(), figure);
        }
        searchAdapter.clear();
        searchAdapter.addAll(figures.keySet());
    }

    @Override
    public void show(AppCompatActivity activity) {
        FragmentReplacer.setFragment(activity, this);
    }

    public boolean handleBackClick() {
        if (searching) {
            resetSearching();
            return true;
        }
        return false;
    }

}
