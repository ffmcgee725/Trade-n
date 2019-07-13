package org.academiadecodigo.codezillas.trade_n.server;

import org.academiadecodigo.codezillas.trade_n.currency.ExchangeManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ServerSocket serverSocket;
    private ExecutorService service;
    private HashMap<Integer, ClientHandler> clients;
    private ExchangeManager exchangeManager;

    public Server(int port, ExchangeManager manager) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.exchangeManager = manager;
        this.service = Executors.newCachedThreadPool();
    }

    public void start() {

        int connections = 0;

        while (true) {
            waitConnection(connections);
            connections++;
        }
    }

    private void waitConnection(int connections) {
        try {
            Socket clientSocket = serverSocket.accept();

            ClientHandler clientHandler = new ClientHandler(clientSocket);
            service.submit(clientHandler);
            clients.put(clients.size(), clientHandler);

        } catch (IOException e) {
            System.err.println("Error establishing connection: " + e.getMessage());
        }
    }

    public String loggedClients() {
        StringBuilder list = new StringBuilder("Connected Clients:\n");

        synchronized (clients) {
            for(Map.Entry<Integer, ClientHandler> entry : clients.entrySet()) {

            }
        }
        return list.toString();
    }




}
