package org.academiadecodigo.codezillas.trade_n.server;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.codezillas.trade_n.currency.ExchangeManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static ConcurrentHashMap<Integer, ClientHandler> clients;
    private ServerSocket serverSocket;
    private ExecutorService service;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.service = Executors.newCachedThreadPool();
        clients = new ConcurrentHashMap<>();
    }

    public void start() {

        while (true) {
            waitConnection();
        }
    }

    private void waitConnection() {
        try {
            Socket clientSocket = serverSocket.accept();

            ClientHandler clientHandler = new ClientHandler(clients.size(), clientSocket);
            service.submit(clientHandler);

        } catch (IOException e) {
            System.err.println("Error establishing connection: " + e.getMessage());
        }
    }

    public static class ClientManager{

        public static boolean logIn(Prompt prompt){
            IntegerInputScanner integerInputScanner = new IntegerInputScanner();
            integerInputScanner.setMessage("Enter your Customer number: ");

            return clients.containsKey(prompt.getUserInput(integerInputScanner));
        }

        public static void register(ClientHandler clientHandler){
            clients.put(clients.size()+1,clientHandler);
            clientHandler.setClientID(clients.size());
        }

        public static String loggedClients() {
            return "Current client: " + (Thread.activeCount()-2)+"\n";
        }
    }

}