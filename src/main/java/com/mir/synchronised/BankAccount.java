package com.mir.synchronised;

/**
 * Created by Mir on 08/09/2016.
 */
public class BankAccount {

    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public synchronized void deposit(double depositAmount) {
        System.out.print ("Depositing " + depositAmount + " to Banalce " + balance + ": ");
        balance += depositAmount;
        System.out.println ("New balance after deposting is :" + balance);
        notifyAll();
    }

    public synchronized void withdraw (double withdrawAmount) {
        try {
            while (balance < withdrawAmount) {
                System.out.println(".............waiting for account to be in credit...");
                wait();
            }
            System.out.print("Withdrawing " + withdrawAmount + " from Banalce " + balance + ": ");
            balance -= withdrawAmount;
            System.out.println("New balance after withdrawal is :" + balance);
        }
        catch (InterruptedException e) {
        }
    }

    public double getBalance() {
        return balance;
    }
}
