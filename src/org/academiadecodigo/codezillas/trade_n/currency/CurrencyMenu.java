package org.academiadecodigo.codezillas.trade_n.currency;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class CurrencyMenu {

    private Prompt prompt;

    public CurrencyMenu(Prompt prompt) {
        this.prompt = prompt;
    }

    public CurrencyType chooseCurrency() {
        String[] options = new String[CurrencyType.values().length];
        for (int i = 0; i < CurrencyType.values().length; i++) {
            options[i] = CurrencyType.values()[i].getCurrencyName();
        }

        MenuInputScanner menu = new MenuInputScanner(options);
        menu.setMessage("Please choose currency: ");

        int currency = prompt.getUserInput(menu);
        return CurrencyType.values()[currency - 1];
    }
}
