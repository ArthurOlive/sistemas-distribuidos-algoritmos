package project.utils;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Queue;

public class HandleQueueProcess implements Runnable {
    private Queue<Process> queue;
    private Resource resourse;
    HandleQueueProcess(Queue<Process> queue) throws MalformedURLException, RemoteException, NotBoundException{
        this.queue  = queue;    
        resourse = (Resource) Naming.lookup("//localhost:1099/RecursoService");  
    }
    
    @Override
    public void run() {
        while(true){
            if(queue.size()>0){
                try {
                    if(resourse.isSemaphoro()){
                        var process = queue.poll();
                        System.out.println("[LOG]Processo " + process.getId() + " esta acessando o recurso");
                        resourse.enter(process.getId());
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            
        }
    }
    
}
