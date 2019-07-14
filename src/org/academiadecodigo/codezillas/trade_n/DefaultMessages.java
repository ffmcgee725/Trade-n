package org.academiadecodigo.codezillas.trade_n;

import org.academiadecodigo.codezillas.trade_n.colors.Color;

public class DefaultMessages {

    /* ERROR MESSAGES */
    public static final String NO_ACCOUNTS_ERROR = Color.RED + "You must have at least 1 account to make transfers." + Color.RESET;

    public static final String ACCOUNTS_ERROR = Color.RED + "You must have at least 2 accounts." + Color.RESET;

    public static final String USERNAME_ERROR = Color.RED + " doesn't exist." + Color.RESET;

    public static final String USERNAME_ERROR_2 = Color.RED + " already exists in our database." + Color.RESET;

    public static final String EXCHANGE_ERROR = Color.RED + "Failed to exchange!" + Color.RESET;

    public static final String PASSWORD_ERROR = Color.RED + "Invalid password." + Color.RESET;

    public static final String CURRENCY_ERROR = Color.RED + "You already have an account with that type of currency." + Color.RESET;

    public static final String DEPOSIT_ERROR = Color.RED + "Deposit failed. No account found." + Color.RESET;

    public static final String AMOUNT_ERROR = Color.RED + "Enter the value to deposit: " + Color.RESET;

    /* INFORMATION MESSAGES */
    public static final String INSERT_USERNAME = Color.GREEN + "Insert username: " + Color.RESET;

    public static final String LOGIN = "Login\n";

    public static final String LOGOUT = "Logout";

    public static final String USERNAME = Color.GREEN + "Introduce your username: " + Color.RESET;

    public static final String PASSWORD = Color.GREEN + "Introduce your password: " + Color.RESET;

    public static final String SUCCESSFUL_REGISTER = Color.YELLOW + "\nRegister Successful!" + Color.RESET;

    public static final String SUCCESSFUL_LOGIN = Color.YELLOW + "\nLogin Sucessful!" + Color.RESET;

    public static final String WELCOME = Color.CYAN + "\nWelcome to TRADE-N!" + Color.RESET;

    public static final String WELCOME_2 = Color.CYAN + "Welcome to Trade-N StartServer!\n" + Color.RESET + "\nSelect your option: ";

    public static final String WELCOME_3 = Color.CYAN + "Welcome " + Color.RESET;

    public static final String OPTION_SELECTION = "\n" + "Select your option!";

    public static final String DEPOSIT = Color.GREEN + "Enter the value to deposit: " + Color.GREEN;

    public static final String DEBIT = Color.GREEN + "Enter value to debit: " + Color.GREEN;

    public static final String DEBIT_2 = "Select the Amount to Debit";

    public static final String BALANCE = Color.YELLOW + "Your balance is: " + Color.YELLOW;

    public static final String ORIGIN_AMOUNT = "Select Origin Account";

    public static final String DESTINATION_AMOUNT = "Select Destination Account";

}
