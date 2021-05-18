package com.client;

public class c2 {
    
    public static void main (String [] args) {

        Client cliente = new Client(3001);

        Thread thread = new Thread(cliente);
        thread.start();

    }

}
