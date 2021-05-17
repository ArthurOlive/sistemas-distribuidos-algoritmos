package project;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import project.utils.Recurso;
import project.utils.RecursoImp;

public class RecursoServer {
    public RecursoServer(){
        try {
            System.out.println("Server online...");
            System.setProperty("java.rmi.server.hostname", "localhost");
            LocateRegistry.createRegistry(1099);

            Recurso res = new RecursoImp();

            Naming.bind("RecursoService", res);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new RecursoServer();
    }
}
