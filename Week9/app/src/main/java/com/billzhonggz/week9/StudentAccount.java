package com.billzhonggz.week9;

/**
 * Created by ZHONG on 2017/4/3.
 */
public class StudentAccount extends Account{
    public StudentAccount(String name, int money) throws NotEnoughMoneyException {
        super(name, money);
        if (money < 0)
            throw new NotEnoughMoneyException("Cannot create student account with negative amount of money.");
    }

    public void withdraw (int amount) throws NotEnoughMoneyException {
        // Get the current balance.
        int bal = this.getMoney();
        // Do the test.
        if (bal - amount < 0) {
            throw new NotEnoughMoneyException("Cannot withdraw " + amount + " from account, only " + bal + " is available.");
        }
        else {
            // Do the subtraction.
            this.setMoney(bal - amount);
        }
    }

    public static void TestStudentAccount () {
        try {
            StudentAccount sa = new StudentAccount("TestStudent", -10);
        } catch (NotEnoughMoneyException notEnoughMoneyException) {
            System.out.println(notEnoughMoneyException.getMessage().equals("Cannot create student account with negative amount of money."));
        }

        try {
            StudentAccount sa2 = new StudentAccount("TestStudent",10);
            System.out.println(sa2.getName().equals("TestStudent"));
            System.out.println(sa2.getMoney() == 10);
            sa2.withdraw(5);
            System.out.println(sa2.getMoney() == 5);
        } catch (NotEnoughMoneyException notEnoughMoneyException) {
            notEnoughMoneyException.printStackTrace();
        }

        try {
            StudentAccount sa3 = new StudentAccount("TestStudent",10);
            System.out.println(sa3.getName().equals("TestStudent"));
            System.out.println(sa3.getMoney() == 10);
            sa3.withdraw(15);
        } catch (NotEnoughMoneyException notEnoughMoneyException) {
            System.out.println(notEnoughMoneyException.getMessage().equals("Cannot withdraw 15 from account, only 10 is available."));
        }
    }
}
