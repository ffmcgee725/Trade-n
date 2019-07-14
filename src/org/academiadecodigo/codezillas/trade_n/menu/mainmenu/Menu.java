package org.academiadecodigo.codezillas.trade_n.menu.mainmenu;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.codezillas.trade_n.menu.mainmenu.mainmenustrategy.MenuType;
import org.academiadecodigo.codezillas.trade_n.server.RegisterManager;

public class Menu {

    private Prompt prompt;
    private RegisterManager registManager;
    private MenuInputScanner menuInputScanner;

    public Menu(Prompt prompt, RegisterManager registManager){
        this.prompt = prompt;
        this.registManager = registManager;
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
            MenuType.values()[prompt.getUserInput(menuInputScanner) - 1].menuBehaviour.doOperation(prompt,registManager);
        }
    }
}
