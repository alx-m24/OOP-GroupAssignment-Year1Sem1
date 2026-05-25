package Appliance;

import Units.*;

import java.time.Duration;
import java.time.Instant;

// Base class for every Appliance in the system
public class Appliance {
    // Private Members for Encapsulation
    protected final String m_name;
    protected final Watts m_powerRating;

    protected boolean m_poweredOn = false;
    protected Instant m_poweredOnTimestamp = null;
    protected Duration m_totalTurnedOnDuration = Duration.ZERO;

    public Appliance(String name, Watts powerRating) {
        m_name = name;
        m_powerRating = powerRating;
    }

    public String getName() {
        return m_name;
    }

    public  Watts getPowerRating() {
        return m_powerRating;
    }

    public void TurnOn() {
        if (m_poweredOn) return;
        m_poweredOn = true;
        m_poweredOnTimestamp = Instant.now();
    }

    public void TurnOff() {
        if (!m_poweredOn) {
            return;
        }
        m_totalTurnedOnDuration = m_totalTurnedOnDuration.plus(Duration.between(m_poweredOnTimestamp, Instant.now()));
        m_poweredOn = false;
    }

    public Duration getTotalPoweredOnDuration() {
        if (!m_poweredOn) {
            return m_totalTurnedOnDuration;
        }

        return m_totalTurnedOnDuration.plus(getCurrentPoweredOnDuration());
    }

    public Duration getCurrentPoweredOnDuration() {
        if (!m_poweredOn) {
            return Duration.ZERO;
        }
        return Duration.between(m_poweredOnTimestamp,  Instant.now());
    }

    public boolean isPoweredOn() {
        return m_poweredOn;
    }

    public KiloWattHours calculateEnergyConsumed() {
        double hours = getTotalPoweredOnDuration().getSeconds() / 3600.0;
        return new KiloWattHours(m_powerRating.getValue() / 1000.0 * hours);
    }
}
