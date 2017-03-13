/*
 * Mobile Application Development
 * Homework 1
 * 1430003045 Zhong Junru (Bill)
 * March 9, 2017
 */
package com.billzhonggz.homework1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // Declare a random object.
    private final Random r = new Random();
    private int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Generate a random number.
        final int number = r.nextInt(1000);

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
                ClickChangeTest(hundreds,1,number);
            }
        });
        plus_tens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickChangeTest(tens,1,number);
            }
        });
        plus_units.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickChangeTest(units,1,number);
            }
        });

        // Define actions of subtractions.
        // Find the buttons.
        Button sub_hundreds = (Button)findViewById(R.id.sub_hundreds);
        Button sub_tens = (Button)findViewById(R.id.sub_tens);
        Button sub_units = (Button)findViewById(R.id.sub_units);
        // Set listeners.
        sub_hundreds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickChangeTest(hundreds,0,number);
            }
        });
        sub_tens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickChangeTest(tens,0,number);
            }
        });
        sub_units.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickChangeTest(units,0,number);
            }
        });

        // Define restart button
        Button restart = (Button)findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = getIntent();
                finish();
                startActivity(i);
            }
        });
    }

    // Function executes when button clicked.
    // sign = 1 for plus, 0 for subtractions.
    protected void ClickChangeTest (TextView tv, int sign, int number){
        // Get current number.
        i = Integer.parseInt(tv.getText().toString());
        // Test and set.
        if (sign == 1){
            if (i == 9)
                i = 0;
            else
                i++;
        }
        if (sign == 0){
            if (i == 0)
                i = 9;
            else
                i--;
        }
        tv.setText(Integer.toString(i));

        // Test with random number.
        // Get current numbers and combine.
        TextView h = (TextView)findViewById(R.id.hundreds);
        TextView t = (TextView)findViewById(R.id.tens);
        TextView u = (TextView)findViewById(R.id.units);
        String sNumber = h.getText().toString() + t.getText().toString() + u.getText().toString();
        int guessNumber = Integer.parseInt(sNumber);
        // Make comparison
        if (guessNumber < number)
            Toast.makeText(this,getString(R.string.low),Toast.LENGTH_SHORT).show();
        else if (guessNumber > number)
            Toast.makeText(this,getString(R.string.high),Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,getString(R.string.win),Toast.LENGTH_SHORT).show();
    }
}
