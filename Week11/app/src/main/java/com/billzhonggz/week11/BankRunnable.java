package com.billzhonggz.week11;

/**
 * Created by ZHONG on 2017/4/24.
 */
public class BankRunnable implements Runnable {
    private Bank m;

    public BankRunnable(Bank m) {
        this.m = m;
    }

    @Override
    public void run() {

        for (int i = 0; i <= 100; i++) {

            try {
                Thread.sleep(100);
                synchronized (m) {
                    m.withdraw("Philippe", 1000);
                    m.withdraw("Philippe", -1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (UnknownCustomerException e) {
                e.printStackTrace();
            } catch (NotEnoughMoneyException e) {
                e.printStackTrace();
            }
        }
    }

}
