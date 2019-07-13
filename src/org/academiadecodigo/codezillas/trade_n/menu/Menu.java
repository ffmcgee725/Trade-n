package org.academiadecodigo.codezillas.trade_n.menu;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class Menu {

    private Prompt prompt;
    private MenuInputScanner menuInputScanner;

    public Menu(){
        prompt = new Prompt(System.in,System.out);
    }

    public void start(){
        String[] options = {
                "Register",
                "Login",
                "Leave"
        };

        menuInputScanner = new MenuInputScanner(options);
        menuInputScanner.setMessage("Welcome to Trade-N Server!\n\nSelect your option:");
        MenuType.values()[prompt.getUserInput(menuInputScanner)-1].menuBehaviour.doOperation();
    }
}
