package com.service.utils;

import java.time.LocalDateTime;

import com.model.Clock;

public class ClockTime implements Runnable {

    private Clock clock;

    public ClockTime (Clock clock) {
        this.clock = clock; 
    } 

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            
            try {
                Thread.sleep(1000);
                clock.setTime(clock.getTime().plusSeconds(1));
                System.out.println("Time change:" + clock.getTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
