package com.mir.synchronised;

/**
 * Created by Mir on 08/09/2016.
 */
public class WithdrwalJob implements Runnable {
    private BankAccount acc;
    private double withdrawalAmount;

    public WithdrwalJob() {
        this.acc = new BankAccount(0.0);
    }

    public WithdrwalJob(BankAccount account, double amount) {
        this.acc = account;
        this.withdrawalAmount = amount;
    }

    public void run() {
        try {
            acc.withdraw (withdrawalAmount);
            Thread.sleep(100);
        }
        catch (Exception e) {
//            System.out.println ("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
