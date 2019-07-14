package org.academiadecodigo.codezillas.trade_n.server;

import java.net.Socket;

public class Register {

    private String username;
    private String password;
    private ClientHandler clientHandler;

    public Register(String username, String password, Socket socket){
        this.username = username;
        this.password = password;
        clientHandler = new ClientHandler(username,socket);
    }

    public ClientHandler getClientHandler() {
        return clientHandler;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
