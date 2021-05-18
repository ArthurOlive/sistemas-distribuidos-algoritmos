package com.client;

public class c1 {
    
    public static void main (String [] args) {

        Client cliente = new Client(3000);

        Thread thread = new Thread(cliente);
        thread.start();

    }
    
}
