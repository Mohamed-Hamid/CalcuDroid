package com.hamid.company.calcudroid;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.security.Key;

/**
 * Created by Mohamed on 3/6/2015.
 */
public class KeyboardAdapter extends BaseAdapter{

    private Context context;
    private int portHeight;
    private Button.OnClickListener onClickListener;
    private Button.OnLongClickListener clearOnLongClickListener, activityOnLongClickListner;

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

    public void setOnButtonClickListener(Button.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    public void setClearOnButtonLongClickListner(Button.OnLongClickListener onButtonLongClickListner){
        this.clearOnLongClickListener = onButtonLongClickListner;
    }

    public void setActivityOnButtonLongCLickListner(Button.OnLongClickListener onButtonLongCLickListner ){
        this.activityOnLongClickListner = onButtonLongCLickListner;
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
            button.setText(keyboardButtons[position].getButtonText());
            button.setHeight(portHeight / 10 + 10);
            button.setTextColor(Color.WHITE);
            button.setTextSize(20);
            //make it a beardless button
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true);
            button.setBackgroundResource(typedValue.resourceId);

            button.setOnClickListener(onClickListener);
            if (keyboardButtons[position].equals(KeyboardButton.DEL)){
                button.setOnLongClickListener(clearOnLongClickListener);
            }

            if (keyboardButtons[position].equals(KeyboardButton.ANS)){
                button.setOnLongClickListener(activityOnLongClickListner);
            }

        } else {
            button = (Button) convertView;
        }

        return button;
    }

    private KeyboardButton[] keyboardButtons = {
        KeyboardButton.OPNBRCKT, KeyboardButton.CLSDBRCKT, KeyboardButton.DIV, KeyboardButton.DEL, KeyboardButton.SEVEN,
        KeyboardButton.EIGHT, KeyboardButton.NINE, KeyboardButton.MUL, KeyboardButton.FOUR, KeyboardButton.FIVE,
        KeyboardButton.SIX, KeyboardButton.MINUS, KeyboardButton.ONE, KeyboardButton.TWO, KeyboardButton.THREE,
        KeyboardButton.PLUS, KeyboardButton.DECPNT, KeyboardButton.ZERO, KeyboardButton.DUMMY, KeyboardButton.ANS
    };
}
