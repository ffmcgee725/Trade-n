package org.academiadecodigo.codezillas.trade_n.menu.mainmenu.mainmenustrategy;

import org.academiadecodigo.codezillas.trade_n.menu.clientmenu.ClientMenu;

class OperationLogin implements MenuBehaviour{

    @Override
    public void doOperation() {


        ClientMenu clientMenu = new ClientMenu(0);
        clientMenu.start();
    }
}
