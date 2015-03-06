package com.hamid.company.calcudroid;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.TextView;


public class MainActivity extends Activity {

    KeyboardAdapter keyboardAdapter;
    GridView gridView;
    TextView userInput, result;

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
        result = (TextView) findViewById(R.id.answer);

        keyboardAdapter = new KeyboardAdapter(this, metrics.heightPixels);
        gridView.setAdapter(keyboardAdapter);
    }
}
