package Utils.Units;

public class KiloWatts {
    private double value;

    public KiloWatts() {
        value = 0.0;
    }
    public KiloWatts(double value) {
        setValue(value);
    }
    public KiloWatts(Watts watts) {
        setValue(watts.getValue() / 1000.0);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        if (value < 0.0) {
            System.out.println("KiloWatts cannot be negative");
        }
        this.value = value;
    }

    Watts toWatts() {
        return new Watts(this);
    }

    @Override
    public String toString() {
        return value + " kw";
    }
}
