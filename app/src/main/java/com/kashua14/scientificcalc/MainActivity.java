package com.kashua14.scientificcalc;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.snackbar.Snackbar;
import com.kashua14.scientificcalc.Fragments.BasicFragment;
import com.kashua14.scientificcalc.Fragments.EmptyFragment;
import com.kashua14.scientificcalc.Fragments.ScientificFragment;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    ImageView closePopupPositive, closePopupNegative, closePopupQuizRules, closePopupQuestion;
    Dialog epicDialog;
    Button btnStart, btnNext, btnTryAgain, btnFinish;
    EditText quiz_answer;
    View view;
    int i = 1, count = 0;
    Snackbar mSnackBar = null;

    BasicFragment basicFragment = new BasicFragment();
    ScientificFragment scientificFragment = new ScientificFragment();
    EmptyFragment emptyFragment = new EmptyFragment();
    FragmentManager fragmentManager;


    TextView calculation, answer;
    Button basic_btn, scientific_Btn, game;

    String sCalculation = "", sAnswer = "", ans = "", current_operator = "", number_one = "", number_two = "";
    Double temp = 0.0, result = 0.0, numberOne = 0.0, numberTwo = 0.0, val =0.0;
    boolean dot_present = false, root_present = false, invert_allow = true, power_present = false;
    boolean constant_presnet = false, factorial_present = false, value_inverted = false;
    int onOff = 0;
    NumberFormat format, longFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        epicDialog = new Dialog(this);
        game = findViewById(R.id.game);

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQuizIntroDialog();
            }
        });


        answer = findViewById(R.id.answer);
        calculation = findViewById(R.id.calculation);
//        calculation.setMovementMethod(new ScrollingMovementMethod());

        fragmentManager = getSupportFragmentManager();

        basic_btn = findViewById(R.id.basic_btn);
        scientific_Btn = findViewById(R.id.scientific_Btn);

        format = new DecimalFormat("#.##########");
        longFormat = new DecimalFormat("0.#E0");


        /*
          Snackbar implementation.
         */
//        game.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Snackbar.make(findViewById(R.id.myCoordinatedLayout), R.string.game, Snackbar.LENGTH_SHORT).show();
//                Snackbar mSnackBar = Snackbar.make(v, "TOP SNACKBAR", Snackbar.LENGTH_LONG);
//                View view = mSnackBar.getView();
//                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
//                params.gravity = Gravity.TOP;
//                view.setLayoutParams(params);
//                view.setBackgroundColor(Color.CYAN);
//                TextView mainTextView;
//                mainTextView = (view).findViewById(com.google.android.material.R.id.snackbar_text);
//                mainTextView.setTextColor(Color.BLACK);
//                mSnackBar.show();
//            }
//        });


        /**
        *  Changing the Action bar Title
        */
        basic_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOff == 0 || (getSupportActionBar().getTitle().equals("Scientific Calculator")))
                {
                    changeFragment(basicFragment, "Standard Calculator");
                }else{
                    changeFragment(emptyFragment, "Lets Calculate");
                }
            }
        });

        scientific_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOff == 0 || (getSupportActionBar().getTitle().equals("Standard Calculator"))){
                    changeFragment(scientificFragment, "Scientific Calculator");
                }else{
                    changeFragment(emptyFragment, "Lets Calculate");
                }
            }
        });

    }

    public void showQuizIntroDialog(){
        epicDialog = new Dialog(this);
        epicDialog.setContentView(R.layout.game_rules);
        closePopupQuizRules = epicDialog.findViewById(R.id.closePopupQuizRules);
        btnStart = epicDialog.findViewById(R.id.btnStart);

        closePopupQuizRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQuestion1Dialog();
            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }

    public void showQuestion1Dialog(){
        epicDialog.setContentView(R.layout.question_one);
        closePopupQuestion= epicDialog.findViewById(R.id.closePopupQuestion);
        btnNext = epicDialog.findViewById(R.id.btnNext);
        quiz_answer = epicDialog.findViewById(R.id.quiz_answer);

        closePopupQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=0;
                epicDialog.dismiss();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ans = quiz_answer.getText().toString();
                if (!ans.equals("")) {
                    val = Double.parseDouble(ans);
                    if (val == 49) {
                        count++;
                        mSnackBar = Snackbar.make(v, String.format("+%d correct answer", count),
                                Snackbar.LENGTH_SHORT);
                        View view = mSnackBar.getView();
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                        params.gravity = Gravity.TOP;
                        view.setLayoutParams(params);
                        view.setBackgroundColor(Color.GREEN);
                        TextView mainTextView;
                        mainTextView = (view).findViewById(com.google.android.material.R.id.snackbar_text);
                        mainTextView.setTextColor(Color.BLACK);
                        mSnackBar.show();

                    } else {
                        mSnackBar = Snackbar.make(v, "Wrong Answer", Snackbar.LENGTH_SHORT);
                        View view = mSnackBar.getView();
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                        params.gravity = Gravity.TOP;
                        view.setLayoutParams(params);
                        view.setBackgroundColor(Color.RED);
                        TextView mainTextView;
                        mainTextView = (view).findViewById(com.google.android.material.R.id.snackbar_text);
                        mainTextView.setTextColor(Color.WHITE);
                        mSnackBar.show();
                    }
                    showQuestion2Dialog();
                } else {
                    mSnackBar = Snackbar.make(v, "Please provide an answer", Snackbar.LENGTH_SHORT);
                    View view = mSnackBar.getView();
                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                    params.gravity = Gravity.TOP;
                    view.setLayoutParams(params);
                    view.setBackgroundColor(Color.BLACK);
                    TextView mainTextView;
                    mainTextView = (view).findViewById(com.google.android.material.R.id.snackbar_text);
                    mainTextView.setTextColor(Color.YELLOW);
                    mSnackBar.show();
                }
            }
        });

        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }

    private void showQuestion2Dialog() {
        epicDialog.setContentView(R.layout.question_two);
        closePopupQuestion= epicDialog.findViewById(R.id.closePopupQuestion);
        btnNext = epicDialog.findViewById(R.id.btnNext);
        quiz_answer = epicDialog.findViewById(R.id.quiz_answer);

        closePopupQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=0;
                epicDialog.dismiss();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ans = quiz_answer.getText().toString();
                if (!ans.equals("")) {
                    val = Double.parseDouble(ans);
                    if (val == 16) {
                        count++;
                        mSnackBar = Snackbar.make(v, String.format("+%d correct answer", count),
                                Snackbar.LENGTH_SHORT);
                        View view = mSnackBar.getView();
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                        params.gravity = Gravity.TOP;
                        view.setLayoutParams(params);
                        view.setBackgroundColor(Color.GREEN);
                        TextView mainTextView;
                        mainTextView = (view).findViewById(com.google.android.material.R.id.snackbar_text);
                        mainTextView.setTextColor(Color.BLACK);
                        mSnackBar.show();

                    } else {
                        mSnackBar = Snackbar.make(v, "Wrong Answer", Snackbar.LENGTH_SHORT);
                        View view = mSnackBar.getView();
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                        params.gravity = Gravity.TOP;
                        view.setLayoutParams(params);
                        view.setBackgroundColor(Color.RED);
                        TextView mainTextView;
                        mainTextView = (view).findViewById(com.google.android.material.R.id.snackbar_text);
                        mainTextView.setTextColor(Color.WHITE);
                        mSnackBar.show();
                    }
                    showQuestion3Dialog();
                } else {
                    mSnackBar = Snackbar.make(v, "Please provide an answer", Snackbar.LENGTH_SHORT);
                    View view = mSnackBar.getView();
                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                    params.gravity = Gravity.TOP;
                    view.setLayoutParams(params);
                    view.setBackgroundColor(Color.BLACK);
                    TextView mainTextView;
                    mainTextView = (view).findViewById(com.google.android.material.R.id.snackbar_text);
                    mainTextView.setTextColor(Color.YELLOW);
                    mSnackBar.show();
                }
            }
        });

        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }

    private void showQuestion3Dialog() {
        epicDialog.setContentView(R.layout.question_three);
        closePopupQuestion= epicDialog.findViewById(R.id.closePopupQuestion);
        btnNext = epicDialog.findViewById(R.id.btnNext);
        quiz_answer = epicDialog.findViewById(R.id.quiz_answer);

        closePopupQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=0;
                epicDialog.dismiss();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ans = quiz_answer.getText().toString();
                if (!ans.equals("")) {
                    val = Double.parseDouble(ans);
                    if (val == 1380) {
                        count++;
                        mSnackBar = Snackbar.make(v, String.format("+%d correct answer", count),
                                Snackbar.LENGTH_SHORT);
                        View view = mSnackBar.getView();
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                        params.gravity = Gravity.TOP;
                        view.setLayoutParams(params);
                        view.setBackgroundColor(Color.GREEN);
                        TextView mainTextView;
                        mainTextView = (view).findViewById(com.google.android.material.R.id.snackbar_text);
                        mainTextView.setTextColor(Color.BLACK);
                        mSnackBar.show();

                    } else {
                        mSnackBar = Snackbar.make(v, "Wrong Answer", Snackbar.LENGTH_SHORT);
                        View view = mSnackBar.getView();
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                        params.gravity = Gravity.TOP;
                        view.setLayoutParams(params);
                        view.setBackgroundColor(Color.RED);
                        TextView mainTextView;
                        mainTextView = (view).findViewById(com.google.android.material.R.id.snackbar_text);
                        mainTextView.setTextColor(Color.WHITE);
                        mSnackBar.show();
                    }
                    showQuestion4Dialog();
                } else {
                    mSnackBar = Snackbar.make(v, "Please provide an answer", Snackbar.LENGTH_SHORT);
                    View view = mSnackBar.getView();
                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                    params.gravity = Gravity.TOP;
                    view.setLayoutParams(params);
                    view.setBackgroundColor(Color.BLACK);
                    TextView mainTextView;
                    mainTextView = (view).findViewById(com.google.android.material.R.id.snackbar_text);
                    mainTextView.setTextColor(Color.YELLOW);
                    mSnackBar.show();
                }
            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }

    private void showQuestion4Dialog() {
        epicDialog.setContentView(R.layout.question_four);
        closePopupQuestion= epicDialog.findViewById(R.id.closePopupQuestion);
        btnNext = epicDialog.findViewById(R.id.btnNext);
        quiz_answer = epicDialog.findViewById(R.id.quiz_answer);

        closePopupQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ans = quiz_answer.getText().toString();
                if (!ans.equals("")) {
                    val = Double.parseDouble(ans);
                    System.out.println("Value: "+val);
                    if (val == 35) {
                        count++;
                        mSnackBar = Snackbar.make(v, String.format("+%d correct answer", count),
                                Snackbar.LENGTH_SHORT);
                        View view = mSnackBar.getView();
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                        params.gravity = Gravity.TOP;
                        view.setLayoutParams(params);
                        view.setBackgroundColor(Color.GREEN);
                        TextView mainTextView;
                        mainTextView = (view).findViewById(com.google.android.material.R.id.snackbar_text);
                        mainTextView.setTextColor(Color.BLACK);
                        mSnackBar.show();

                    } else {
                        mSnackBar = Snackbar.make(v, "Wrong Answer", Snackbar.LENGTH_SHORT);
                        View view = mSnackBar.getView();
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                        params.gravity = Gravity.TOP;
                        view.setLayoutParams(params);
                        view.setBackgroundColor(Color.RED);
                        TextView mainTextView;
                        mainTextView = (view).findViewById(com.google.android.material.R.id.snackbar_text);
                        mainTextView.setTextColor(Color.WHITE);
                        mSnackBar.show();
                    }
                    showQuestion5Dialog();
                } else {
                    mSnackBar = Snackbar.make(v, "Please provide an answer", Snackbar.LENGTH_SHORT);
                    View view = mSnackBar.getView();
                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                    params.gravity = Gravity.TOP;
                    view.setLayoutParams(params);
                    view.setBackgroundColor(Color.BLACK);
                    TextView mainTextView;
                    mainTextView = (view).findViewById(com.google.android.material.R.id.snackbar_text);
                    mainTextView.setTextColor(Color.YELLOW);
                    mSnackBar.show();
                }
            }
        });

        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }

    private void showQuestion5Dialog() {
        epicDialog.setContentView(R.layout.question_five);
        closePopupQuestion= epicDialog.findViewById(R.id.closePopupQuestion);
        btnNext = epicDialog.findViewById(R.id.btnNext);
        quiz_answer = epicDialog.findViewById(R.id.quiz_answer);

        closePopupQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=0;
                epicDialog.dismiss();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ans = quiz_answer.getText().toString();
                System.out.println("Answer: "+ans);
                if (!ans.equals("")) {
                    val = Double.parseDouble(ans);
                    System.out.println("Value: "+val);
                    if (val == 120) {
                        count++;
                        mSnackBar = Snackbar.make(v, String.format("+%d correct answer", count),
                                Snackbar.LENGTH_SHORT);
                        View view = mSnackBar.getView();
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                        params.gravity = Gravity.TOP;
                        view.setLayoutParams(params);
                        view.setBackgroundColor(Color.GREEN);
                        TextView mainTextView;
                        mainTextView = (view).findViewById(com.google.android.material.R.id.snackbar_text);
                        mainTextView.setTextColor(Color.BLACK);
                        mSnackBar.show();

                    } else {
                        mSnackBar = Snackbar.make(v, "Wrong Answer", Snackbar.LENGTH_SHORT);
                        View view = mSnackBar.getView();
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                        params.gravity = Gravity.TOP;
                        view.setLayoutParams(params);
                        view.setBackgroundColor(Color.RED);
                        TextView mainTextView;
                        mainTextView = (view).findViewById(com.google.android.material.R.id.snackbar_text);
                        mainTextView.setTextColor(Color.WHITE);
                        mSnackBar.show();
                    }

                    if(count != 5){ showNegativeDialog(); }
                    else{ showPositiveDialog(); }
                } else {
                    mSnackBar = Snackbar.make(v, "Please provide an answer", Snackbar.LENGTH_SHORT);
                    View view = mSnackBar.getView();
                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                    params.gravity = Gravity.TOP;
                    view.setLayoutParams(params);
                    view.setBackgroundColor(Color.BLACK);
                    TextView mainTextView;
                    mainTextView = (view).findViewById(com.google.android.material.R.id.snackbar_text);
                    mainTextView.setTextColor(Color.YELLOW);
                    mSnackBar.show();
                }
            }
        });

        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }

    public void showPositiveDialog(){
        epicDialog.setContentView(R.layout.epic_popup_positive);
        closePopupPositive = epicDialog.findViewById(R.id.closePopupPositive);
        btnFinish = epicDialog.findViewById(R.id.btnFinish);

        closePopupPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.king.candycrushsaga");
                if (launchIntent != null){
                    startActivity(launchIntent);
                }

            }
        });

        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }

    public void showNegativeDialog(){
        epicDialog.setContentView(R.layout.epic_popup_negative);
        closePopupNegative = epicDialog.findViewById(R.id.closePopupNegative);
        btnTryAgain = epicDialog.findViewById(R.id.btnTryagain);

        closePopupNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();
            }
        });

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                showQuizIntroDialog();
            }
        });

        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }


    public void changeFragment(Fragment fragment, String fragment_title){
        fragmentManager.beginTransaction().replace(R.id.operations, fragment, null).commit();
        getSupportActionBar().setTitle(fragment_title);
        if (fragment.equals(emptyFragment)){ onOff = 0; }
        else{ onOff = 1; }

    }


    public void onClickList(View v){
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        startActivity(intent);

//        Button bn = (Button) v;
//        bn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    public void onClickNumber(View v){
        Button btn = (Button) v;

        sCalculation += btn.getText();
        number_one += btn.getText();
        numberOne = Double.parseDouble(number_one);

        if(root_present){
            numberOne = Math.sqrt(numberOne);
        }

        switch(current_operator){
            case "":
            case "+":
                if (power_present){
                    temp = result + Math.pow(numberTwo,numberOne);
                }else {
                    temp = result +numberOne;
                }break;
            case "-":
                if (power_present){
                    temp = result - Math.pow(numberTwo,numberOne);
                }else {
                    temp = result - numberOne;
                }break;
            case "x":
                if (power_present){
                    temp = result * Math.pow(numberTwo,numberOne);
                }else {
                    temp = result * numberOne;
                }break;
            case "/":
                try{
                    if (power_present){
                        temp = result / Math.pow(numberTwo,numberOne);
                    }else {
                    }temp = result / numberOne;
                } catch (Exception e){
                    sAnswer = e.getMessage();
                }
                break;
        }
        sAnswer = format.format(temp);
        updateCalculation();
    }

    public void onClickOperator(String operator){

        if(!sAnswer.equals("")){
            if (!current_operator.equals("")){
                char c = getCharFromLast(sCalculation);
                if (c == '+' || c == '-' || c == '/' || c == 'x'){
                    sCalculation = sCalculation.substring(0, sCalculation.length()-3);
                }
            }
            sCalculation += operator;
            number_one = "";
            number_two = "";
            numberOne = 0.0;
            numberTwo = 0.0;
            result = temp;
            current_operator = operator;
            updateCalculation();
            dot_present = false;
            root_present = false;
            invert_allow = true;
            power_present = false;
            value_inverted = false;
            constant_presnet = false;
            factorial_present = false;
        }
    }

    public void onClickModulo(View v){
        if(!sAnswer.equals("")){
            sCalculation += "% ";
            switch (current_operator){
                case "":
                    temp = temp / 100;
                    break;
                case "+":
                    temp = result + ((result * numberOne) / 100);
                    break;
                case "*":
                    temp = result * (numberOne / 100);
                    break;
                case "/":
                    try{
                        temp = result / (numberOne / 100);
                    }catch (Exception e){
                        sAnswer = e.getMessage();
                    }
                    break;
            }
            sAnswer = format.format(temp);
            if (sAnswer.length() > 9){
                sAnswer = longFormat.format(temp);
            }
            result = temp;
            showResult();

        }
    }

    public void onClickDot(View v){
        if(!dot_present){
            if(number_one.length() == 0){
                number_one = "0.";
                sCalculation += "0.";
                sAnswer = "0.";
                dot_present = true;
                updateCalculation();
            }
            else{
                number_one += ".";
                sCalculation += ".";
                sAnswer += ".";
                dot_present = true;
                updateCalculation();
            }
        }
    }

    public void onClickPlusOrMinus(View v){
       if (invert_allow){
           if (!sAnswer.equals("")){
               numberOne = -numberOne;
               number_one = format.format(numberOne);
               switch (current_operator){
                   case "":
                       temp = numberOne;
                       sCalculation = number_one;
                       break;
                   case "+":
                       temp = result + numberOne;
                       removeUntilChar(sCalculation, ' ');
                       sCalculation += number_one;
                       break;
                   case "-":
                       temp = result - numberOne;
                       removeUntilChar(sCalculation, ' ');
                       sCalculation += number_one;
                       break;
                   case "*":
                       temp = result * numberOne;
                       removeUntilChar(sCalculation, ' ');
                       sCalculation += number_one;
                       break;
                   case "/":
                       try {
                           temp = result / numberOne;
                           removeUntilChar(sCalculation, ' ');
                           sCalculation += number_one;
                       }catch (Exception e){
                           sAnswer = e.getMessage();
                       }
                       break;
               }
               sAnswer = format.format(temp);
               value_inverted = !value_inverted;
               updateCalculation();
           }
       }
    }

    public void onClickClear(View v){
        clearData();
    }

    public  void clearData(){
        answer.setText("");
        sCalculation = "";
        sAnswer = "";
        current_operator = "";
        number_one = "";
        number_two = "";
        temp = 0.0;
        result = 0.0;
        numberOne = 0.0;
        numberTwo= 0.0;
        updateCalculation();
        dot_present = false;
        root_present = false;
        invert_allow = true;
        power_present = false;
        value_inverted = false;
        constant_presnet = false;
        factorial_present = false;
    }

    public String removeChar(String str, int i){
        char c = str.charAt(str.length()-i);
        if (c == '.'){
            dot_present = false;
        }
        if (c == '^'){
            power_present = false;
        }
        if (c == ' '){
            return str.substring(0, str.length() - (i-1));
        }
        return str.substring(0, str.length()-i);
    }

    public void onClickDelete(View v){
        if (!sAnswer.equals("")){
            if (number_one.length() < 2 && !current_operator.equals("")){
                number_one = "";
                temp = result;
                sAnswer = format.format(result);
                sCalculation = removeChar(sCalculation, 1);
                updateCalculation();
            }else {
                switch (current_operator){
                    case "":
                        if(value_inverted){
                            sAnswer = sAnswer.substring(1);
                            sCalculation =sCalculation.substring(1,sAnswer.length());
                            updateCalculation();
                        }
                        if(sCalculation.length() < 2){
                            clearData();
                        }else{
                            if (getCharFromLast(sCalculation) == '.'){
                                dot_present = false;
                            }
                            number_one = removeChar(number_one,1);
                            numberOne = Double.parseDouble(number_one);
                            temp = numberOne;
                            sCalculation = number_one;
                            sAnswer = number_one;
                            updateCalculation();
                        }break;
                    case "+":
                        if (value_inverted){
                            numberOne = numberOne*(-1);
                            number_one = format.format(temp);
                            temp = result + numberOne;
                            sAnswer = format.format(temp);
                            removeUntilChar(sCalculation, ' ');
                            sCalculation += number_one;
                            updateCalculation();
                            value_inverted = !value_inverted;
                        }
                        if (getCharFromLast(sCalculation) == '.'){
                            dot_present = false;
                        }
                        number_one = removeChar(number_one, 1);
                        if (number_one.length() == 1 && number_one.equals(".")){
                            numberOne = Double.parseDouble(number_one);
                        }
                        numberOne = Double.parseDouble(number_one);
                        temp = result + numberOne;
                        sAnswer = format.format(temp);
                        sCalculation = removeChar(sCalculation, 1);
                        updateCalculation();
                        break;
                    case "-":
                        if (value_inverted){
                            numberOne = numberOne*(-1);
                            number_one = format.format(temp);
                            temp = result - numberOne;
                            sAnswer = format.format(temp);
                            removeUntilChar(sCalculation, ' ');
                            sCalculation += number_one;
                            updateCalculation();
                            value_inverted = !value_inverted;
                        }
                        if (getCharFromLast(sCalculation) == '.'){
                            dot_present = false;
                        }
                        number_one = removeChar(number_one, 1);
                        if (number_one.length() == 1 && number_one.equals(".")){
                            numberOne = Double.parseDouble(number_one);
                        }
                        numberOne = Double.parseDouble(number_one);
                        temp = result - numberOne;
                        sAnswer = format.format(temp);
                        sCalculation = removeChar(sCalculation, 1);
                        updateCalculation();
                        break;
                    case "x":
                        if (value_inverted){
                            numberOne = numberOne*(-1);
                            number_one = format.format(temp);
                            temp = result * numberOne;
                            sAnswer = format.format(temp);
                            removeUntilChar(sCalculation, ' ');
                            sCalculation += number_one;
                            updateCalculation();
                            value_inverted = !value_inverted;
                        }
                        if (getCharFromLast(sCalculation) == '.'){
                            dot_present = false;
                        }
                        number_one = removeChar(number_one, 1);
                        if (number_one.length() == 1 && number_one.equals(".")){
                            numberOne = Double.parseDouble(number_one);
                        }
                        numberOne = Double.parseDouble(number_one);
                        temp = result * numberOne;
                        sAnswer = format.format(temp);
                        sCalculation = removeChar(sCalculation, 1);
                        updateCalculation();
                        break;
                    case "/":
                        try{
                            if (value_inverted){
                                numberOne = numberOne*(-1);
                                number_one = format.format(temp);
                                temp = result / numberOne;
                                sAnswer = format.format(temp);
                                removeUntilChar(sCalculation, ' ');
                                sCalculation += number_one;
                                updateCalculation();
                                value_inverted = !value_inverted;
                            }
                            if (getCharFromLast(sCalculation) == '.'){
                                dot_present = false;
                            }
                            number_one = removeChar(number_one, 1);
                            if (number_one.length() == 1 && number_one.equals(".")){
                                numberOne = Double.parseDouble(number_one);
                            }
                            numberOne = Double.parseDouble(number_one);
                            temp = result / numberOne;
                            sAnswer = format.format(temp);
                            sCalculation = removeChar(sCalculation, 1);
                            updateCalculation();
                        }catch (Exception e){
                            sAnswer = e.getMessage();
                        }
                        break;
                }
            }
        }
    }

    public void onClickEqualSign(View v){
        showResult();
    }

    public void onClickRoot(String root){
        if(sAnswer.equals("") && result ==0 && !root_present){
            sCalculation = root;
            root_present = true;
            invert_allow = false;
            updateCalculation();
        }else if(!current_operator.equals("") && !root_present){
            sCalculation += root;
            root_present = true;
            invert_allow = false;
            updateCalculation();
        }
    }

    public void  onClickPower(String power){
        if(!sCalculation.equals("") && !root_present && !power_present){
            sCalculation += power;
            number_two = number_one;
            numberTwo = numberOne;
            number_one = "";
            power_present = true;
            updateCalculation();
        }
    }

    public void onClickSquare(){
        if (!sCalculation.equals("") && !sAnswer.equals("")){
            if (!root_present && !power_present){
                numberOne = numberOne * numberOne;
                number_one = format.format(numberOne);

                if (current_operator.equals("")){
                    if (number_one.length() > 9){
                        number_one = longFormat.format(numberOne);
                    }
                    sCalculation = number_one;
                    temp = numberOne;
                }else{
                    switch (current_operator){
                        case "+":
                            temp = result + numberOne;
                            break;
                        case "-":
                            temp = result - numberOne;
                            break;
                        case "x":
                            temp = result * numberOne;
                            break;
                        case "/":
                            try{
                                temp = result / numberOne;
                            }catch (Exception e){
                                sAnswer = e.getMessage();
                            }
                            break;
                    }
                    removeUntilChar(sCalculation, ' ');
                    if (number_one.length() > 9){
                        number_one = longFormat.format(numberOne);
                    }
                    sCalculation += number_one;
                }
                sAnswer = format.format(temp);
                if (sAnswer.length() > 9){
                    sAnswer = longFormat.format(temp);
                }
                updateCalculation();
            }
        }
    }

    public void onClickFactorial(){
        if (!sAnswer.equals("") && !factorial_present && !root_present && !dot_present && !power_present){
            for (int i=1; i<Integer.parseInt(number_one); i++) {
                numberOne *= i;
            }
            number_one = format.format(numberOne);
            switch (current_operator){
                case "":
                    result =numberOne;
                    break;
                case "+":
                    result += numberOne;
                    break;
                case "-":
                    result -= numberOne;
                    break;
                case "x":
                    result *= numberOne;
                    break;
                case "/":
                    try{
                        result /= numberOne;
                    }catch (Exception e){
                        sAnswer = e.getMessage();
                    }
                    break;
            }
            sAnswer = result.toString();
            temp = result;
            sCalculation += "!";
            factorial_present = true;
            updateCalculation();
        }
    }

    public void onClickInverse(){
        if (!sAnswer.equals("") && !factorial_present && !root_present && !dot_present && !power_present){
            numberOne = Math.pow(numberOne, -1);
            number_one = format.format(numberOne);
            switch (current_operator){
                case "":
                    temp = numberOne;
                    sCalculation = number_one;
                    break;
                case "+":
                    temp = result + numberOne;
                    removeUntilChar(sCalculation, ' ');
                    sCalculation += number_one;
                    break;
                case "-":
                    temp = result - numberOne;
                    removeUntilChar(sCalculation, ' ');
                    sCalculation += number_one;
                    break;
                case "x":
                    temp = result * numberOne;
                    removeUntilChar(sCalculation, ' ');
                    sCalculation += number_one;
                    break;
                case "/":
                    try{
                        temp = result / numberOne;
                        removeUntilChar(sCalculation, ' ');
                        sCalculation += number_one;
                    }catch (Exception e){
                        sAnswer = e.getMessage();
                    }
                    break;
            }
            sAnswer = format.format(temp);
            updateCalculation();
        }
    }

    public void onClickPIorE(String constant) {
        if (!root_present && !dot_present && !power_present && !factorial_present && !constant_presnet){
            String str_PIorE = constant;
            if (!str_PIorE.equals("e")){
                str_PIorE = "\u03A0";
            }
            if (sCalculation.equals("")) {
                number_one = str_PIorE;
                if (str_PIorE.equals("e")){
                    numberOne = Math.E;
                }else {
                    numberOne = Math.PI;
                }
                temp = numberOne;
                sCalculation += number_one;
                sAnswer = format.format(temp);
                updateCalculation();
            }else{
                if (str_PIorE.equals("e")){
                    numberOne = getCharFromLast(sCalculation) == ' '? Math.E: Double.parseDouble(number_one) * Math.E;
                }else {
                    numberOne = getCharFromLast(sCalculation) == ' '? Math.PI: Double.parseDouble(number_one) * Math.PI;
                }

                switch (current_operator){
                    case "":
                    case "+":
                        temp = result + numberOne;
                        break;
                    case "-":
                        temp = result - numberOne;
                        break;
                    case "x":
                        temp = result * numberOne;
                        break;
                    case "/":
                        try{
                            temp = result / numberOne;
                        }catch (Exception e){
                            sAnswer = e.getMessage();
                        }
                        break;
                }
                sCalculation += str_PIorE;
                sAnswer = format.format(temp);
                updateCalculation();
            }
            constant_presnet = true;
        }
    }


    public void showResult (){
        changeFragment(emptyFragment, "Lets Calculate");
        answer.setText(String.format("= %s", sAnswer));
        dot_present = true;
        power_present = false;
        value_inverted = false;
        constant_presnet = false;
        factorial_present = false;
    }

    public void updateCalculation(){
        calculation.setText(sCalculation);
        answer.setText(sAnswer);
    }

    private char getCharFromLast(String s) {
        return s.charAt(s.length()-1);
    }

    public void removeUntilChar(String str, char chr){
        char c = getCharFromLast(str);
        if (c  != chr){
            str = str.substring(0, str.length()-1);
            sCalculation = str;
            updateCalculation();
//            removeUntilChar(str, chr);
        }
    }

}
