package org.academiadecodigo.codezillas.trade_n.menu.mainmenu.mainmenustrategy;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.codezillas.trade_n.server.RegisterManager;

class OperationLogin implements MenuBehaviour{

    @Override
    public void doOperation(Prompt prompt, RegisterManager registryManager) {
        registryManager.logIn();
    }
}
