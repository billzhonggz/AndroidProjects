package com.billzhonggz.week5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Initialize changing variable.
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up text view.
        final TextView t = (TextView) findViewById(R.id.textView);
        t.setText(Integer.toString(i));

        // Find out the buttons for increment in xml file.
        Button add = (Button) findViewById(R.id.addBtn);
        Button minor = (Button) findViewById(R.id.minorBtn);
        Button reset = (Button) findViewById(R.id.resetBtn);
        // Do add.
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Increment i and update.
                i++;
                String display = Integer.toString(i);
                t.setText(display);
            }
        });

        // Do minor.
        minor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Decrease i.
                i--;
                String display = Integer.toString(i);
                t.setText(display);
            }
        });

        // Do reset.
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Reset i.
                i = 0;
                String display = Integer.toString(i);
                t.setText(display);
            }
        });

        // Checkbox and toasts.
        // Find the checkbox.
        final CheckBox cb = (CheckBox) findViewById(R.id.checkBox);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb.isChecked()) {
                    Toast.makeText(view.getContext(), "Checked!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(view.getContext(), "Unchecked!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
