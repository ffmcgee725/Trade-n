package org.academiadecodigo.codezillas.trade_n.menu.clientmenu;

import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.codezillas.trade_n.menu.clientmenu.clientmenustrategy.ClientMenuType;
import org.academiadecodigo.codezillas.trade_n.server.ClientHandler;

public class ClientMenu {

    private ClientHandler clientHandler;
    private MenuInputScanner scanner;

    public ClientMenu(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public void start() {
        String[] options = new String[ClientMenuType.values().length + 1];
        for (int i = 0; i < ClientMenuType.values().length; i++) {
            options[i] = ClientMenuType.values()[i].getDescription();
        }
        options[options.length - 1] = "Logout";

        scanner = new MenuInputScanner(options);
        scanner.setMessage("Welcome Client: " + clientHandler.getClientID() + "\n" + "Select your option!");

        while (true) {
            int choice = clientHandler.getPrompt().getUserInput(scanner);

            if (choice == options.length) {
                return;
            }

            ClientMenuType.values()[choice - 1].getClientMenuStrategy().doOperation(clientHandler);
        }
    }

    public ClientHandler getClientHandler() {
        return clientHandler;
    }
}
