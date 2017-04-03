package com.billzhonggz.week9;

/**
 * Created by ZHONG on 2017/4/3.
 */
public class CreditAccount extends Account {
    public CreditAccount(String name, int money) {
        super(name, money);
    }

    public void withdraw (int amount) {
        this.setMoney(this.getMoney() - amount);
    }

    public static void TestCreditAccount() {
        CreditAccount ca = new CreditAccount("TestCredit", 10);
        //System.out.println("CreditAccount Name: " + ca.getName() + "Money: " + ca.getMoney());
        System.out.println(ca.getName().equals("TestCredit"));
        System.out.println(ca.getMoney() == 10);
        ca.withdraw(20);
        System.out.println(ca.getMoney() == -10);
    }
}
