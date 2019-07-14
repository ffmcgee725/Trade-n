package org.academiadecodigo.codezillas.trade_n.server;

import java.net.Socket;

public class Register {

    private String username;
    private String password;
    private ClientHandler clientHandler;

    Register(String username, String password, Socket socket) {
        this.username = username;
        this.password = password;
        clientHandler = new ClientHandler(username, socket);
    }

    public ClientHandler getClientHandler() {
        return clientHandler;
    }

    String getPassword() {
        return password;
    }

}
