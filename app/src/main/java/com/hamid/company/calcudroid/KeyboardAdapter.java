package com.hamid.company.calcudroid;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

/**
 * Created by Mohamed on 3/6/2015.
 */
public class KeyboardAdapter extends BaseAdapter{

    private Context context;
    private int portHeight;

    public KeyboardAdapter(Context context, int portHeight) {
        this.portHeight = portHeight;
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public int getCount() {
        return keyboardButtons.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;

        if (convertView == null){
            button = new Button(context);
            button.setTag(keyboardButtons[position]);
        } else {
            button = (Button) convertView;
        }

        button.setText(keyboardButtons[position].getButtonText());
        button.setHeight(portHeight / 10 + 10);
        button.setTextColor(Color.WHITE);
        button.setTextSize(20);

        //make it a beardless button
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true);
        button.setBackgroundResource(typedValue.resourceId);
        return button;
    }

    private KeyboardButton[] keyboardButtons = {
        KeyboardButton.C, KeyboardButton.DIV, KeyboardButton.MUL, KeyboardButton.R, KeyboardButton.SEVEN,
        KeyboardButton.EIGHT, KeyboardButton.NINE, KeyboardButton.MINUS, KeyboardButton.FOUR, KeyboardButton.FIVE,
        KeyboardButton.SIX, KeyboardButton.PLUS, KeyboardButton.ONE, KeyboardButton.TWO, KeyboardButton.THREE,
        KeyboardButton.ZERO, KeyboardButton.BRCKT, KeyboardButton.DECPNT, KeyboardButton.DUMMY, KeyboardButton.ANS
    };
}
