package com.billzhonggz.homework3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize MVC.
        Model m = new Model(this);
        ControllerNew cn = new ControllerNew(m);
        ControllerSnakes cs = new ControllerSnakes(m);
        ViewNew vn = (ViewNew) findViewById(R.id.view_new);
        ViewSnakes vs = (ViewSnakes) findViewById(R.id.view_snakes);
        vn.setMVC(m, cn);
        vs.setMVC(m, cs);
    }
}
