package org.academiadecodigo.codezillas.trade_n.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.codezillas.trade_n.currency.CurrencyMenu;
import org.academiadecodigo.codezillas.trade_n.currency.CurrencyType;
import org.academiadecodigo.codezillas.trade_n.currency.ExchangeManager;

import java.util.HashSet;

public class AccountManager {

    private HashSet<Account> accounts;
    private Prompt prompt;
    private ExchangeManager exchangeManager;
    private CurrencyMenu currencyMenu;

    public AccountManager(Prompt prompt) {
        this.prompt = prompt;
        accounts = new HashSet<>();
        exchangeManager = new ExchangeManager();
        currencyMenu = new CurrencyMenu(prompt);
    }

    public CurrencyMenu getCurrencyMenu() {
        return currencyMenu;
    }

    public Account makeAccount(CurrencyType currencyType) {
        Account account = new Account(currencyType);
        accounts.add(account);
        return account;
    }

    public HashSet<Account> getAccounts() {
        return accounts;
    }

    public void deposit(int accountID, int amount) {
        for (Account acc : accounts) {
            if (acc.getId() == accountID) {
                acc.deposit(amount);
            }
        }
    }

    public void transfer(int accountID, int amount) {
        for (Account acc : accounts) {
            if (acc.getId() == accountID) {
                acc.transfer(amount);
            }
        }
    }

    public int getAccountBalance(int accountID) {
        for (Account acc : accounts) {
            if (acc.getId() == accountID) {
                return acc.getBalance();
            }
        }
        return 0;
    }
}
