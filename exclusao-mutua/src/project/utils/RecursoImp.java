package project.utils;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RecursoImp extends UnicastRemoteObject implements Recurso {
    private boolean semaphoro = true;

    public RecursoImp() throws RemoteException {
        super();
    }

    @Override
    public void entrar(int id) throws RemoteException {
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
            System.out.println("Acesso: " + id);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public boolean isSemaphoro() throws RemoteException {
        return this.semaphoro;
    }
}
