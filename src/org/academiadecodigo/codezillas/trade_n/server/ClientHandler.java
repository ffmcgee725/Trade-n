package org.academiadecodigo.codezillas.trade_n.server;

import org.academiadecodigo.codezillas.trade_n.client.Account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;

public class ClientHandler implements Runnable{

    private Socket socket;
    private HashSet<Account> accounts;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket) {
        accounts = new HashSet<>();
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (!socket.isClosed()) {
                listen(in);
            }

        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
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

    public Socket getSocket() {
        return socket;
    }

}
