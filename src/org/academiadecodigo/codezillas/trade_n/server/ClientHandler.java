package org.academiadecodigo.codezillas.trade_n.server;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.codezillas.trade_n.account.Account;
import org.academiadecodigo.codezillas.trade_n.account.AccountManager;
import org.academiadecodigo.codezillas.trade_n.menu.clientmenu.ClientMenu;
import org.academiadecodigo.codezillas.trade_n.menu.mainmenu.Menu;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;

public class ClientHandler{

    private Socket socket;
    private HashSet<Account> accounts;
    private AccountManager accountManager;
    private Prompt prompt;
    private ClientMenu clientMenu;
    private String username;

    public ClientHandler(String username,Socket socket) {
        this.username = username;
        this.socket = socket;
        prompt = getPrompt();
        this.accountManager = new AccountManager(prompt);
        clientMenu = new ClientMenu(this);
    }

    public String getUsername() {
        return username;
    }

    public void run() {
        prompt = getPrompt();
        accountManager.setPrompt(prompt);
        accounts = accountManager.getAccounts();
        clientMenu.start();
    }

    public AccountManager getAccountManager() {
        return accountManager;
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

    public void setSocket(Socket socket) {
        this.socket = socket;
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

    public Socket getSocket() {
        return socket;
    }

}

