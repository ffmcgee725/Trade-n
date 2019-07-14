package org.academiadecodigo.codezillas.trade_n.server;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.codezillas.trade_n.menu.mainmenu.Menu;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class RegisterManager implements Runnable {

    private static ConcurrentHashMap<String, Register> registers;
    private Socket socket;
    private Prompt prompt;
    private PrintWriter printWriter;

    public RegisterManager(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        start();
    }

    public void start(){
        try {
            prompt = new Prompt(socket.getInputStream(), new PrintStream(socket.getOutputStream()));
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Menu menu = new Menu(prompt,this);
        menu.start();
    }

    public void logIn(){
        printWriter.println("Login");
        StringInputScanner stringInputScanner = new StringInputScanner();
        stringInputScanner.setMessage("Introduze your username: ");
        String username = prompt.getUserInput(stringInputScanner);

        if (!registers.containsKey(username)){
            printWriter.println(username + "doesn't exist");
            return;
        }

        stringInputScanner.setMessage("Introduze your password: ");
        String password = prompt.getUserInput(stringInputScanner);

        if (registers.get(username).getPassword().equals(password)){
            printWriter.println("Login Sucessfull!");
            registers.get(username).getClientHandler().setSocket(socket);
            registers.get(username).getClientHandler().run();
        }
    }

    public void register(){

        StringInputScanner stringInputScanner = new StringInputScanner();
        stringInputScanner.setMessage("Introduze your username: ");
        String username = prompt.getUserInput(stringInputScanner);

        if (registers.containsKey(username)){
            printWriter.println(username + " already exists in our database");
            return;
        }

        stringInputScanner.setMessage("Introduze your password: ");
        String password = prompt.getUserInput(stringInputScanner);

        registers.put(username,new Register(username,password,socket));
        printWriter.println("Register Sucessfull!");
        printWriter.println("Welcome to TRADE-N!");

        registers.get(username).getClientHandler().setSocket(socket);
        registers.get(username).getClientHandler().run();
    }

    public void leave(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setRegisters(ConcurrentHashMap<String, Register> registers) {
        RegisterManager.registers = registers;
    }
}
