package org.academiadecodigo.codezillas.trade_n.currency;

import org.academiadecodigo.codezillas.trade_n.currency.currencystrategy.BitcoinStrategy;
import org.academiadecodigo.codezillas.trade_n.currency.currencystrategy.CurrencyHandler;
import org.academiadecodigo.codezillas.trade_n.currency.currencystrategy.EuroStrategy;

public enum CurrencyType {
    BITCOIN("Bitcoin", "BTC", new BitcoinStrategy()),
    EURO("Euro", "EUR", new EuroStrategy());

    private String currencyName;
    private String code;
    private CurrencyHandler currencyHandler;

    CurrencyType(String currencyName, String code, CurrencyHandler currencyHandler){
        this.currencyName = currencyName;
        this.code = code;
        this.currencyHandler = currencyHandler;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public CurrencyHandler getCurrencyHandler() {
        return currencyHandler;
    }

    public String getCode(){
        return this.code;
    }
}
