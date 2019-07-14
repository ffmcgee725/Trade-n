package org.academiadecodigo.codezillas.trade_n.menu.clientmenu.clientmenustrategy;

import org.academiadecodigo.codezillas.trade_n.server.ClientHandler;

class OperationExchange implements ClientMenuStrategy {

    @Override
    public void doOperation(ClientHandler clientHandler) {
        clientHandler.transfer();
    }
}
