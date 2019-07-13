package org.academiadecodigo.codezillas.trade_n.menu.clientmenu.clientmenustrategy;

public enum ClientMenuType {
    OPENACCOUNT("Open account", new OperationOpenAccount()),
    DEPOSIT("Deposit", new OperationDeposit()),
    PAY("Pay", new OperationPay());

    private String description;
    private ClientMenuStrategy clientMenuStrategy;

    ClientMenuType(String description, ClientMenuStrategy clientMenuStrategy){
        this.description = description;
        this.clientMenuStrategy = clientMenuStrategy;
    }

    public String getDescription() {
        return description;
    }

    public ClientMenuStrategy getClientMenuStrategy() {
        return clientMenuStrategy;
    }
}
