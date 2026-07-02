package Utils.Units;

public class Hours {
    private double value;

    public Hours() {
        value = 0.0;
    }
    public Hours(double value) {
        setValue(value);
    }
    public Hours(Seconds seconds) {
        setValue(seconds.getValue() / 3600.0);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        if (value < 0) {
            System.out.println("Hours cannot be negative");
        }
        this.value = value;
    }

    public Seconds toSeconds() {
        return new Seconds(this);
    }

    @Override
    public String toString() {
        return value + "h";
    }
}