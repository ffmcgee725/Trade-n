package org.academiadecodigo.codezillas.trade_n.server;

import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.codezillas.trade_n.DefaultMessages;
import org.academiadecodigo.codezillas.trade_n.account.Account;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartServer {

    private ServerSocket serverSocket;
    private ExecutorService threadPool;

    public StartServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.threadPool = Executors.newCachedThreadPool();
    }

    public void start() {
        RegisterManager.setRegistries(new ConcurrentHashMap<>());
        while (true) {
            waitConnection();
        }
    }

    private void waitConnection() {
        try {
            Socket clientSocket = serverSocket.accept();
            threadPool.submit(new RegisterManager(clientSocket));

        } catch (IOException e) {
            System.err.println("Error establishing connection: " + e.getMessage());
        }
    }

    static void transfer(ClientHandler clientHandler) {

        if (!clientHandler.hasAccount()) {
            clientHandler.getPrintWriter().println(DefaultMessages.NO_ACCOUNTS_ERROR);
            return;
        }

        synchronized (clientHandler) {

            Account account = clientHandler.getAccountManager().selectAccount();

            if (account == null) {
                return;
            }

            double amount = clientHandler.getAccountManager().withdraw(account);
            StringInputScanner stringInputScanner = new StringInputScanner();
            stringInputScanner.setMessage(DefaultMessages.INSERT_USERNAME);
            String username = clientHandler.getPrompt().getUserInput(stringInputScanner);

            if (!RegisterManager.getRegistries().containsKey(username)
                    || (RegisterManager.getRegistries().get(username).getClientHandler().getAccountManager().getAccounts().size() < 1)) {

                clientHandler.getPrintWriter().println(DefaultMessages.EXCHANGE_ERROR);
                account.deposit(amount);
                return;
            }

            amount = clientHandler.getAccountManager().getExchangeManager()
                    .getRates(account.getCurrencyType(),
                    RegisterManager.getRegistries().get(username).getClientHandler()
                            .getDefaultAccount().getCurrencyType()) * amount;

            RegisterManager.getRegistries().get(username).getClientHandler().getDefaultAccount().deposit(amount);
        }
    }
}
