package com.billzhonggz.week11;

/**
 * Created by ZHONG on 2017/4/24.
 */
public interface IAccount {
    String getName();
    int getMoney();
    void withdraw(int amount) throws NotEnoughMoneyException;
}
