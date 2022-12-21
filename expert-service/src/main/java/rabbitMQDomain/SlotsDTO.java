package rabbitMQDomain;
import com.stackroute.expertservice.entity.SlotStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SlotsDTO {
    private Map<String, Map<String, SlotStatusDTO>> availableSLot = new HashMap<>();

    public SlotsDTO(Map<String, Map<String, SlotStatusDTO>> availableSLot) {
        this.availableSLot = availableSLot;
    }

    public Map<String, Map<String, SlotStatusDTO>> getAvailableSLot() {
        return availableSLot;
    }

    public void setAvailableSLot(Map<String, Map<String, SlotStatusDTO>> availableSLot) {
        this.availableSLot = availableSLot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SlotsDTO slots = (SlotsDTO) o;
        return Objects.equals(availableSLot, slots.availableSLot);
    }
    @Override
    public int hashCode() {
        return Objects.hash(availableSLot);
    }
}
