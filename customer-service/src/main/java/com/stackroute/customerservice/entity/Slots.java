package com.stackroute.customerservice.entity;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Slots {
    private Map<String, Map<String, SlotStatus>> availableSLot = new HashMap<>();
    public Slots(Map<String, Map<String, SlotStatus>> availableSLot) {
        this.availableSLot = availableSLot;
    }
    public Slots() {
        super();
    }
    public Map<String, Map<String, SlotStatus>> getAvailableSLot() {
        return availableSLot;
    }
    public void setAvailableSLot(Map<String, Map<String, SlotStatus>> availableSLot) {
        this.availableSLot = availableSLot;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slots slots = (Slots) o;
        return Objects.equals(availableSLot, slots.availableSLot);
    }
    @Override
    public int hashCode() {
        return Objects.hash(availableSLot);
    }
}
