package com.billzhonggz.week6;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogoutConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout_confirm);

        // Get buttons.
        Button logoutYes = (Button)findViewById(R.id.logout_yes);
        // Set listener.
        logoutYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iLogoutConfirmYes = new Intent(view.getContext(), MainActivity.class);
                iLogoutConfirmYes.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(iLogoutConfirmYes);
            }
        });

        // Get no buttom.
        Button logoutNo = (Button)findViewById(R.id.logout_no);
        // Set listener.
        logoutNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity a = (Activity)view.getContext();
                a.finish();
            }
        });
    }
}
