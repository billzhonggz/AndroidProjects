package com.billzhonggz.week10;

/**
 * Created by ZHONG on 2017/4/10.
 */
public interface IAccount {
    String getName();
    int getMoney();
    void withdraw(int amount) throws NotEnoughMoneyException;
}
