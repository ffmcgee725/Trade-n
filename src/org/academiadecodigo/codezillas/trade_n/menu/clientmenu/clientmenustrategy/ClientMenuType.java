package org.academiadecodigo.codezillas.trade_n.menu.clientmenu.clientmenustrategy;

public enum ClientMenuType {
    OPENACCOUNT(new OperationOpenAccount()),
    SELECTACCOUNT(new OperationSelectAccount()),
    PAY(new OperationPay());

    public ClientMenuStrategy clientMenuStrategy;

    ClientMenuType(ClientMenuStrategy clientMenuStrategy){
        this.clientMenuStrategy = clientMenuStrategy;
    }
}
