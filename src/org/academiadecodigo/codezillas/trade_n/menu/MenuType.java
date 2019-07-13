package org.academiadecodigo.codezillas.trade_n.menu;

import org.academiadecodigo.codezillas.trade_n.menu.mainmenu.OperationLeave;
import org.academiadecodigo.codezillas.trade_n.menu.mainmenu.OperationLogin;
import org.academiadecodigo.codezillas.trade_n.menu.mainmenu.MenuBehaviour;
import org.academiadecodigo.codezillas.trade_n.menu.mainmenu.OperationRegister;

public enum MenuType {
    Register(new OperationRegister()),
    Login(new OperationLogin()),
    Leave(new OperationLeave());

    MenuBehaviour menuBehaviour;

    MenuType(MenuBehaviour menuBehaviour){
            this.menuBehaviour = menuBehaviour;
    }

}
