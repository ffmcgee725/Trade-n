package org.academiadecodigo.codezillas.trade_n.menu.mainmenu.mainmenustrategy;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.codezillas.trade_n.menu.clientmenu.ClientMenu;

import java.io.IOException;

class OperationLeave implements MenuBehaviour {
    @Override
    public void doOperation(Prompt prompt, ClientMenu clientMenu) {
        try {
            clientMenu.getClientHandler().getSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
