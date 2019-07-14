package org.academiadecodigo.codezillas.trade_n.server;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.codezillas.trade_n.account.Account;
import org.academiadecodigo.codezillas.trade_n.account.AccountManager;
import org.academiadecodigo.codezillas.trade_n.menu.clientmenu.ClientMenu;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;

public class ClientHandler {

    private Socket socket;
    private HashSet<Account> accounts;
    private AccountManager accountManager;
    private Prompt prompt;
    private ClientMenu clientMenu;
    private String username;
    private Account defaultAccount;

    public ClientHandler(String username, Socket socket) {
        this.username = username;
        this.socket = socket;
        prompt = getPrompt();
        this.accountManager = new AccountManager(prompt);
        clientMenu = new ClientMenu(this);
    }

    public String getUsername() {
        return username;
    }

    void run() {
        prompt = getPrompt();
        accountManager.setPrompt(prompt);
        accounts = accountManager.getAccounts();
        clientMenu.start();
    }

    AccountManager getAccountManager() {
        return accountManager;
    }

    public Prompt getPrompt() {
        Prompt prompt = null;

        try {
            prompt = new Prompt(socket.getInputStream(), new PrintStream(socket.getOutputStream(), true));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return prompt;
    }

    void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void deposit() {
        accountManager.deposit(getPrintWriter());
    }

    public void transfer() {
        accountManager.exchange(getPrintWriter());
    }

    PrintWriter getPrintWriter() {

        try {
            return new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void setDefaultAccount(Account defaultAccount) {
        this.defaultAccount = defaultAccount;
    }

    public void changeDefaultAccount() {
        defaultAccount = accountManager.selectAccount();
    }

    public void getAccountBalance() {
        accountManager.getAccountBalance(getPrintWriter());
    }

    public void openAccount() {
        accountManager.makeAccount(getPrintWriter(), accountManager.getCurrencyMenu().chooseCurrency(), this);
    }

    Account getDefaultAccount() {
        return defaultAccount;
    }

    public void payment() {
        StartServer.transfer(this);
    }

    boolean hasAccount() {
        return getAccountManager().getAccounts().size() > 0;
    }

}

