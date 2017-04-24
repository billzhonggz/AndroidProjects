package com.billzhonggz.week11;

import java.util.Random;

/**
 * Created by ZHONG on 2017/4/24.
 */
public class CreditAccount extends Account {
    public CreditAccount(String name, int money) {
        super(name, money);
    }
    private Random r = new Random();

    public static void TestCreditAccount() {
        CreditAccount c = new CreditAccount("Philippe", 1000);
        System.out.println(c.getName() == "Philippe");
        System.out.println(c.getMoney() == 1000);
        c.setMoney(2000);
        System.out.println(c.getMoney() == 2000);
        c.withdraw(500);
        System.out.println(c.getMoney() == 1500);
        c.withdraw(2000);
        System.out.println(c.getMoney() == -500);
    }

    // The withdraw method of the CreditAccount class simply subtracts the
// amount of money given as argument to the method from the amount of
// money currently stored in the account. A credit account is allowed to
// have a negative amount of money in the account, so money can be
// withdrawn from a credit account even if this makes the amount of money
// in the credit account become negative.
// The public getMoney method and the protected setMoney method are
// inherited from the superclass Account.
    @Override
    public void withdraw(int amount) {
//        setMoney(getMoney() - amount);
        // Sleep for [0, 10) milliseconds.
        try {
            Thread.sleep(r.nextInt(10));
        } catch (InterruptedException ex) {
        }
// Get the current amount of money from the account.
        int money = getMoney();
// Sleep for [0, 10) milliseconds.
        try {
            Thread.sleep(r.nextInt(10));
        } catch (InterruptedException ex) {
        }
// Change the current amount of money of the account.
        setMoney(money - amount);
    }
}
