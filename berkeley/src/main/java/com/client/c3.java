package com.client;

public class c3 {
    public static void main (String [] args) {

        Client cliente = new Client(3002);

        Thread thread = new Thread(cliente);
        thread.start();

    }
}
