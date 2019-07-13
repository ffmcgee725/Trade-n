package org.academiadecodigo.codezillas.trade_n.menu.mainmenu;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.codezillas.trade_n.menu.mainmenu.mainmenustrategy.MenuType;

public class Menu {

    private Prompt prompt;
    private MenuInputScanner menuInputScanner;

    public Menu(){
        prompt = new Prompt(System.in,System.out);
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
