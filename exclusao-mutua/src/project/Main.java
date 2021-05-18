package project;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import project.utils.Process;
import project.utils.Manager;

public class Main{
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        var manager = new Manager();
        var p1 = new Thread(new Process(manager));
        var p2 = new Thread(new Process(manager));
        var p3 = new Thread(new Process(manager));

        p1.start();
        p2.start();
        p3.start();
    }
}