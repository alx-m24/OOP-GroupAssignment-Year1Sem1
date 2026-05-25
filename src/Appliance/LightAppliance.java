package Appliance;


import Units.KiloWattHours;
import Units.Watts;

public class LightAppliance extends  Appliance {
    final double m_efficiencyRating;
    final int m_lightQuantity;

    public LightAppliance(String name, Watts powerRating, double efficiencyRating, int lightQuatity) {
        super(name, powerRating);
        m_efficiencyRating = efficiencyRating;
        m_lightQuantity = lightQuatity;
    }

    @Override
    public KiloWattHours calculateEnergyConsumed() {
        double hours = getTotalPoweredOnDuration().getSeconds() / 3600.0;
        return new KiloWattHours(m_powerRating.getValue() / 1000.0 * hours * m_lightQuantity * m_efficiencyRating);
    }
}
