package com.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.service.parse.Parser;

public class TimerSicronize extends Parser {
    
    private LocalDateTime time;
    private long timeDeslocated;

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public void setTimeDeslocated(long timeDeslocated) {
        this.timeDeslocated = timeDeslocated;
    }

    public long getTimeDesclocated() {
        return this.timeDeslocated;
    }
}