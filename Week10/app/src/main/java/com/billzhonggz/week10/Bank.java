package com.billzhonggz.week10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by ZHONG on 2017/4/10.
 */
public class Bank extends SQLiteOpenHelper {
    private String name;
    //private ArrayList<IAccount> accounts;
    private ArrayList<IModelListener> listeners;

    public Bank(String name, Context context) {
        // Create database if not exist.
        super(context, "bank.db", null, 1);
        // When a bank is created, it has an empty arraylist of listeners.
        //this.accounts = new ArrayList<IAccount>();
        this.listeners = new ArrayList<IModelListener>();
        this.name = name;
        // When a bank is created, it has an arraylist of accounts but the
        // arraylist is empty (the arraylist does not contain any bank account).
        // this.accounts = new ArrayList<IAccount>();
        // Check number of rows.
        SQLiteDatabase rdb = this.getReadableDatabase();
        int rowNum = (int) DatabaseUtils.queryNumEntries(rdb, "accounts");
        if (rowNum == 0) {
            try {
                this.addAccount("Philippe", 1000, 0);
                this.addAccount("Meunier", 1500, 1);
            } catch (NotEnoughMoneyException ex) {
                // This should never happen!
                // Toast.makeText("This should never happen!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE accounts(_id INTEGER PRIMARY KEY, name TEXT, amount INTEGER, type INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Nothing.
    }

//    public static void TestBank() {
//        Bank b = new Bank("UIC Bank");
//        System.out.println(b.totalMoney() == 0);
//        b.addAccount(new CreditAccount("Philippe", 1000));
//        try {
//            System.out.println(b.getMoney("Philippe") == 1000);
//            System.out.println(b.totalMoney() == 1000);
//            b.addAccount(new StudentAccount("Meunier", 1500));
//            System.out.println(b.getMoney("Philippe") == 1000);
//            System.out.println(b.getMoney("Meunier") == 1500);
//            System.out.println(b.totalMoney() == 2500);
//            b.getMoney("Ms. Park");
//        } catch (UnknownCustomerException ex) {
//            System.out.println(ex.getMessage().equals("Customer Ms. Park unknown"));
//        } catch (NotEnoughMoneyException ex) {
//            // This should never happen!
//            System.out.println(false);
//        }
//        try {
//            b.withdraw("Philippe", 500);
//            System.out.println(b.getMoney("Philippe") == 500);
//            System.out.println(b.getMoney("Meunier") == 1500);
//            b.withdraw("Ms. Park", 1);
//        } catch (UnknownCustomerException ex) {
//            System.out.println(ex.getMessage().equals("Customer Ms. Park unknown"));
//        } catch (NotEnoughMoneyException ex) {
//            // This should never happen!
//            System.out.println(false);
//        }
//        try {
//            b.withdraw("Meunier", 2000);
//        } catch (NotEnoughMoneyException ex) {
//            System.out.println(ex.getMessage().equals("Cannot withdraw 2000 from account, only 1500 is available"));
//        } catch (UnknownCustomerException ex) {
//            // This should never happen!
//            System.out.println(false);
//        }
//        try {
//            System.out.println(b.getMoney("Philippe") == 500);
//            System.out.println(b.getMoney("Meunier") == 1500);
//        } catch (UnknownCustomerException ex) {
//            // This should never happen!
//            System.out.println(false);
//        }
//    }

    // The addListener method takes a listener as argument and adds the
    // listener to the arraylist of listeners for the bank.
    public void addListener(IModelListener listener) {
        listeners.add(listener);
    }

    // Notify all listeners that the bank data has changed. Once they
    // have been notified, each listener will then use the appropriate
    // methods of the bank to get all the new values that the listener
    // needs to display.
    private void notifyAllModelListeners() {
        for (IModelListener listener : listeners) {
            listener.notifyModelListener();
        }
    }

    // The addAccount method takes an account as argument and adds the
    // account to the arraylist of accounts for the bank.
    public void addAccount(String name, int amount, int type) throws NotEnoughMoneyException {
        // accounts.add(account);
        if (type == 1 && amount < 0)
            throw new NotEnoughMoneyException("Cannot create a student account with a negative amount!");
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("amount", amount);
        values.put("type", type);
        // Open database.
        SQLiteDatabase wdb = this.getWritableDatabase();
        wdb.insert("accounts", null, values);
        wdb.close();
        // Notify all the listeners that a change has occurred with the bank’s data.
        notifyAllModelListeners();
    }

    // The totalMoney method returns as result the total amount of money in
    // all the bank accounts of the bank.
    public int totalMoney() {
        int total = 0;
        // Open database.
        SQLiteDatabase rdb = this.getReadableDatabase();
        Cursor cursor = rdb.rawQuery("SELECT SUM(amount) FROM accounts", null);
        cursor.moveToFirst();
        total = cursor.getInt(0);
        cursor.close();
        rdb.close();
        // We loop over all the accounts in the arraylist and add the amount of
        // money in each account to the total.
//        for (IAccount a : accounts) {
//            total += a.getMoney();
//        }
        return total;
    }

    // The getMoney method takes as argument the name of a customer and
    // returns as result the amount of money currently stored in the bank
    // account that belongs to that customer. If the customer does not have
    // a bank account in the bank then the getMoney method should throw an
    // UnknownCustomerException with the message "Customer XXX unknown", where
    // XXX is replaced with the name of the customer.
    public int getMoney(String name) throws UnknownCustomerException {
        // We loop over all the accounts in the arraylist, looking for the
        // account with the correct customer name.
//        for (IAccount a : accounts) {
//            if (a.getName().equals(name)) {
//                // We have found the account with the correct customer name.
//                // So we return as result the amount of money in that account.
//                return a.getMoney();
//            }
//        }
        // Open database
        SQLiteDatabase rdb = this.getReadableDatabase();
        Cursor cursor = null;
        int money = 0;
        try {
            cursor = rdb.rawQuery("SELECT amount FROM accounts WHERE name = \"" + name + "\"", null);
            cursor.moveToFirst();
            money = cursor.getInt(0);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnknownCustomerException("Customer " + name + " unknown");
        }
        cursor.close();
        rdb.close();
        return money;
        // If we reach this point in the code, then it means we have looped
        // over all the accounts in the arraylist without finding an account
        // with the correct customer name. Therefore this customer does not
        // have an account in the bank and we throw an exception.

    }

    // The withdraw method takes as argument the name of a customer and an
    // amount of money and withdraws that amount of money from the amount of
    // money currently stored in the bank account that belongs to that
    // customer. If the customer does not have a bank account in the bank
    // then the withdraw method should throw an UnknownCustomerException with
    // the message "Customer XXX unknown", where XXX is replaced with the
    // name of the customer.
    public void withdraw(String name, int amount) throws UnknownCustomerException, NotEnoughMoneyException {
        // We loop over all the accounts in the arraylist, looking for the
        // account with the correct customer name.
//        for (IAccount a : accounts) {
//            if (a.getName().equals(name)) {
//                // We have found the account with the correct customer name.
//                // So we withdraw the amount of money from that account, and
//                // end the function by returning nothing.
//                a.withdraw(amount);
//                // Notify all the listeners that a change has occurred with the bank’s data.
//                notifyAllModelListeners();
//                return;
//            }
        // Find out the account.
        SQLiteDatabase wdb = this.getWritableDatabase();
        Cursor cursor = null;
        int type, balance;
        // Check type.
        try {
            cursor = wdb.rawQuery("SELECT type FROM accounts WHERE name = \"" + name + "\"", null);
            cursor.moveToFirst();
            type = cursor.getInt(0);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnknownCustomerException("Customer " + name + " unknown");
        }
        // Get balance
        try {
            cursor = wdb.rawQuery("SELECT amount FROM accounts WHERE name = \"" + name + "\"", null);
            cursor.moveToFirst();
            balance = cursor.getInt(0);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnknownCustomerException("Customer " + name + " unknown");
        }
        if (type == 1) {
            // Check balance.
            if (balance < amount)
                throw new NotEnoughMoneyException("Not enough money! You can only withdraw " + balance + "!");
        } else {
            // Do withdraw.
            balance = balance - amount;
            // Write back to db.
            ContentValues values = new ContentValues();
            values.put("amount", balance);
            wdb.update("accounts", values, "name = \"" + name + "\"", null);
            cursor.close();
            wdb.close();
            // If we reach this point in the code, then it means we have looped
            // over all the accounts in the arraylist without finding an account
            // with the correct customer name. Therefore this customer does not
            // have an account in the bank and we throw an exception.

        }
    }
}

