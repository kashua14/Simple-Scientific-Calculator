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
public class ScientificFragment extends Fragment {


    private String str, RorD = "RAD";


    public ScientificFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scientific, container, false);

        final MainActivity mainActivity = (MainActivity) getActivity();

        Button root = view.findViewById(R.id.square_root);
        Button power = view.findViewById(R.id.power);
        Button square = view.findViewById(R.id.square);
        Button factorial = view.findViewById(R.id.factorial);
        Button inverse = view.findViewById(R.id.inverse);
        Button pi = view.findViewById(R.id.pi);
        Button exp = view.findViewById(R.id.exponent);

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                if (mainActivity != null) {
                    str = btn.getText().toString();
                    mainActivity.onClickRoot(str);
                }
            }
        });

        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                if (mainActivity != null) {
                    str = btn.getText().toString();
                    mainActivity.onClickPower(str);
                }
            }
        });

        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainActivity != null) {
                    mainActivity.onClickSquare();
                }
            }
        });

        factorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainActivity != null) {
                    mainActivity.onClickFactorial();
                }
            }
        });

        inverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainActivity != null) {
                    mainActivity.onClickInverse();
                }
            }
        });

        pi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                if (mainActivity != null) {
                    str = btn.getText().toString();
                    mainActivity.onClickPIorE(str);
                }
            }
        });

        exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                if (mainActivity != null) {
                    str = btn.getText().toString();
                    mainActivity.onClickPIorE(str);
                }
            }
        });

        return view;
    }

}
