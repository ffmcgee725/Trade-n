package org.academiadecodigo.codezillas.trade_n.server;

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
}