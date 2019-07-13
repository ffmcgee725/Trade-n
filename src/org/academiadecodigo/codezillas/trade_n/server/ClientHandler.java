package org.academiadecodigo.codezillas.trade_n.server;

import org.academiadecodigo.codezillas.trade_n.client.Account;

import java.net.Socket;
import java.util.HashSet;

public class ClientHandler {

    private Socket socket;
    private HashSet<Account> accounts;

    public ClientHandler() {
        accounts = new HashSet<>();
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
}
