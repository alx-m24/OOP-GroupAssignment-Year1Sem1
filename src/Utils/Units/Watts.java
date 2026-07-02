package Utils.Units;

public class Watts {
    private double value;

    public Watts() {
        value = 0.0;
    }
    public Watts(double value) {
        setValue(value);
    }
    public Watts(KiloWatts kiloWatts) {
        this.value = kiloWatts.getValue() * 1000.0;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        if (value < 0.0) {
            System.out.println("Watts cannot be negative");
        }
        this.value = value;
    }

    public KiloWatts toKiloWatts() {
        return new KiloWatts(this);
    }

    @Override
    public String toString() {
        return value + "w";
    }
}
