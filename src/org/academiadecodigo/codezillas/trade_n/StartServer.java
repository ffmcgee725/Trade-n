package org.academiadecodigo.codezillas.trade_n;

import org.academiadecodigo.codezillas.trade_n.currency.ExchangeManager;
import org.academiadecodigo.codezillas.trade_n.server.Server;

import java.io.IOException;

public class StartServer {

    private static final int DEFAULT_PORT = 8000;

    public static void main(String[] args) {

        try {
            ExchangeManager exchangeManager = new ExchangeManager();
            Server server = new Server (args.length > 0 ? Integer.valueOf(args[0]) :
                    DEFAULT_PORT, exchangeManager);

            server.start();
        } catch (IOException e) {
            System.err.println("Error opening server socket: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error port must be a valid number: " + args[0]);
        }
    }
}
