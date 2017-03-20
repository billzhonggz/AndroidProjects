package com.billzhonggz.week7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find clear button.
        Button clear = (Button)findViewById(R.id.clearBtn);
        Button undo = (Button)findViewById(R.id.undoBtn);
        // Find view.
        final MyDrawingView draw = (MyDrawingView)findViewById(R.id.drawing_area);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draw.clearAllPoints();
            }
        });
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draw.undoPoint();
            }
        });
    }
}
