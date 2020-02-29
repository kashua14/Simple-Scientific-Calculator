package com.kashua14.scientificcalc.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.kashua14.scientificcalc.MainActivity;
import com.kashua14.scientificcalc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BasicFragment extends Fragment {

    private String current_operator;


    public BasicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_basic, container, false);
        final MainActivity mainActivity = (MainActivity) getActivity();

        Button addition = view.findViewById(R.id.addition);
        Button subtraction = view.findViewById(R.id.subtraction);
        Button multiplication = view.findViewById(R.id.multiplication);
        Button division = view.findViewById(R.id.division);

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                current_operator= btn.getText().toString();
                if (mainActivity != null) {
                    mainActivity.onClickOperator(current_operator);
                }
            }
        });

        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                current_operator= btn.getText().toString();
                if (mainActivity != null) {
                    mainActivity.onClickOperator(current_operator);
                }
            }
        });

        multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                current_operator= btn.getText().toString();

                if (mainActivity != null) {
                    mainActivity.onClickOperator(current_operator);
                }
            }
        });

        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                current_operator= btn.getText().toString();
                if (mainActivity != null) {
                    mainActivity.onClickOperator(current_operator);
                }
            }
        });


        return view;
    }


}
