package com.dacker.adouble.massmeter.gui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.dacker.adouble.massmeter.R;
import com.dacker.adouble.massmeter.core.Counter;
import com.dacker.adouble.massmeter.core.unitof.UnitOf;
import com.dacker.adouble.massmeter.util.UnitUtil;

import java.util.HashMap;
import java.util.Map;

import static com.dacker.adouble.massmeter.util.UnitUtil.defaultLenUnit;

public class CountAdapter extends BaseAdapter {

    private String [] vars;

    private Map<String, UnitUtil.StringIntPair> values = new HashMap<>();

    private Context context;
    private TextView result;
    private View resultContainer;
    private Spinner resultUnit;

    private UnitOf.Volume resultInMeters;

    private static String valuePostfix = "@#___value___#!!";

    public boolean validate() {
        for (String var : vars) {
            UnitUtil.StringIntPair userInput = values.get((var));
            if (userInput == null ||
                    userInput.first == null || userInput.first.equals("") ||
                    userInput.second == null || userInput.second.equals("")) {
                return false;
            } else {
                if (UnitUtil.getLength(userInput) == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public String[] getVars() {
        return vars;
    }

    public CountAdapter(Context context, String [] vars, TextView result, View resultContainer, Spinner resultUnit) {
        this.context = context;
        this.vars = vars;
        this.result = result;
        this.resultContainer = resultContainer;
        this.resultUnit = resultUnit;
        setupResultsView();
    }

    private void setupResultsView() {
        ArrayAdapter<String> resultAdapter = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item,
                android.R.id.text1);
        resultAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        resultUnit.setAdapter(resultAdapter);
        resultAdapter.addAll(UnitUtil.volumeUnits);
        resultUnit.setSelection(UnitUtil.defaultVolumeUnit);
        resultAdapter.notifyDataSetChanged();
        resultUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertAndSetResult();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public int getCount() {
        return vars.length;
    }

    @Override
    public Object getItem(int position) {
        return vars[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            RelativeLayout layout = new RelativeLayout(context);
            layout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT));
            TextView label = new TextView(context);
            label.setId(1);
            RelativeLayout.LayoutParams labelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            labelParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            labelParams.addRule(RelativeLayout.CENTER_VERTICAL);
            EditText field = new EditText(context);
            field.setId(2);
            field.setSingleLine();
            field.setEms(4);
            field.getBackground().mutate().setColorFilter(0xFF616161, PorterDuff.Mode.SRC_ATOP);
            RelativeLayout.LayoutParams editParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            editParams.addRule(RelativeLayout.CENTER_VERTICAL);
            Spinner unit = new Spinner(context);
            RelativeLayout.LayoutParams spinnerParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            spinnerParams.addRule(RelativeLayout.CENTER_VERTICAL);
            label.setText(context.getString(R.string.variable_placeholder, vars[position]));
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context,
                    android.R.layout.simple_spinner_item,
                    android.R.id.text1);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            unit.setAdapter(spinnerAdapter);
            spinnerAdapter.addAll(UnitUtil.lengthUnits);
            spinnerAdapter.notifyDataSetChanged();
            unit.setSelection(defaultLenUnit);
            editParams.addRule(RelativeLayout.RIGHT_OF, label.getId());
            spinnerParams.addRule(RelativeLayout.RIGHT_OF, field.getId());
            label.setLayoutParams(labelParams);
            field.setLayoutParams(editParams);
            unit.setLayoutParams(spinnerParams);
            layout.addView(label);
            layout.addView(field);
            layout.addView(unit);
            setFieldListener(field, vars[position]);
            setUnitListener(unit, vars[position]);
            UnitUtil.StringIntPair pair;
            if ((pair = values.get(vars[position])) != null) {
                if (pair.first != null) {
                    field.setText(pair.first);
                }
                if (pair.second != null) {
                    unit.setSelection(pair.second);
                }
            }
            return layout;
        } else {
            return convertView;
        }
    }

    private void setFieldListener(final EditText field, final String var) {
        field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
                if (values.get(var) == null) {
                    values.put(var, new UnitUtil.StringIntPair(s.toString(), null));
                } else {
                    values.get(var).first = s.toString();
                }
                executeCount();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setUnitListener(final Spinner unit, final String var) {
        unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (values.get(var) == null) {
                    values.put(var, new UnitUtil.StringIntPair(null, position));
                } else {
                    values.get(var).second = position;
                }
                executeCount();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void executeCount() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    if (validate()) {
                        Map<String, Double> valuesToCount = new HashMap<>();
                        for (Map.Entry<String, UnitUtil.StringIntPair> entry : values.entrySet()) {
                            valuesToCount.put(entry.getKey(), UnitUtil.getLength(entry.getValue()).toMeters());
                        }
                        Counter.getCounter().setValues(valuesToCount);
                        final double volume = Counter.getCounter().countVolume();
                        resultInMeters = new UnitOf.Volume().fromCubicMeters(volume);
                        resultContainer.post(new Runnable() {
                            @Override
                            public void run() {
                                convertAndSetResult();
                            }
                        });
                    } else {
                        resultContainer.post(new Runnable() {
                            @Override
                            public void run() {
                                resultContainer.setVisibility(View.GONE);
                            }
                        });
                    }
                }
            }
        });
    }

    private void convertAndSetResult() {
        Double resVolume = UnitUtil.convertVolumeTo(resultInMeters,
                UnitUtil.volumeUnits[resultUnit.getSelectedItemPosition()]);
        if (resVolume != null) {
            result.setText(context.getString(R.string.volume_placeholder, resVolume));
            resultContainer.setVisibility(View.VISIBLE);
        }
    }

    public void saveState(Bundle bundle) {
        for (Map.Entry<String, UnitUtil.StringIntPair> entry : values.entrySet()) {
            if (entry.getValue().first != null) {
                bundle.putString(entry.getKey(), entry.getValue().first);
                bundle.putInt(entry.getKey() + valuePostfix, entry.getValue().second);
            }
        }
    }

    public void loadState(Bundle bundle) {
        for (String var : vars) {
            String fieldText = bundle.getString(var);
            if (fieldText != null) {
                values.put(var, new UnitUtil.StringIntPair(fieldText, bundle.getInt(var + valuePostfix)));
            }
        }
        notifyDataSetChanged();
    }
}
