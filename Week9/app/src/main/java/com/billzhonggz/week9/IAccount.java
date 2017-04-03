package com.billzhonggz.week9;

/**
 * Created by ZHONG on 2017/4/3.
 */
public interface IAccount {
    public String getName();
    public int getMoney();
    public void withdraw(int amount) throws NotEnoughMoneyException;
}
