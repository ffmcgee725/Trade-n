package org.academiadecodigo.codezillas.trade_n.menu.clientmenu;

import org.academiadecodigo.codezillas.trade_n.menu.clientmenu.clientmenustrategy.ClientMenuStrategy;
import org.academiadecodigo.codezillas.trade_n.menu.clientmenu.clientmenustrategy.OperationOpenAccount;
import org.academiadecodigo.codezillas.trade_n.menu.clientmenu.clientmenustrategy.OperationPay;
import org.academiadecodigo.codezillas.trade_n.menu.clientmenu.clientmenustrategy.OperationSelectAccount;

public enum ClientMenuType {
    OPENACCOUNT(new OperationOpenAccount()),
    SELECTACCOUNT(new OperationSelectAccount()),
    PAY(new OperationPay());

    ClientMenuStrategy clientMenuStrategy;

    ClientMenuType(ClientMenuStrategy clientMenuStrategy){
        this.clientMenuStrategy = clientMenuStrategy;
    }
}
