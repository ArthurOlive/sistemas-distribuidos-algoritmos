package com.service.utils;

import java.time.LocalDateTime;

public class ClockTime implements Runnable {

    private LocalDateTime clock;

    public ClockTime (LocalDateTime clock) {
        this.clock = clock; 
    } 

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            
            try {
                Thread.sleep(1000);
                clock = clock.plusSeconds(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
