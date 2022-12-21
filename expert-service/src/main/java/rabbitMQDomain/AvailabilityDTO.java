package rabbitMQDomain;
import com.stackroute.expertservice.entity.Slots;

public class AvailabilityDTO {
    private  String todaydate ;
    private SlotsDTO todaysSlot;
    private String tomorrowdate;
    private SlotsDTO tomorrowSlot;

    public AvailabilityDTO(String todaydate, SlotsDTO todaysSlot, String tomorrowdate, SlotsDTO tomorrowSlot) {
        this.todaydate = todaydate;
        this.todaysSlot = todaysSlot;
        this.tomorrowdate = tomorrowdate;
        this.tomorrowSlot = tomorrowSlot;
    }

    public AvailabilityDTO() {
        super();
    }

    public String getTodaydate() {
        return todaydate;
    }

    public void setTodaydate(String todaydate) {
        this.todaydate = todaydate;
    }

    public SlotsDTO getTodaysSlot() {
        return todaysSlot;
    }

    public void setTodaysSlot(SlotsDTO todaysSlot) {
        this.todaysSlot = todaysSlot;
    }

    public String getTomorrowdate() {
        return tomorrowdate;
    }

    public void setTomorrowdate(String tomorrowdate) {
        this.tomorrowdate = tomorrowdate;
    }

    public SlotsDTO getTomorrowSlot() {
        return tomorrowSlot;
    }

    public void setTomorrowSlot(SlotsDTO tomorrowSlot) {
        this.tomorrowSlot = tomorrowSlot;
    }
}
