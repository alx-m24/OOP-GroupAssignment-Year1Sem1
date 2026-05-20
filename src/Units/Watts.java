package Units;

public class Watts {
    private double m_value;

    public Watts(double value) {
        m_value = value;
    }

    public double getValue() {
        return m_value;
    }

    public void setValue(double value) {
        m_value = value;
    }

    public KiloWatts toKiloWatts() {
        return new KiloWatts(m_value / 1000.0);
    }

    public KiloWattHours toKWh(double hours) {
        return new KiloWattHours(this.toKiloWatts().getValue() * hours);
    }
}
