package org.academiadecodigo.codezillas.trade_n.menu.mainmenu;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.codezillas.trade_n.menu.mainmenu.mainmenustrategy.MenuType;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Menu {

    private Prompt prompt;
    private MenuInputScanner menuInputScanner;

    public Menu(Socket socket){
      init(socket);
    }

    public void init(Socket socket){
        try {
            prompt = new Prompt(socket.getInputStream(), new PrintStream(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){

        while (true) {
            String[] options = {
                    "Register",
                    "Login",
                    "Quit"
            };

            menuInputScanner = new MenuInputScanner(options);
            menuInputScanner.setMessage("Welcome to Trade-N Server!\n\nSelect your option:");
            MenuType.values()[prompt.getUserInput(menuInputScanner) - 1].menuBehaviour.doOperation();
        }
    }
}
