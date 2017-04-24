package com.billzhonggz.week11;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ZHONG on 2017/4/24.
 */
public class ViewSimple extends TextView implements IModelListener {
    private Bank model;
    private ControllerSimple controller;

    public ViewSimple(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setMVC(Bank md, ControllerSimple ct) {
        this.model = md;
        this.controller = ct;
// The setMVC method registers the view with the model and updates
// the text of the ViewSimple to display the total amount of money
// in all the bank accounts of the bank.
        model.addListener(this);
        notifyModelListener();
// No need for any click listener.
    }

    // The notifyModelListener method updates the text of the ViewSimple as
// necessary so that the ViewSimple always displays the current value of
// the total amount of money in all the bank accounts of the bank.
    @Override
    public void notifyModelListener() {
        this.post(new Runnable() {
            @Override
            public void run() {
                ViewSimple viewSimple = (ViewSimple)findViewById(R.id.textViewSimple);
                viewSimple.setText("total money: " + model.totalMoney());
            }
        });
    }
}
