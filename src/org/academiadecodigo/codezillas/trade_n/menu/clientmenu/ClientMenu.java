package org.academiadecodigo.codezillas.trade_n.menu.clientmenu;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class ClientMenu {

    private Prompt prompt;
    private MenuInputScanner scanner;
    private int clientID;


    public ClientMenu(int clientID){
        this.clientID = clientID;
        prompt = new Prompt(System.in,System.out);
    }

    public void start(){

        String[] options ={"OpenAccount","Pay","Select Account","Go Back"};
        scanner = new MenuInputScanner(options);
        scanner.setMessage("Welcome Client: "+clientID+"\nSelect your option!");

        while (true){
            int choice = prompt.getUserInput(scanner)-1;

            if (choice == 3){
                return;
            }

            ClientMenuType.values()[choice].clientMenuStrategy.doOperation();
        }
    }
}
