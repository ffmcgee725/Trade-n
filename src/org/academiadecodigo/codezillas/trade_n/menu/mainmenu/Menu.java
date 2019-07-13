package org.academiadecodigo.codezillas.trade_n.menu.mainmenu;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.codezillas.trade_n.menu.clientmenu.ClientMenu;
import org.academiadecodigo.codezillas.trade_n.menu.mainmenu.mainmenustrategy.MenuType;
import org.academiadecodigo.codezillas.trade_n.server.Server;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Menu {

    private Prompt prompt;
    private ClientMenu clientMenu;
    private MenuInputScanner menuInputScanner;

    public Menu(Prompt prompt, ClientMenu clientMenu){
        this.prompt = prompt;
        this.clientMenu = clientMenu;
    }

    public void start(){

        while (true) {
            String[] options = {
                    "Register",
                    "Login",
                    "Quit"
            };

            menuInputScanner = new MenuInputScanner(options);
            menuInputScanner.setMessage(Server.ClientManager.loggedClients() + "Welcome to Trade-N Server!\n\nSelect your option:");
            MenuType.values()[prompt.getUserInput(menuInputScanner) - 1].menuBehaviour.doOperation(prompt,clientMenu);
        }
    }
}
