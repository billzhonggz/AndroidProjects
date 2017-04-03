package com.billzhonggz.week9;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ZHONG on 2017/4/3.
 */
public class ViewSimple extends TextView implements IModelListener{
    private Bank model;
    private ControllerSimple controller;

    public ViewSimple(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setMVC (Bank md, ControllerSimple ct) {
        md.addModelListener(this);
    }

    public void notifyModelListener() {
        // Update texts.
        this.setText("Total money of the bank: " + model.totalMoney());
    }
}
