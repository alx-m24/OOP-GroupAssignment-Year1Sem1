package Units;

public class KiloWattHours {
    private double m_value;

    public KiloWattHours(double value) {
        m_value = value;
    }

    public double getValue() {
        return m_value;
    }

    public void setValue(double value) {
        m_value = value;
    }

    public Joules toJoules() {
        return new Joules(m_value * 3.6e+6);
    }
}
