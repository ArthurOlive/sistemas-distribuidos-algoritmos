package project.utils;

import java.rmi.RemoteException;

public class Process implements Runnable{
    static int nextId = 1;
    private int id;
    private Manager manager;
    public Process(Manager manager){
        id = nextId++;
        this.manager = manager;
    } 

    public void request() throws RemoteException{
        manager.giveAcess(this);     
    }

    public int getId(){
        return this.id;
    }

    @Override
    public void run() {
        try {
            request();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
