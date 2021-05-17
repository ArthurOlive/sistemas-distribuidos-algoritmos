package project.utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Process {
    public Process(int id, int from, int to, boolean init) throws IOException {
        if(init){
            Thread tClient = new Thread(new Client(id, to));
            tClient.start();
        }        

        ServerSocket serverSocket = new ServerSocket(from);
        while (true) {
            Socket request = serverSocket.accept();
            Thread tServer = new Thread(new Server(id, to, request));
            tServer.start();
        }
    }

    public Process(int id, int from, int to) throws IOException {
        ServerSocket serverSocket = new ServerSocket(from);
        while (true) {
            Socket request = serverSocket.accept();
            Thread tServer = new Thread(new Server(id, to, request));
            tServer.start();
        }
    }
}