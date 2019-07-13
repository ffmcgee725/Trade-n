package org.academiadecodigo.codezillas.trade_n.currency;

import org.academiadecodigo.codezillas.trade_n.account.AccountManager;
import org.academiadecodigo.codezillas.trade_n.currency.currencystrategy.BitcoinStrategy;
import org.academiadecodigo.codezillas.trade_n.currency.currencystrategy.CurrencyHandler;
import org.academiadecodigo.codezillas.trade_n.currency.currencystrategy.CurrencyPromptMenu;
import org.academiadecodigo.codezillas.trade_n.currency.currencystrategy.EuroStrategy;
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

        String[] options = new String[CurrencyType.values().length];
        for (int i = 0; i < CurrencyType.values().length; i++) {
            options[i] = CurrencyType.values()[i].getCurrencyName();
        }

        int customerId = CurrencyPromptMenu.userOptionInput("Please choose currency: ", options);

        while ((selectedOption = CurrencyPromptMenu.userOptionInput()) != 5) {
            CurrencyType.values()[selectedOption - 1].getCurrencyHandler().execute(clientHandler);
        }
    }
}
