package Appliance;

import Units.KiloWattHours;
import Units.Watts;

import java.time.Duration;
import java.time.Instant;

public class CoolingAppliance extends Appliance {
    private boolean m_isInEcoMode;

    private Instant m_ecoModeStartTime = null;
    private Duration m_ecoModeDuration = null;

    public CoolingAppliance(String name, Watts powerRating) {
        super(name, powerRating);
        disableEcoMode();
    }

    public void enableEcoMode() {
        if (m_isInEcoMode && !isPoweredOn()) return;
        m_isInEcoMode = true;
        m_ecoModeStartTime = Instant.now();
    }

    public void disableEcoMode() {
        if (!m_isInEcoMode || !isPoweredOn()) return;
        m_isInEcoMode = false;
        m_ecoModeDuration = m_ecoModeDuration.plus(Duration.between(m_ecoModeStartTime, Instant.now()));
    }

    @Override
    public KiloWattHours calculateEnergyConsumed() {
        double hours = getTotalPoweredOnDuration().getSeconds() / 3600.0;

        double hoursEcoMode = m_ecoModeDuration.getSeconds() / 3600.0;
        double hoursFullPower = hours - hoursEcoMode;

        double fullPower = hoursFullPower * m_powerRating.getValue() / 1000.0 * hours;
        double ecoPower = hoursEcoMode * m_powerRating.getValue() * 0.85 * hours;

        return new KiloWattHours(fullPower + ecoPower);
    }
}
