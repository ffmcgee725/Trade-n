package org.academiadecodigo.codezillas.trade_n.server;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.PasswordInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.codezillas.trade_n.DefaultMessages;
import org.academiadecodigo.codezillas.trade_n.menu.mainmenu.Menu;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class RegisterManager implements Runnable {

    private static ConcurrentHashMap<String, Register> registries;
    private Socket socket;
    private Prompt prompt;
    private PrintWriter printWriter;

    RegisterManager(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        start();
    }

    private void start() {

        try {
            prompt = new Prompt(socket.getInputStream(), new PrintStream(socket.getOutputStream()));
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Menu menu = new Menu(prompt, this);
        menu.start();
    }

    public void logIn() {

        printWriter.println(DefaultMessages.LOGIN);
        StringInputScanner stringInputScanner = new StringInputScanner();
        stringInputScanner.setMessage(DefaultMessages.USERNAME);
        String username = prompt.getUserInput(stringInputScanner);

        if (!registries.containsKey(username)) {
            printWriter.println(username + DefaultMessages.USERNAME_ERROR);
            return;
        }

        PasswordInputScanner passwordInputScanner = new PasswordInputScanner();
        passwordInputScanner.setMessage(DefaultMessages.PASSWORD);
        String password = prompt.getUserInput(passwordInputScanner);

        if (registries.get(username).getPassword().equals(password)) {
            printWriter.println(DefaultMessages.SUCCESSFUL_LOGIN);
            registries.get(username).getClientHandler().setSocket(socket);
            registries.get(username).getClientHandler().run();
            return;
        }

        printWriter.println(DefaultMessages.PASSWORD_ERROR);
    }

    public void register() {

        StringInputScanner stringInputScanner = new StringInputScanner();
        stringInputScanner.setMessage(DefaultMessages.USERNAME);
        String username = prompt.getUserInput(stringInputScanner);

        if (registries.containsKey(username)) {
            printWriter.println(username + DefaultMessages.USERNAME_ERROR_2);
            return;
        }

        PasswordInputScanner passwordInputScanner = new PasswordInputScanner();
        passwordInputScanner.setMessage(DefaultMessages.PASSWORD);
        String password = prompt.getUserInput(passwordInputScanner);

        registries.put(username, new Register(username, password, socket));
        printWriter.println(DefaultMessages.SUCCESSFUL_REGISTER);
        printWriter.println(DefaultMessages.WELCOME);

        registries.get(username).getClientHandler().setSocket(socket);
        registries.get(username).getClientHandler().run();
    }

    public void leave() {
        try {
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ConcurrentHashMap<String, Register> getRegistries() {
        return registries;
    }

    static void setRegistries(ConcurrentHashMap<String, Register> registries) {
        RegisterManager.registries = registries;
    }
}
