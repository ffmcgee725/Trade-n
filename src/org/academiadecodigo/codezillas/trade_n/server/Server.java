package org.academiadecodigo.codezillas.trade_n.server;

import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.codezillas.trade_n.account.Account;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ServerSocket serverSocket;
    private ExecutorService service;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.service = Executors.newCachedThreadPool();
    }

    public void start() {

        RegisterManager.setRegisters(new ConcurrentHashMap<>());
        while (true) {
            waitConnection();
        }
    }

    private void waitConnection() {
        try {
            Socket clientSocket = serverSocket.accept();

            service.submit(new RegisterManager(clientSocket));

        } catch (IOException e) {
            System.err.println("Error establishing connection: " + e.getMessage());
        }
    }

    public static void transfer(ClientHandler clientHandler){
        Account account = clientHandler.getAccountManager().selectAccount();
       double amount =  clientHandler.getAccountManager().withdraw(account);
       StringInputScanner stringInputScanner = new StringInputScanner();
       stringInputScanner.setMessage("Insert username: ");
       String username = clientHandler.getPrompt().getUserInput(stringInputScanner);

       if (!RegisterManager.getRegisters().containsKey(username)){
           clientHandler.getPrintWriter().println("Failed to transfer!");
           account.deposit(amount);
           return;
       }

       amount = clientHandler.getAccountManager().getExchangeManager().getRates(
               account.getCurrencyType(),
               RegisterManager.getRegisters().get(username).getClientHandler().getDefaultAccount().getCurrencyType()) * amount;

       RegisterManager.getRegisters().get(username).getClientHandler().getDefaultAccount().deposit(amount);

    }
}