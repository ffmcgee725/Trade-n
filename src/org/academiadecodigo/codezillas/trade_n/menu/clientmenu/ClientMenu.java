package org.academiadecodigo.codezillas.trade_n.menu.clientmenu;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.codezillas.trade_n.menu.clientmenu.clientmenustrategy.ClientMenuType;
import org.academiadecodigo.codezillas.trade_n.server.ClientHandler;

import java.net.Socket;

public class ClientMenu {

    private ClientHandler clientHandler;
    private MenuInputScanner scanner;

    public ClientMenu(ClientHandler clientHandler){
        this.clientHandler = clientHandler;
    }

    public void start(){

        String[] options ={"OpenAccount","Pay","Select Account","Go Back"};
        scanner = new MenuInputScanner(options);
        scanner.setMessage("Welcome Client: "+clientHandler.getClientID()+"\nSelect your option!");

        while (true){
            int choice = clientHandler.getPrompt().getUserInput(scanner)-1;

            if (choice == 3){
                return;
            }

            ClientMenuType.values()[choice].clientMenuStrategy.doOperation();
        }
    }
    public ClientHandler getClientHandler() {
        return clientHandler;
    }
}
