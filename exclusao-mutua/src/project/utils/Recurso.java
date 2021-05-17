package project.utils;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Recurso extends Remote{
    public void entrar(int id) throws RemoteException;
    public boolean isSemaphoro() throws RemoteException;
}
