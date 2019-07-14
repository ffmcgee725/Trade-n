package org.academiadecodigo.codezillas.trade_n.menu.mainmenu.mainmenustrategy;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.codezillas.trade_n.server.RegisterManager;

public interface MenuBehaviour {

    void doOperation(Prompt prompt, RegisterManager registManager);
}
