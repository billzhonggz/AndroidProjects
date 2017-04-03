package com.billzhonggz.week9;

import java.util.ArrayList;

/**
 * Created by ZHONG on 2017/4/3.
 */
public class Bank {
    private String name;
    private ArrayList<IAccount> accounts;
    private ArrayList<IModelListener> views;

    public Bank(String name) {
        this.name = name;
        accounts = new ArrayList<IAccount>();
        views = new ArrayList<IModelListener>();
    }

    public void addModelListener(IModelListener v) {
        views.add(v);
    }

    private void notifyAllModelListener() {
        for (IModelListener v : views) {
            v.notifyModelListener();
        }
    }

    public void addAccount(IAccount account) {
        accounts.add(account);
        this.notifyAllModelListener();
    }

    public int totalMoney() {
        int total = 0;
        for (IAccount a : accounts) {
            total = total + a.getMoney();
        }
        return total;
    }

    public int getMoney(String name) throws UnknownCustomerException {
        int money = 0;
        for (IAccount a : accounts) {
            if (a.getName().equals(name)) {
                money = a.getMoney();
                break;
            }
        }
        if (money == 0) {
            throw new UnknownCustomerException("Customer " + name + " unknown.");
        } else
            return money;
    }

    public void withdraw(String name, int amount) throws UnknownCustomerException {
        // Find the account.
        IAccount a = null;
        for (IAccount a1 : accounts) {
            if (a1.getName().equals(name)) {
                a = a1;
                break;
            }
        }
        if (a.equals(null)) {
            throw new UnknownCustomerException("Customer " + name + " unknown.");
        } else {
            // Do withdraw.
            try {
                a.withdraw(amount);
                this.notifyAllModelListener();
            } catch (NotEnoughMoneyException e) {
                e.printStackTrace();
            }
        }
    }

    public static void TestBank() {
        Bank b = new Bank("TestBank");
        // Create credit account.
        CreditAccount ca = new CreditAccount("TestCredit", 10);
        b.addAccount(ca);
        // Create student account.
        try {
            StudentAccount sa = new StudentAccount("TestStudent", 10);
            b.addAccount(sa);
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
        }
        // Test total money.
        System.out.println(b.totalMoney() == 20);
        // Test get money.
        try {
            System.out.println(b.getMoney("TestStudent") == 10);
        } catch (UnknownCustomerException e) {
            e.printStackTrace();
        }
        // Test withdraw.
        try {
            b.withdraw("TestCredit", 20);
            System.out.println(b.getMoney("TestCredit") == -10);
        } catch (UnknownCustomerException e) {
            e.printStackTrace();
        }
    }
}
