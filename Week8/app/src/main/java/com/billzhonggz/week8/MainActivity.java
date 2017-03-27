package com.billzhonggz.week8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume () {
        super.onResume();
        MyDrawingView mdv = (MyDrawingView)findViewById(R.id.drawing_area);
        mdv.startTicker();
    }

    @Override
    protected void onPause () {
        super.onPause();
        MyDrawingView mdv = (MyDrawingView)findViewById(R.id.drawing_area);
        mdv.stopTicker();
    }
}
