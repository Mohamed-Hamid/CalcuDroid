package com.hamid.company.calcudroid;

/**
 * Created by Mohamed on 3/7/2015.
 */
import android.util.Log;

import java.util.Stack;

public class InfixEvaluator {
    public static double evaluate(String expression) {
        char[] tokens = expression.toCharArray();
        Stack<Double> values = new Stack<>();

        for (char token : tokens) {
            Log.w("EE", "*" + token + "*");
        }

        Stack<Character> ops = new Stack<>();
      //  Log.w("YES", tokens[1]+" ");
        for (int i = 0; i < tokens.length; i++) {
           if (tokens[i] == ' ')
                continue;

            if ((tokens[i] >= '0' && tokens[i] <= '9' ) || tokens[i] == '.') {
                StringBuilder sbuf = new StringBuilder();

                while (i < tokens.length && (( tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.'))
                    sbuf.append(tokens[i++]);
                i--;
                values.push(Double.parseDouble(sbuf.toString()));
            }
            else if (tokens[i] == '(')
                ops.push(tokens[i]);
            else if (tokens[i] == ')') {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }
            else if (tokens[i] == '+' || tokens[i] == '-' ||
                    tokens[i] == '*' || tokens[i] == '/') {
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.push(tokens[i]);
            }
        }

        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        return values.pop();
    }

    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        return !((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'));
    }

    public static double applyOp(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }

}