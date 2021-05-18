package project;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import project.utils.Process;

public class Main{
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        List<Process> processes = new ArrayList<>();
        processes.add(new Process());
        processes.add(new Process());
        processes.add(new Process());
        processes.add(new Process());
        processes.add(new Process());
        var manager = processes.get(4);

        var p0 = new Thread(processes.get(0));
        var p1 = new Thread(processes.get(1));
        var p2 = new Thread(processes.get(2));
        var p3 = new Thread(processes.get(3));
        var p4 = new Thread(processes.get(4));

        p0.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();
    }
}