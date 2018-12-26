package com.dacker.adouble.massmeter.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.dacker.adouble.massmeter.R;
import com.dacker.adouble.massmeter.core.mxparser.Expression;
import com.dacker.adouble.massmeter.util.FragmentReplacer;

import java.util.ArrayList;
import java.util.List;

public class CalculatorFragment extends Fragment implements Tab{
    private String expression="";
    private String loaded="";

    public List<String> counterfield = new ArrayList<>();

    TextView resultView;
    TextView expressionView;
    Spinner spinner;
    ArrayAdapter<String> adapter;

    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btnClear;
    Button btnDelete;
    Button btnPower;
    Button btnRight;
    Button btnLeft;
    Button btnPlus;
    Button btnMinus;
    Button btnMult;
    Button btnDiv;
    Button btnLoad;
    Button btnPoint;
    Button btnCount;

    public void addLastCounts(String s){
        counterfield.add(s);
        adapter.add(s);
    }

    public void beforeOpenFromCountMass(String s){
        spinner.setSelection(counterfield.indexOf(s));
    }

    private void setupFields(){
        resultView = getActivity().findViewById(R.id.result);
        expressionView = getActivity().findViewById(R.id.expression);
        spinner = getActivity().findViewById(R.id.counterfield);

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.addAll(counterfield);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Last counted");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loaded = adapter.getItem(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        }
        );
    }

    private void setupButtons(){
        btn0 = getActivity().findViewById(R.id.btn_0);
        btn1 = getActivity().findViewById(R.id.btn_1);
        btn2 = getActivity().findViewById(R.id.btn_2);
        btn3 = getActivity().findViewById(R.id.btn_3);
        btn4 = getActivity().findViewById(R.id.btn_4);
        btn5 = getActivity().findViewById(R.id.btn_5);
        btn6 = getActivity().findViewById(R.id.btn_6);
        btn7 = getActivity().findViewById(R.id.btn_7);
        btn8 = getActivity().findViewById(R.id.btn_8);
        btn9 = getActivity().findViewById(R.id.btn_9);
        btnClear = getActivity().findViewById(R.id.btn_clear);
        btnDelete = getActivity().findViewById(R.id.btn_delete);
        btnPower = getActivity().findViewById(R.id.btn_power);
        btnRight = getActivity().findViewById(R.id.btn_rightbracket);
        btnLeft = getActivity().findViewById(R.id.btn_leftbracket);
        btnPlus = getActivity().findViewById(R.id.btn_plus);
        btnMinus = getActivity().findViewById(R.id.btn_minus);
        btnMult = getActivity().findViewById(R.id.btn_multiply);
        btnDiv = getActivity().findViewById(R.id.btn_divide);
        btnLoad = getActivity().findViewById(R.id.btn_load);
        btnPoint = getActivity().findViewById(R.id.btn_point);
        btnCount = getActivity().findViewById(R.id.btn_count);
    }
    private void initListeners() {
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "0";
                expressionView.setText(expression);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "1";
                expressionView.setText(expression);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "2";
                expressionView.setText(expression);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "3";
                expressionView.setText(expression);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "4";
                expressionView.setText(expression);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "5";
                expressionView.setText(expression);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "6";
                expressionView.setText(expression);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "7";
                expressionView.setText(expression);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "8";
                expressionView.setText(expression);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "9";
                expressionView.setText(expression);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression = expression.substring(0,expression.length()-1) ;
                expressionView.setText(expression);
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression = "";
                expressionView.setText(expression);
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "-";
                expressionView.setText(expression);
            }
        });
        btnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "*";
                expressionView.setText(expression);
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "/";
                expressionView.setText(expression);
            }
        });
        btnPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "^";
                expressionView.setText(expression);
            }
        });
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "(";
                expressionView.setText(expression);
            }
        });
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += ")";
                expressionView.setText(expression);
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "+";
                expressionView.setText(expression);
            }
        });
        btnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += ".";
                expressionView.setText(expression);
            }
        });
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Expression expr = new Expression(expression);
                expr.calculate();
                resultView.setText(Double.toString(expr.calculate()));
            }
        });
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += loaded;
                expressionView.setText(expression);
            }
        });
    }
    @Override
    public void onCreate(Bundle saved) {
        super.onCreate(saved);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        counterfield.add("0.125");
        counterfield.add("0.155");
        counterfield.add("0.1213265");
        counterfield.add("0.12531");
        setupButtons();
        setupFields();
        initListeners();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calculator, container, false);
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
