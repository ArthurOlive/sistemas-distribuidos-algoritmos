package project;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import project.utils.Resource;
import project.utils.ResourceImp;

public class RecursoServer {
    public RecursoServer(){
        try {
            System.out.println("Server online...");
            System.setProperty("java.rmi.server.hostname", "localhost");
            LocateRegistry.createRegistry(1099);

            Resource res = new ResourceImp();

            Naming.bind("RecursoService", res);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new RecursoServer();
    }
}
