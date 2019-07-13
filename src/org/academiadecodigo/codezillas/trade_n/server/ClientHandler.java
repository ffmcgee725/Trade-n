package org.academiadecodigo.codezillas.trade_n.server;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.codezillas.trade_n.account.Account;
import org.academiadecodigo.codezillas.trade_n.account.AccountManager;
import org.academiadecodigo.codezillas.trade_n.menu.clientmenu.ClientMenu;
import org.academiadecodigo.codezillas.trade_n.menu.mainmenu.Menu;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;

public class ClientHandler implements Runnable {

    private Socket socket;
    private HashSet<Account> accounts;
    private AccountManager accountManager;
    private Prompt prompt;
    private ClientMenu clientMenu;
    private Menu menu;
    private int clientID;

    public ClientHandler(int clientID, Socket socket) {
        this.clientID = clientID;
        this.socket = socket;
    }

    @Override
    public void run() {
        init();
        menu.start();
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    private void init() {
        prompt = getPrompt();
        this.accountManager = new AccountManager(prompt);
        accounts = accountManager.getAccounts();
        clientMenu = new ClientMenu(this);
        this.menu = new Menu(prompt, clientMenu);
    }

    public Prompt getPrompt() {
        Prompt prompt = null;
        try {
            prompt = new Prompt(socket.getInputStream(), new PrintStream(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prompt;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public HashSet<Account> getAccounts() {
        return accounts;
    }

    public void deposit() {
        accountManager.deposit(this);
    }

    public void transfer(int accountID, int amount) {
        accountManager.transfer(accountID, amount);
    }

    public void getAccountBalance(int accountID) {
        accountManager.getAccountBalance(accountID);
    }

    public void openAccount() {
        accountManager.makeAccount(accountManager.getCurrencyMenu().chooseCurrency());
    }

    public int getClientID() {
        return clientID;
    }

    public Socket getSocket() {
        return socket;
    }

}

