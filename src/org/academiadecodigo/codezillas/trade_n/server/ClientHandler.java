package org.academiadecodigo.codezillas.trade_n.server;

import org.academiadecodigo.codezillas.trade_n.account.Account;
import org.academiadecodigo.codezillas.trade_n.account.AccountManager;
import org.academiadecodigo.codezillas.trade_n.menu.mainmenu.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;

public class ClientHandler implements Runnable {

    private Socket socket;
    private HashSet<Account> accounts;
    private AccountManager accountManager;
    private Menu menu;

    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket) {
        this.accountManager = new AccountManager();
        accounts = accountManager.getAccounts();
        this.socket = socket;
        this.menu = new Menu(socket);
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (!socket.isClosed()) {
                menu.start();
            }

        } catch (IOException e) {
            System.err.println("Error handling account: " + e.getMessage());
        }
    }

    private void listen(BufferedReader in) throws IOException {

        String message = in.readLine();

        if (message == null) {

            System.out.println("Client closed, exiting");

        }

        System.out.println(message);
        out.println("From server: " + message);
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

    public Socket getSocket() {
        return socket;
    }
}

