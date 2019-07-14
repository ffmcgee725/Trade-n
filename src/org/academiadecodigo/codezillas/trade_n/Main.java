package org.academiadecodigo.codezillas.trade_n;

import org.academiadecodigo.codezillas.trade_n.server.StartServer;

import java.io.IOException;

public class Main {

    private static final int DEFAULT_PORT = 8000;

    public static void main(String[] args) {

        try {

            StartServer startServer = new StartServer(args.length > 0 ? Integer.valueOf(args[0]) : DEFAULT_PORT);
            startServer.start();

        } catch (IOException e) {
            System.err.println("Error opening server socket: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error port must be a valid number: " + args[0]);
        }
    }
}
