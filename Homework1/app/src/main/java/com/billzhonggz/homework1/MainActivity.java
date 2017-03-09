package com.billzhonggz.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // Declare a random object.
    private final Random r = new Random();
    private int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the TextViews display digits.
        final TextView hundreds = (TextView)findViewById(R.id.hundreds);
        final TextView tens = (TextView)findViewById(R.id.tens);
        final TextView units = (TextView)findViewById(R.id.units);

        // Define actions of plus buttons.
        // Find the buttons.
        Button plus_hundreds = (Button)findViewById(R.id.plus_hundreds);
        Button plus_tens = (Button)findViewById(R.id.plus_tens);
        Button plus_units = (Button)findViewById(R.id.plus_units);
        // Define actions of TextViews.
        // Set listeners.
        plus_hundreds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get text from the TextView.
                i = Integer.parseInt(hundreds.getText().toString());
                // Test and set.
                if (i == 9)
                    i = 0;
                else
                    i++;
                hundreds.setText(Integer.toString(i));
            }
        });
        plus_tens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get text from the TextView.
                i = Integer.parseInt(tens.getText().toString());
                // Test and set.
                if (i == 9)
                    i = 0;
                else
                    i++;
                tens.setText(Integer.toString(i));
            }
        });
        plus_units.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get text from the TextView.
                i = Integer.parseInt(units.getText().toString());
                // Test and set.
                if (i == 9)
                    i = 0;
                else
                    i++;
                units.setText(Integer.toString(i));
            }
        });
        // Generate a random number.
        int number = r.nextInt(1000);

    }
}
