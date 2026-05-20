package Units;

public class Joules {
    private double m_value;

    public Joules(double value) {
        m_value = value;
    }

    public double getValue() {
        return m_value;
    }

    public void setValue(double value) {
        m_value = value;
    }

    public KiloWattHours toKWh() {
        return new KiloWattHours(m_value / 3.6e+6);
    }
}