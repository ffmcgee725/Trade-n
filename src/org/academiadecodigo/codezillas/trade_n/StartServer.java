package org.academiadecodigo.codezillas.trade_n;

import org.academiadecodigo.codezillas.trade_n.client.Account;
import org.academiadecodigo.codezillas.trade_n.currency.CurrencyType;

public class StartServer {
    public static void main(String[] args) {
        Account account = new Account(CurrencyType.BITCOIN);
        Account account2 = new Account(CurrencyType.EURO);
        Account account3 = new Account(CurrencyType.EURO);

        System.out.println(account.getBalance());

        account.deposit(300);
        account.transfer(150);

        System.out.println(account.getBalance());

        System.out.println(account3.getId());
    }
}
