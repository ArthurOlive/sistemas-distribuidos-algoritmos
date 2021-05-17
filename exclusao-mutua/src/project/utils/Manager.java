package project.utils;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.Queue;


public class Manager {
    private Queue<Process> queueProcess  = new LinkedList<>();
    public Manager() throws MalformedURLException, RemoteException, NotBoundException{   
        Thread thread = new Thread(new HandleQueueProcess(queueProcess));    
        thread.start();   
    }

    public void giveAcess(Process process) throws RemoteException{
        System.out.println("[LOG]Processo " + process.getId() + " entrou na fila");
        queueProcess.add(process);        
    }
}
