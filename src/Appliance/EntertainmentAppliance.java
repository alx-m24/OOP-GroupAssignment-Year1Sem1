package Appliance;

import Units.KiloWattHours;
import Units.Watts;

import java.time.Duration;
import java.time.Instant;

public class EntertainmentAppliance extends Appliance {
    private final Watts m_idlePower;

    private boolean m_idling = false;
    private Instant m_idlingStartTimeStamp = null;
    private Duration m_idlingDuration = Duration.ZERO;

    public EntertainmentAppliance(String name, Watts powerRating, Watts idlePower) {
        super(name, powerRating);
        m_idlePower = idlePower;
    }

    @Override
    public void TurnOn() {
        if (m_poweredOn) return;
        m_idling = false;
        m_poweredOn = true;
        m_poweredOnTimestamp = Instant.now();
    }

    @Override
    public void TurnOff() {
        if (!m_poweredOn) return;
        EndIdling();
        m_totalTurnedOnDuration = m_totalTurnedOnDuration.plus(Duration.between(m_poweredOnTimestamp, Instant.now()));
        m_poweredOn = false;
    }

    public void EnterIdling() {
        if (m_idling) return;
        if (!isPoweredOn()) TurnOn();
        m_idling = true;
        m_idlingStartTimeStamp = Instant.now();
    }

    public void EndIdling() {
        if (!m_idling) return;
        m_idling = false;
        m_idlingDuration = m_idlingDuration.plus(Duration.between(m_idlingStartTimeStamp, Instant.now()));
    }

    public Duration getFullPowerDuration() {
        return getTotalPoweredOnDuration().minus(getTotalIdlingDuration());
    }

    public Duration getTotalIdlingDuration() {
        if (m_idling) {
            return m_idlingDuration.plus(Duration.between(m_idlingStartTimeStamp, Instant.now()));
        }
        return m_idlingDuration;
    }

    @Override
    public KiloWattHours calculateEnergyConsumed() {
        double fullEnergyHours = getFullPowerDuration().getSeconds() / 3600.0;
        double idlingEnergyHours = getTotalIdlingDuration().getSeconds() / 3600.0;

        double activeKwhVal = (m_powerRating.getValue() / 1000.0) * fullEnergyHours;
        double idlingKwhVal = (m_idlePower.getValue() / 1000.0) * idlingEnergyHours;

        if (activeKwhVal < 0 || idlingKwhVal < 0) {
            return new KiloWattHours(0);
        }

        return new KiloWattHours(activeKwhVal + idlingKwhVal);
    }
}
