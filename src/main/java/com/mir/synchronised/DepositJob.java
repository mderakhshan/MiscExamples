package com.mir.synchronised;

/**
 * Created by Mir on 08/09/2016.
 */
public class DepositJob implements Runnable {
    private BankAccount acc;
    private double depositAmount;

    public DepositJob(BankAccount account, double amount) {
        this.acc = account;
        this.depositAmount = amount;
    }

    public void run() {
        try {
            acc.deposit (depositAmount);
            Thread.sleep(100);
        }
        catch (Exception e) {
           // System.out.println ("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
