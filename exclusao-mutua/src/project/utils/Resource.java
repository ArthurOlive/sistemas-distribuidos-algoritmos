package project.utils;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Resource extends Remote{
    public void enter(int id) throws RemoteException;
    public boolean isSemaphoro() throws RemoteException;
}
