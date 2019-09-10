package sample;

import java.util.Date;

public class Parking {
    private Vehicle vehicle;
    private Date entry;
    private Date exit;

    public Parking(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.entry = new Date ();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int exitVehicle () {
        this.exit = new Date ();
        long time = this.exit.getTime() - this.entry.getTime();
        return (int) Math.ceil(time / 60_000.0);
    }
}
