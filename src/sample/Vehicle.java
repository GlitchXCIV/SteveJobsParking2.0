package sample;

public class Vehicle {
    enum Kind {
        SCOOTER,
        CAR,
        TRUCK,
    }
    private String plate;
    private Kind kind;

    public Vehicle(String plate, Kind kind) {
        this.plate = plate;
        this.kind = kind;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public double getSpaceForVehicle () {
        if (this.kind == Kind.CAR) return 1;
        else if (this.kind == Kind.SCOOTER) return 1;
        else if (this.kind  == Kind.TRUCK) return 3;
        else

        return 0;
    }

}

