package com.dacker.adouble.massmeter.gui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.dacker.adouble.massmeter.R;
import com.dacker.adouble.massmeter.core.Counter;
import com.dacker.adouble.massmeter.core.unitof.UnitOf;
import com.dacker.adouble.massmeter.db.Category;
import com.dacker.adouble.massmeter.db.Material;
import com.dacker.adouble.massmeter.db.Preset;
import com.dacker.adouble.massmeter.db.ReferenceDataBase;
import com.dacker.adouble.massmeter.util.FragmentReplacer;
import com.dacker.adouble.massmeter.util.UnitUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ThirdStepFragment extends Fragment implements Step{

    private Map<String, Integer> categories;
    private Map<String, Material> materials;
    private SingleSelectionAdapter categoriesAdapter;
    private SingleSelectionAdapter materialsAdapter;
    private SingleSelectionAdapter searchAdapter;
    private ReferenceDataBase dataBase;
    private LinearLayout centerLayout;
    private ListView categoryListView;
    private ListView materialListView;
    private ListView searchListView;
    private SearchView searchView;
    private boolean searching;
    private Button savePresetButton;
    private TextView result;
    private Spinner resultUnit;
    private ImageButton gotoCalculator;
    private ImageButton infoButton;
    private UnitOf.Mass massInKilos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third_step, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dataBase = ReferenceDataBase.getInstance(getActivity());
        searching = false;
        categories = new HashMap<>();
        materials = new HashMap<>();
        findViews();
        setupCategoryListView();
        setupMaterialsListView();
        setupSearchListView();
        setupSearchView();
        resultUnit.setSelection(UnitUtil.defaultMassUnit);
        ArrayAdapter<String> resultAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item,
                android.R.id.text1);
        resultAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        resultUnit.setAdapter(resultAdapter);
        resultAdapter.addAll(UnitUtil.massUnits);
        resultUnit.setSelection(UnitUtil.defaultMassUnit);
        resultAdapter.notifyDataSetChanged();
        resultUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (massInKilos != null)
                    showMassInCorrectUnit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        setupCalcButton();
    }

    private void setupCalcButton() {
        gotoCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (massInKilos != null) {
                    SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    Set<String> previous = pref.getStringSet("calcHis", new HashSet<String>());
                    previous.add(String.valueOf(massInKilos.toKilograms()));
                    editor.putStringSet("calcHis", previous);
                    editor.commit();
                    ((MainActivity)getActivity()).showCalculatorTab();
                } else {
                    result.setText(getActivity().getString(R.string.mass_placeholder));
                }
            }
        });
        savePresetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (massInKilos != null) {
                    Preset preset = new Preset();
                    preset.setFigure(Counter.getCounter().getFigure().getId());
                    preset.setId(ReferenceDataBase.getInstance(getActivity()).referenceDao().getAllPresets().size());
                    preset.setValues(Counter.getCounter().getValuesForPreset());
                    if (Counter.getCounter().hasMaterial())
                        preset.setMaterial(Counter.getCounter().getMaterial().getId());
                    ReferenceDataBase.getInstance(getActivity())
                            .referenceDao().addPreset(preset);
                }
            }
        });
    }

    private void findViews() {
        centerLayout = getActivity().findViewById(R.id.categories_layout_st3);
        categoryListView = getActivity().findViewById(R.id.category_view_st3);
        materialListView = getActivity().findViewById(R.id.material_view_st3);
        searchListView = getActivity().findViewById(R.id.material_search);
        searchView = getActivity().findViewById(R.id.search_material_st3);
        savePresetButton = getActivity().findViewById(R.id.save_button_st3);
        savePresetButton = getActivity().findViewById(R.id.save_button_st3);
        result = getActivity().findViewById(R.id.mass_res);
        resultUnit = getActivity().findViewById(R.id.mass_spinner);
        gotoCalculator = getActivity().findViewById(R.id.goto_calculator);
        infoButton = getActivity().findViewById(R.id.info_button);
    }

    private void openMaterials(int position) {
        categoriesAdapter.setPosition(position);
        List<Material> materialList = dataBase.referenceDao().
                getMaterialsFromCategory(categories.get(categoriesAdapter.getSelectedName()));
        materialsAdapter.clear();
        for (Material material : materialList) {
            materialsAdapter.add(material.getName());
        }
    }

    private void setupCategoryListView() {
        categoriesAdapter = new SingleSelectionAdapter(getActivity());
        categoryListView.setAdapter(categoriesAdapter);
        categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openMaterials(position);
                materialsAdapter.setPosition(0);
                checkAndCount();
            }
        });
    }

    private void setupMaterialsListView() {
        materialsAdapter = new SingleSelectionAdapter(getActivity());
        materialListView.setAdapter(materialsAdapter);
        materialListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                materialsAdapter.setPosition(position);
                checkAndCount();
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
                checkAndCount();
            }
        });
    }

    private void checkAndCount() {
        if (searching && searchAdapter.selected()) {
            String selected = searchAdapter.getSelectedName();
            countAndShowMassRes(materials.get(selected));
        } else if (!searching && materialsAdapter.selected()) {
            String selected = materialsAdapter.getSelectedName();
            countAndShowMassRes(materials.get(selected));
        }
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

    @Override
    public void onPause() {
        super.onPause();
        if (getArguments() == null) {
            setArguments(new Bundle());
        }
        getArguments().putBoolean("searching", searching);
        getArguments().putInt("selectedCategory", categoriesAdapter.getPosition());
        getArguments().putInt("selectedMaterial", materialsAdapter.getPosition());
        if (searching) {
            getArguments().putInt("searchedMaterial", searchAdapter.getPosition());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadCategories();
        loadMaterials();
        if (getArguments() != null) {
            searching = getArguments().getBoolean("searching");
            if (searching) {
                int searchPos = getArguments().getInt("searchedMaterial");
                setSeaching();
                if (searchPos != -1) {
                    searchAdapter.getFilter().filter(searchView.getQuery());
                    searchAdapter.setPosition(searchPos);
                }
            }
            int catPos = getArguments().getInt("selectedCategory");
            if (catPos != -1) {
                categoriesAdapter.setPosition(catPos);
                openMaterials(categoriesAdapter.getPosition());
                materialsAdapter.setPosition(getArguments().getInt("selectedMaterial"));
            }
        }
    }

    private void loadCategories() {
        List<Category> categoryList = ReferenceDataBase.getInstance(getActivity())
                .referenceDao().getMaterialCategories();
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

    private void loadMaterials() {
        List<Material> materialList = ReferenceDataBase.getInstance(getActivity())
                .referenceDao().getAllMaterialss();
        for (Material material: materialList) {
            materials.put(material.getName(), material);
        }
        searchAdapter.clear();
        searchAdapter.addAll(materials.keySet());
    }

    @Override
    public void show(AppCompatActivity activity) {
        FragmentReplacer.setFragment(activity, this);
    }

    public boolean handleBackClick() {
        if (searching) {
            resetSearching();
        } else {
            ((MainActivity)getActivity()).previousCounterStep();
        }
        return true;
    }

    private void countAndShowMassRes(final Material material) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    Counter.getCounter().setMaterial(material);
                    Double vol = Counter.getCounter().countMass();
                    massInKilos = new UnitOf.Mass().fromKilograms(vol);
                    showMassInCorrectUnit();
                }
            }
        });
    }

    private void showMassInCorrectUnit() {
        result.post(new Runnable() {
            @Override
            public void run() {
                result.setText(getActivity().getString(R.string.mass_placeholder,
                        UnitUtil.convertMassTo(massInKilos, UnitUtil.massUnits[resultUnit.getSelectedItemPosition()])));
            }
        });
    }
}
