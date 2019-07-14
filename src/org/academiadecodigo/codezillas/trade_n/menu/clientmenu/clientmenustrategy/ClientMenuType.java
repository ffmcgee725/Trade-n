package org.academiadecodigo.codezillas.trade_n.menu.clientmenu.clientmenustrategy;

public enum ClientMenuType {
    OPEN_ACCOUNT("Open account", new OperationOpenAccount()),
    DEPOSIT("Deposit", new OperationDeposit()),
    EXCHANGE("Exchange", new OperationExchange()),
    SET_DEFAULT("Set Default Account", new OperationSetDefaultAccount()),
    BALANCE("Balance", new OperationGetBalance()),
    TRANSFER("Transfer", new OperationTransfer());

    private String description;
    private ClientMenuStrategy clientMenuStrategy;

    ClientMenuType(String description, ClientMenuStrategy clientMenuStrategy) {
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
