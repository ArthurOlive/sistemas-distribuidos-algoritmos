package project.utils;

public class Process implements Runnable{
    static int nextId = 1;
    private int id;
    public Process(){
        id = nextId++;
    } 

    public int getId(){
        return this.id;
    }

    @Override
    public void run() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
