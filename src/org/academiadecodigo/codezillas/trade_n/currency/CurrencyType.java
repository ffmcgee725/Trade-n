package org.academiadecodigo.codezillas.trade_n.currency;

public enum CurrencyType {

    EURO("Euro", "EUR"),
    DOLLAR("Dollar", "USD"),
    POUND("British Pound", "GBP"),
    BITCOIN("Bitcoin", "BTC"),
    ETHEREUM("Ethereum","ETH");

    private String currencyName;
    private String code;

    CurrencyType(String currencyName, String code) {
        this.currencyName = currencyName;
        this.code = code;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        return currencyName;
    }
}
