package org.academiadecodigo.codezillas.trade_n.currency.currencystrategy;

import org.academiadecodigo.codezillas.trade_n.account.AccountManager;
import org.academiadecodigo.codezillas.trade_n.server.ClientHandler;

public class CurrencyMenu {

    private AccountManager accountManager;
    private ClientHandler clientHandler;
    private CurrencyHandler currencyHandler;

    public CurrencyMenu(AccountManager accountManager, ClientHandler clientHandler) {
        this.accountManager = accountManager;
        this.clientHandler = clientHandler;

    }

    public void init() {

        int selectedOption;
        double value;
        String[] options = {"Bitcoin", "Euro"};
        int customerId = CurrencyPromptMenu.userOptionInput("Please choose currency: ", options);

        while ((selectedOption = CurrencyPromptMenu.userOptionInput()) != 5) {

            MenuOptionsStrategy.values()[selectedOption - 1].currencyHandler.execute(clientHandler);

        }
    }

    public enum MenuOptionsStrategy {
        BITCOIN(new BitcoinStrategy()),
        EURO(new EuroStrategy());

        private CurrencyHandler currencyHandler;

        MenuOptionsStrategy(CurrencyHandler currencyHandler) {

            this.currencyHandler = currencyHandler;
        }
    }
}
