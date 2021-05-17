package project.utils;

import java.rmi.RemoteException;

public class Process{
    static int nextId = 1;
    private int id;
    public Process(){
        id = nextId++;
    } 

    public void request(Manager manager) throws RemoteException{
        manager.giveAcess(this);     
    }

    public int getId(){
        return this.id;
    }
}
