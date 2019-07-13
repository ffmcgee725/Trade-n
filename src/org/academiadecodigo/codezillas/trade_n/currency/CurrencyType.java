package org.academiadecodigo.codezillas.trade_n.currency;

public enum CurrencyType {
    BITCOIN("Bitcoin", "BTC"),
    EURO("Euro", "EUR");

    private String currencyName;
    private String code;

    CurrencyType(String currencyName, String code){
        this.currencyName = currencyName;
        this.code = code;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getCode(){
        return this.code;
    }
}
