package project.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;

public class ResourceImp extends UnicastRemoteObject implements Resource {
    private boolean semaphoro = true;

    public ResourceImp() throws RemoteException {
        super();
    }

    @Override
    public void enter(int id) throws RemoteException {
        if (semaphoro) {
            semaphoro = false;
            acessarRecurso(id);
            sair();
        }
    }

    private void sair() {
        semaphoro = true;
    }

    private void acessarRecurso(int id) {
        try {
            System.out.println("Processo(" + id + ")");
            FileWriter file = new FileWriter("src/file.txt",true);
            file.write("Processo(" + id + "): " + LocalDateTime.now() +"\n");
            Thread.sleep(3000);
            file.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isSemaphoro() throws RemoteException {
        return this.semaphoro;
    }
}
