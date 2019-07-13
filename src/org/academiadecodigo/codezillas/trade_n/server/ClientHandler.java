package org.academiadecodigo.codezillas.trade_n.server;

import org.academiadecodigo.codezillas.trade_n.client.Account;
import org.academiadecodigo.codezillas.trade_n.client.AccountManager;

import java.net.Socket;
import java.util.HashSet;

public class ClientHandler {

    private Socket socket;
    private HashSet<Account> accounts;
    private AccountManager accountManager;

    public ClientHandler(AccountManager accountManager) {
        this.accountManager = accountManager;
        accounts = accountManager.getAccounts();
    }

    public HashSet<Account> getAccounts() {
        return accounts;
    }

    public void deposit(int accountID, int amount) {
        accountManager.deposit(accountID, amount);
    }

    public void transfer(int accountID, int amount) {
        accountManager.transfer(accountID, amount);
    }

    public void getAccountBalance(int accountID) {
        accountManager.getAccountBalance(accountID);
    }
}
