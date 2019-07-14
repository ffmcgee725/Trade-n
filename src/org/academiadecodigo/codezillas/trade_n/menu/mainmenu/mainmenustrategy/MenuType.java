package org.academiadecodigo.codezillas.trade_n.menu.mainmenu.mainmenustrategy;

public enum MenuType {

    Register(new OperationRegister()),
    Login(new OperationLogin()),
    Leave(new OperationLeave());

    public MenuBehaviour menuBehaviour;

    MenuType(MenuBehaviour menuBehaviour){
            this.menuBehaviour = menuBehaviour;
    }
}
