package com.billzhonggz.week4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//    // Every listener for button should implements this interface.
//    private class MyClickListener implements View.OnClickListener{
//
//        @Override
//        public void onClick(View view) {
//            Toast.makeText(view.getContext(),"Button 0 has been clicked!",Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"This is a test message.",Toast.LENGTH_SHORT).show();
        Log.d("onCreate","a message");
        Button b = (Button)findViewById(R.id.button11);
//        MyClickListener l = new MyClickListener();
        b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Button 0 has been clicked!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
