package org.academiadecodigo.codezillas.trade_n.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.codezillas.trade_n.currency.CurrencyMenu;
import org.academiadecodigo.codezillas.trade_n.currency.CurrencyType;
import org.academiadecodigo.codezillas.trade_n.currency.ExchangeManager;
import org.academiadecodigo.codezillas.trade_n.server.ClientHandler;

import java.io.IOException;
import java.io.PrintWriter;
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

    public void deposit(ClientHandler clientHandler) {
        Account account = selectAccount();
        if (account == null) {
            sendMessage(clientHandler,"Deposit failed. No account found.");
        } else {
            DoubleInputScanner valueScanner = new DoubleInputScanner();
            valueScanner.setMessage("Enter the value to deposit:");
            double value = prompt.getUserInput(valueScanner);
            account.deposit(value);
        }
    }

    private void sendMessage(ClientHandler clientHandler,String message){
        try {
            PrintWriter printWriter = new PrintWriter(clientHandler.getSocket().getOutputStream(), true);
            printWriter.println(message);
        } catch (IOException e) {
            e.printStackTrace();
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

    public Account selectAccount() {
        String[] options = new String[accounts.size() + 1];
        Account[] accountIndex = new Account[accounts.size()];
        if (accountIndex.length==0){
            return null;
        }

        for (int i = 0; i < accounts.size() ; i++) {
            accountIndex[i] = (Account) accounts.toArray()[i];
            options[i] = accountIndex[i].toString();
        }
        options[options.length - 1] = "Go back";
        MenuInputScanner menu = new MenuInputScanner(options);
        int id = prompt.getUserInput(menu);
        return accountIndex[id-1];
    }
}
