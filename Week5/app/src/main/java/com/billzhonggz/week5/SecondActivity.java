package com.billzhonggz.week5;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button web = (Button)findViewById(R.id.webBtn);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://uic.edu.hk/"));
                startActivity(i);
            }
        });

        Button back = (Button)findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Find the activity which the button belongs.
                Activity a = (Activity)view.getContext();
                // Tell Android to finish this activity.
                a.finish();
            }
        });

        Button next = (Button)findViewById(R.id.nextBtn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ThirdActivity.class);
                startActivity(i);
                // Add code to finish this activity.
                Activity a = (Activity)view.getContext();
                a.finish();
            }
        });
    }
}
