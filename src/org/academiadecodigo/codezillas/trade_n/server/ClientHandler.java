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
        this.accountManager = new AccountManager();
        accounts = accountManager.getAccounts();
        this.clientID = clientID;
        this.socket = socket;
        prompt = getPrompt();
        clientMenu = new ClientMenu(this);
        this.menu = new Menu(prompt,clientMenu);
    }

    @Override
    public void run() {
         menu.start();
         clientmenu();
    }

    public void clientmenu(){

    }

    public Prompt getPrompt() {
        Prompt prompt = null;
        try {
            prompt = new Prompt(socket.getInputStream(),new PrintStream(socket.getOutputStream()));
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

    public void deposit(int accountID, int amount) {
        accountManager.deposit(accountID, amount);
    }

    public void transfer(int accountID, int amount) {
        accountManager.transfer(accountID, amount);
    }

    public void getAccountBalance(int accountID) {
        accountManager.getAccountBalance(accountID);
    }

    public int getClientID() {
        return clientID;
    }

    public Socket getSocket() {
        return socket;
    }
}

