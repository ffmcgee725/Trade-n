package org.academiadecodigo.codezillas.trade_n.currency.currencystrategy;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;

public class CurrencyPromptMenu {

    public static int userNumberInput(String message) {
        IntegerInputScanner scanner = new IntegerInputScanner();
        scanner.setMessage(message);

        Prompt prompt = new Prompt(System.in, System.out);

        return prompt.getUserInput(scanner);
    }

    public static double userDoubleInput(String message) {

        DoubleInputScanner scanner = new DoubleInputScanner();
        scanner.setMessage(message);

        Prompt prompt = new Prompt(System.in, System.out);

        return prompt.getUserInput(scanner);
    }

    public static int userOptionInput() {

        String[] options = {"View Balance", "Make Deposit", "Make Withdrawal", "Open Account", "Quit"};

        MenuInputScanner scanner = new MenuInputScanner(options);
        scanner.setMessage("Welcome to Java Bank");

        Prompt prompt = new Prompt(System.in, System.out);

        return prompt.getUserInput(scanner);
    }

    public static int userOptionInput(String message, Object[] arrayOfOptions) {
        String[] options = new String[arrayOfOptions.length];
        for (int i = 0; i < arrayOfOptions.length; i++) {
            options[i] = arrayOfOptions[i] + "";
        }

        MenuInputScanner scanner = new MenuInputScanner(options);
        scanner.setMessage(message);

        Prompt prompt = new Prompt(System.in, System.out);

        return prompt.getUserInput(scanner);
    }
}
