package com.billzhonggz.week9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Bank b;
    private ControllerSimple ct;
    private ViewSimple vs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vs = (ViewSimple)findViewById(R.id.view_simple);
    }
}
