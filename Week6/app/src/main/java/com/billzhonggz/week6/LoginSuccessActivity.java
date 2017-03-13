package com.billzhonggz.week6;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        // Get button
        Button logout = (Button)findViewById(R.id.logout);
        // Set listener
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iLogoutConfirm = new Intent(view.getContext(), LogoutConfirmActivity.class);
                startActivity(iLogoutConfirm);
            }
        });

        // Get button & textview
        Button goWebsite = (Button)findViewById(R.id.goWebsite);
        final EditText websiteAddress = (EditText)findViewById(R.id.website_address);
        // Set listener.
        goWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get website address.
                String address = websiteAddress.getText().toString();
                // Create activity.
                Intent iWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse(address));
                startActivity(iWebsite);
            }
        });

        // Get button & textview
        Button goSearch = (Button)findViewById(R.id.goSearch);
        final EditText searchKeyword = (EditText)findViewById(R.id.search_keyword);
        // Set listener
        goSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get search keyword
                String keyword = searchKeyword.getText().toString();
                // Create activity
                Intent iSearch = new Intent(Intent.ACTION_WEB_SEARCH);
                iSearch.putExtra(SearchManager.QUERY,keyword);
                startActivity(iSearch);
            }
        });
    }
}
