package org.academiadecodigo.codezillas.trade_n.menu.mainmenu;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.codezillas.trade_n.DefaultMessages;
import org.academiadecodigo.codezillas.trade_n.menu.mainmenu.mainmenustrategy.MenuType;
import org.academiadecodigo.codezillas.trade_n.server.RegisterManager;

public class Menu {

    private Prompt prompt;
    private RegisterManager registryManager;

    public Menu(Prompt prompt, RegisterManager registryManager) {
        this.prompt = prompt;
        this.registryManager = registryManager;

    }

    public void start() {

        while (true) {
            String[] options = {
                    "Register",
                    "Login",
                    "Quit"
            };

            MenuInputScanner menuInputScanner = new MenuInputScanner(options);
            menuInputScanner.setMessage(DefaultMessages.WELCOME_2);
            MenuType.values()[prompt.getUserInput(menuInputScanner) - 1].menuBehaviour.doOperation(prompt, registryManager);
        }
    }
}
