package com.server;

import java.util.ArrayList;
import java.util.List;

public class Cache {
    
    private List<Integer> clients;

    public Cache () {
        clients = new ArrayList<>();
    }

    public List<Integer> getClients() {
        return clients;
    }

    public void setClients(List<Integer> clients) {
        this.clients = clients;
    }
    

}
