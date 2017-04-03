package com.billzhonggz.week9;

/**
 * Created by ZHONG on 2017/4/3.
 */
public abstract class Account implements IAccount {
    private String name;
    private int money;

    public Account(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    protected void setMoney(int money) {
        this.money = money;
    }

    public abstract void withdraw(int amount) throws NotEnoughMoneyException;

    public static void TestAccount() {}
}
