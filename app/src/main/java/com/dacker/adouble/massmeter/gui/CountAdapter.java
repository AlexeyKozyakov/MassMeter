package com.dacker.adouble.massmeter.gui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.dacker.adouble.massmeter.R;
import com.dacker.adouble.massmeter.core.unitof.UnitOf;

import java.util.HashMap;
import java.util.Map;

public class CountAdapter extends BaseAdapter {

    private static final String [] units = {"мм", "см", "дм", "м", "км"};
    private static final String defaultUnit =  "м";

    private Map<String, DimField> values = new HashMap<>();
    private String [] vars;

    private Context context;

    private static class DimField {

        private EditText input;
        private Spinner unit;

        public DimField(EditText input, Spinner unit) {
            this.input = input;
            this.unit = unit;
        }

    }

    public UnitOf.Length getValue(String var) {
        try {
            DimField field = values.get(var);
            if (field == null) {
                return null;
            }
            double val = Double.valueOf(field.input.getText().toString());
            String measure = field.unit.getSelectedItem().toString();
            switch (measure) {
                case "мм":
                    return new UnitOf.Length().fromMillimeters(val);
                case "см":
                    return new UnitOf.Length().fromCentimeters(val);
                case "дм":
                    return new UnitOf.Length().fromDecimeters(val);
                case "м":
                    return new UnitOf.Length().fromMeters(val);
                case "км":
                    return new UnitOf.Length().fromKilometers(val);
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return null;
    }

    public boolean validate() {
        for (String var : values.keySet()) {
            if (getValue(var) == null) {
                return false;
            }
        }
        return true;
    }

    public String[] getVars() {
        return vars;
    }

    public CountAdapter(Context context, String [] vars) {
        this.context = context;
        this.vars = vars;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout layout = new RelativeLayout(context);
        TextView label = new TextView(context);
        RelativeLayout.LayoutParams labelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        label.setLayoutParams(labelParams);
        EditText field = new EditText(context);
        RelativeLayout.LayoutParams editParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        editParams.addRule(RelativeLayout.RIGHT_OF, label.getId());
        field.setLayoutParams(editParams);
        Spinner unit = new Spinner(context);
        RelativeLayout.LayoutParams spinnerParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        spinnerParams.addRule(RelativeLayout.RIGHT_OF, field.getId());
        unit.setLayoutParams(spinnerParams);
        layout.addView(label);
        layout.addView(field);
        layout.addView(unit);
        label.setText(context.getString(R.string.variable_placeholder, vars[position]));
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item,
                android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit.setAdapter(spinnerAdapter);
        spinnerAdapter.addAll(units);
        spinnerAdapter.notifyDataSetChanged();
        unit.setSelection(0);
        values.put(vars[position], new DimField(field, unit));
        return layout;
    }

}
