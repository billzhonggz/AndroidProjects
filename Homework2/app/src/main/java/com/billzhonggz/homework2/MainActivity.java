package com.billzhonggz.homework2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Homework Assignment 2
 * Mobile Application Development
 * 1430003045 Junru Zhong (Bill)
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up views.
        ViewBubbles vb = (ViewBubbles) findViewById(R.id.view_bubbles);
        ViewScore vs = (ViewScore) findViewById(R.id.view_score);
        ViewClear vc = (ViewClear) findViewById(R.id.view_clear);
        // Initialize model and controllers.
        Model m = new Model();
        ControllerBubbles controllerBubbles = new ControllerBubbles(m);
        ControllerScore controllerScore = new ControllerScore(m);
        ControllerClear controllerClear = new ControllerClear(m);
        // Set up MVC
        vb.setMVC(m, controllerBubbles);
        vs.setMVC(m, controllerScore);
        vc.setMVC(m, controllerClear);
        // Start tick.
        vb.startTicker();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ViewBubbles vb = (ViewBubbles) findViewById(R.id.view_bubbles);
        // Stop tick.
        vb.stopTicker();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewBubbles vb = (ViewBubbles) findViewById(R.id.view_bubbles);
        // Restart tick.
        vb.startTicker();
    }
}
