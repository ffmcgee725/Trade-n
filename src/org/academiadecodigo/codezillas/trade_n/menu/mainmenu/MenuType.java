package org.academiadecodigo.codezillas.trade_n.menu.mainmenu;

import org.academiadecodigo.codezillas.trade_n.menu.mainmenu.mainmenustrategy.OperationLeave;
import org.academiadecodigo.codezillas.trade_n.menu.mainmenu.mainmenustrategy.OperationLogin;
import org.academiadecodigo.codezillas.trade_n.menu.mainmenu.mainmenustrategy.MenuBehaviour;
import org.academiadecodigo.codezillas.trade_n.menu.mainmenu.mainmenustrategy.OperationRegister;

public enum MenuType {
    Register(new OperationRegister()),
    Login(new OperationLogin()),
    Leave(new OperationLeave());

    MenuBehaviour menuBehaviour;

    MenuType(MenuBehaviour menuBehaviour){
            this.menuBehaviour = menuBehaviour;
    }

}
