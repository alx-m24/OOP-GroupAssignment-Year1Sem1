package Utils.Units;

public class KiloWattsHour {
    private double value;

    public KiloWattsHour() {
        value = 0.0;
    }
    public KiloWattsHour(double value) {
        setValue(value);
    }
    public KiloWattsHour(Watts energy, Hours time) {
        setValue(energy.getValue() / time.getValue());
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        if (value < 0.0) {
            System.out.println("KiloWattsHour cannot be negative");
        }
        this.value = value;
    }

    public void setValue(Watts energy, Hours time) {
        double newValue = energy.getValue() / time.getValue();
        if (newValue < 0.0) {
            System.out.println("KiloWattsHour cannot be negative");
        }
        this.value = newValue;
    }

    @Override
    public String toString() {
        return value + "kwh";
    }
}
