package org.academiadecodigo.codezillas.trade_n.currency;

import org.academiadecodigo.codezillas.trade_n.currency.currencystrategy.BitcoinStrategy;
import org.academiadecodigo.codezillas.trade_n.currency.currencystrategy.CurrencyHandler;
import org.academiadecodigo.codezillas.trade_n.currency.currencystrategy.EuroStrategy;

public enum CurrencyType {
    BITCOIN("BTC", new BitcoinStrategy()),
    EURO("EUR", new EuroStrategy());

    private String code;
    private CurrencyHandler currencyHandler;

    CurrencyType(String code, CurrencyHandler currencyHandler){
        this.code = code;
        this.currencyHandler = currencyHandler;
    }

    public String getCode(){
        return this.code;
    }
}
