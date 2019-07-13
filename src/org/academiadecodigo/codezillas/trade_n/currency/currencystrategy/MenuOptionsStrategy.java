package org.academiadecodigo.codezillas.trade_n.currency.currencystrategy;

public enum MenuOptionsStrategy {
    BITCOIN("Bitcoin", new BitcoinStrategy()),
    EURO("Euro", new EuroStrategy());

    private String currencyName;
    private CurrencyHandler currencyHandler;

    MenuOptionsStrategy(String currencyName, CurrencyHandler currencyHandler){
        this.currencyName = currencyName;
        this.currencyHandler = currencyHandler;
    }
}
