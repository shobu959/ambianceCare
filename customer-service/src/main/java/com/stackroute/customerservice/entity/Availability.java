package com.stackroute.customerservice.entity;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor

public class Availability {
    private  String todaydate ;
    private Slots todaysSlot;
    private String tomorrowdate;
    private Slots tomorrowSlot;

    public Availability(String todaydate, Slots todaysSlot, String tomorrowdate, Slots tomorrowSlot) {
        this.todaydate = todaydate;
        this.todaysSlot = todaysSlot;
        this.tomorrowdate = tomorrowdate;
        this.tomorrowSlot = tomorrowSlot;
    }

    public String getTodaydate() {
        return todaydate;
    }

    public void setTodaydate(String todaydate) {
        this.todaydate = todaydate;
    }

    public Slots getTodaysSlot() {
        return todaysSlot;
    }

    public void setTodaysSlot(Slots todaysSlot) {
        this.todaysSlot = todaysSlot;
    }

    public String getTomorrowdate() {
        return tomorrowdate;
    }

    public void setTomorrowdate(String tomorrowdate) {
        this.tomorrowdate = tomorrowdate;
    }

    public Slots getTomorrowSlot() {
        return tomorrowSlot;
    }

    public void setTomorrowSlot(Slots tomorrowSlot) {
        this.tomorrowSlot = tomorrowSlot;
    }
}
