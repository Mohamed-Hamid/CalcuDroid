package com.hamid.company.calcudroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.hamid.company.expressionEvaluator.Expr;
import com.hamid.company.expressionEvaluator.Parser;
import com.hamid.company.expressionEvaluator.SyntaxException;


public class MainActivity extends Activity {

    KeyboardAdapter keyboardAdapter;
    GridView gridView;
    TextView userInput, userResult;
    HorizontalScrollView horizontalScrollView;
    double result;
    boolean operator = false, decimalPoint = true;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.grid);
        userInput = (TextView) findViewById(R.id.user_input);
        userResult = (TextView) findViewById(R.id.answer);
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.hsv);
        context = this;

        keyboardAdapter = new KeyboardAdapter(this, metrics.heightPixels);
        gridView.setAdapter(keyboardAdapter);

        keyboardAdapter.setOnButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                processInput((KeyboardButton)button.getTag());
            }
        });

        keyboardAdapter.setClearOnButtonLongClickListner(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                userInput.setText("0");
                userResult.setText("0");
                operator = false;
                decimalPoint = true;
                return true;
            }
        });

        keyboardAdapter.setActivityOnButtonLongCLickListner(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
            Intent intent = new Intent(context, ResultActivity.class);
            intent.putExtra("expression", userInput.getText().toString());
            intent.putExtra("result", ""+calculateExpression(userInput.getText().toString()));
            startActivity(intent);
            return true;
            }
        });
    }

    void processInput(KeyboardButton button){
    String userInputText = userInput.getText().toString();
    int userInputLength = userInputText.length();
    switch (button) {
            case ANS:
                double ans;
                try {
                    ans = calculateExpression(userInputText);
                    userResult.setText(ans+"");
                } catch (Exception e){
                    userResult.setText("Error");
                }
                break;
            case DEL:
                if (userInputLength == 1){
                    userInput.setText("0");
                    operator = false;
                    decimalPoint = true;
                } else {
                    if (userInputText.charAt(userInputLength-1) =='.'){
                        decimalPoint = true;
                    }
                    userInput.setText(userInputText.substring(0, userInputLength-1));
                    userInputText = userInput.getText().toString();
                    userInputLength = userInput.length();
                    if (Character.isDigit(userInputText.charAt(userInputLength-1)) ||
                        userInputText.charAt(userInputLength-1) == '(' ||
                        userInputText.charAt(userInputLength - 1) == ')'||
                        userInputText.charAt(userInputLength - 1 ) == '.'){
                        operator = true;
                    } else { //operator
                        operator = false;
                        decimalPoint = true;
                    }
                }
                break;
            case DECPNT:
                if (decimalPoint){
                    userInput.append(button.getButtonText());
                    decimalPoint = false;
                }
                break;
            case PLUS:
            case MINUS:
            case MUL:
            case DIV:
                if (operator){ //waiting for an operator
                    userInput.append(button.getButtonText());
                    operator = false;
                } else { //there is already an operator
                    if (button.getButtonText().equals("-") && (userInputText.charAt(userInputLength-1)=='*'
                    || userInputText.charAt(userInputLength-1)=='/')){
                        userInput.append(button.getButtonText());
                    } else {
                        userInput.setText(userInputText.substring(0,userInputLength-1)+button.getButtonText());
                    }
                }
                decimalPoint = true;
            break;
            default: //numbers
                if (userInputText.equals("0")) { //first digit
                    userInput.setText(button.getButtonText());
                    operator = true;
                    decimalPoint = true;
                } else { //subsequent digits
                    userInput.append(button.getButtonText());
                    operator = true;
                }
                break;
        }
        horizontalScrollView.fullScroll(View.FOCUS_RIGHT);
    }

    private double calculateExpression(String expression) {
       //  InfixEvaluator infixEvaluator = new InfixEvaluator();
       // return infixEvaluator.infix(expression);
       // return (double) InfixEvaluator.evaluate(expression);
        Expr expr = null;
        try {
            expr = Parser.parse(expression);
        } catch (SyntaxException e) {
            userResult.setText(e.explain());
        }
        return expr.value();
    }
}
