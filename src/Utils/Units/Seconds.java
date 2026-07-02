package Utils.Units;

public class Seconds {
    private double value;

    public Seconds() {
        value = 0.0;
    }
    public Seconds(double value) {
        setValue(value);
    }
    public Seconds(Hours hours) {
        setValue(hours.getValue() * 3600.0);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        if (value < 0) {
            System.out.println("Seconds cannot be negative");
        }
        this.value = value;
    }

    public Hours toHours() {
        return new Hours(this);
    }

    @Override
    public String toString() {
        return value + "s";
    }
}