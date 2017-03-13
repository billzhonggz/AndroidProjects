package com.billzhonggz.week6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find login button
        Button login = (Button)findViewById(R.id.login);
        // Get inputted text.
        final EditText name = (EditText)findViewById(R.id.name);
        final EditText pwd = (EditText)findViewById(R.id.password);

        // Create listener.
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = name.getText().toString();
                String p = pwd.getText().toString();
                // Find if name & pwd is correct.
                if (n.equals("bill") && p.equals("UIC"))
                {
                    Intent iSuccess = new Intent(view.getContext(), LoginSuccessActivity.class);
                    startActivity(iSuccess);
                }
                else
                {
                    Intent iFailed = new Intent(view.getContext(), LoginFailedActivity.class);
                    startActivity(iFailed);
                }
            }
        });
    }
}
