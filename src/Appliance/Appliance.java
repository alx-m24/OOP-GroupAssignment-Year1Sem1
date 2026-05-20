package Appliance;

import Units.*;

import java.time.Duration;
import java.time.Instant;

// Base class for every Appliance in the system
public class Appliance {
    // Private Members for Encapsulation
    private final String m_name;
    private final Watts m_powerRating;
    private final KiloWattHours m_energyConsumption;

    private boolean m_poweredOn = false;

    private Instant m_poweredOnTimestamp = null;

    private Duration m_totalTurnedOnDuration = Duration.ZERO;

    public Appliance(String name, Watts powerRating, KiloWattHours energyConsumption) {
        m_name = name;
        m_powerRating = powerRating;
        m_energyConsumption = energyConsumption;
    }

    public String getName() {
        return m_name;
    }

    public  Watts getPowerRating() {
        return m_powerRating;
    }

    public  KiloWattHours getEnergyConsumption() {
        return m_energyConsumption;
    }

    public void TurnOn() {
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
}
