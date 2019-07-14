package org.academiadecodigo.codezillas.trade_n.account;

import org.academiadecodigo.codezillas.trade_n.currency.CurrencyType;

public class Account {

    private static int numberOfAccounts = 0;

    private CurrencyType currencyType;
    private final int id;
    private double balance;

    public Account(CurrencyType currencyType) {
        numberOfAccounts++;
        id = numberOfAccounts;
        this.currencyType = currencyType;
    }

    public double getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + balance + " " + currencyType.toString();
    }
}
