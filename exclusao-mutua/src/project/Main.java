package project;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import project.utils.Process;
import project.utils.Manager;

public class Main{
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        var manager = new Manager();
        var p1 = new Process();
        var p2 = new Process();
        var p3 = new Process();

        p1.request(manager);
        p2.request(manager);
        p3.request(manager);
    }
}