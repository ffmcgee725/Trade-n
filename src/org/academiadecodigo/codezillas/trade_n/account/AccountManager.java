package org.academiadecodigo.codezillas.trade_n.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleRangeInputScanner;
import org.academiadecodigo.codezillas.trade_n.currency.CurrencyMenu;
import org.academiadecodigo.codezillas.trade_n.currency.CurrencyType;
import org.academiadecodigo.codezillas.trade_n.currency.ExchangeManager;
import org.academiadecodigo.codezillas.trade_n.server.ClientHandler;

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

    public void makeAccount(PrintWriter printWriter, CurrencyType currencyType,ClientHandler clientHandler) {

        for (Account acc : accounts) {
            if (acc.getCurrencyType() == currencyType) {
                printWriter.println("You already have an account with that type of currency");
                return;
            }
        }

        Account account = new Account(currencyType);
        if (accounts.isEmpty()){
            clientHandler.setDefaultAccount(account);
        }
        accounts.add(account);
    }

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }

    public HashSet<Account> getAccounts() {
        return accounts;
    }

    public void deposit(PrintWriter printWriter) {
        Account account = selectAccount();
        if (account == null) {
            printWriter.println("Deposit failed. No account found.");
        } else {
            DoubleInputScanner valueScanner = new DoubleRangeInputScanner(0, 999999999);
            valueScanner.setMessage("Enter the value to deposit:");
            valueScanner.setError("Please insert a valid amount");
            double value = prompt.getUserInput(valueScanner);
            account.deposit(value);
        }
    }

    public double withdraw(Account account) {
        DoubleInputScanner valueScanner = new DoubleRangeInputScanner(0, account.getBalance());
        valueScanner.setMessage("Enter value to debit: ");
        valueScanner.setError("Your balance is: " + account.getBalance());
        double value = prompt.getUserInput(valueScanner);
        account.withdraw(value);
        return value;
    }

    public void exchange(PrintWriter printWriter) {

        if (accounts.size() < 2){
            printWriter.println("You must have at least 2 accounts");
            return;
        }

        printWriter.println("Select Origin Account");
        Account sourceAccount = selectAccount();
        printWriter.println("Select Destination Account");
        Account destinationAccount = selectAccount();
        printWriter.println("Select the Amount to Debit");

        if (sourceAccount == null || destinationAccount == null){
            printWriter.println("Failed to exchange");
            return;
        }

        double amount = withdraw(sourceAccount);

        if (sourceAccount.getId() == destinationAccount.getId()){
            printWriter.println("Failed");
            sourceAccount.deposit(amount);
            return;
        }
        amount = exchangeManager.getRates(sourceAccount.getCurrencyType(),destinationAccount.getCurrencyType()) * amount;
        destinationAccount.deposit(amount);
    }

    public void getAccountBalance(PrintWriter printWriter) {
        for (Account acc : accounts) {
            printWriter.println(acc);
        }
    }

    public ExchangeManager getExchangeManager() {
        return exchangeManager;
    }

    public Account selectAccount() {
        String[] options = new String[accounts.size() + 1];
        Account[] accountIndex = new Account[accounts.size()];
        if (accountIndex.length == 0) {
            return null;
        }

        for (int i = 0; i < accounts.size(); i++) {
            accountIndex[i] = (Account) accounts.toArray()[i];
            options[i] = accountIndex[i].toString();
        }
        options[options.length - 1] = "Go back";
        MenuInputScanner menu = new MenuInputScanner(options);
        int id = prompt.getUserInput(menu);

        if (id == options.length) {
            return null;
        }

        return accountIndex[id - 1];
    }
}
