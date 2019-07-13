package org.academiadecodigo.codezillas.trade_n.menu.mainmenu.mainmenustrategy;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.codezillas.trade_n.menu.clientmenu.ClientMenu;
import org.academiadecodigo.codezillas.trade_n.server.Server;

class OperationLogin implements MenuBehaviour{

    @Override
    public void doOperation(Prompt prompt, ClientMenu clientMenu) {

       if (Server.ClientManager.logIn(prompt)){
           clientMenu.start();
       }
    }
}
