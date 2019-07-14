package org.academiadecodigo.codezillas.trade_n.menu.mainmenu.mainmenustrategy;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.codezillas.trade_n.server.RegisterManager;

class OperationLeave implements MenuBehaviour {
    @Override
    public void doOperation(Prompt prompt, RegisterManager registManager) {
        registManager.leave();
    }
}
