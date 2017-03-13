package com.billzhonggz.week6;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginFailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_failed);

        // Get button
        Button again = (Button)findViewById(R.id.try_again);
        // Create listener.
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity a = (Activity)view.getContext();
                a.finish();
            }
        });
    }
}
