package Units;

public class KiloWatts {
    private double m_value;

    public KiloWatts(double value) {
        m_value = value;
    }

    public double getValue() {
        return m_value;
    }

    public void setValue(double value) {
        m_value = value;
    }

    public Watts toWatts() {
        return new Watts(m_value * 1000.0);
    }
}
