package org.academiadecodigo.codezillas.trade_n.client;

import org.academiadecodigo.codezillas.trade_n.currency.CurrencyType;

public class Account {

    private static int numberOfAccounts = 0;

    private CurrencyType currencyType;
    private final int id;
    private int balance;

    public Account(CurrencyType currencyType) {
        numberOfAccounts++;
        id = numberOfAccounts;
        this.currencyType = currencyType;
    }

    public int getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void transfer(int amount) {
        balance -= amount;
    }


}
