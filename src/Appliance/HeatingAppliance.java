package Appliance;

import Units.KiloWattHours;
import Units.Watts;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class HeatingAppliance extends  Appliance {
    private static class TempState {
        int targetTemp = 0;
        double ambientTemp = 0.0;
        boolean turnedOn = false;

        public TempState() { }
        public TempState(boolean turnedOn, int targetTemp, double ambientTemp) {
            this.targetTemp = targetTemp;
            this.ambientTemp = ambientTemp;
            this.turnedOn = turnedOn;
        }
    }
    private double m_ambientTemp;
    private int m_targetTemp;
    private TreeMap<Instant, TempState> m_updates;

    public HeatingAppliance(String name, Watts powerRating, double ambientTemp, int defaultTargetTemp) {
        super(name, powerRating);
        m_ambientTemp = ambientTemp;
        m_targetTemp = defaultTargetTemp;
        pushRecordState();
    }

    private void pushRecordState() {
        m_updates.put(Instant.now(), new TempState(isPoweredOn(), m_targetTemp, m_ambientTemp));
    }

    public void setAmbientTemperature(double ambientTemperature) {
        m_ambientTemp = ambientTemperature;
        pushRecordState();
    }

    public void setTargetTemperature(int targetTemperature) {
        if (!isPoweredOn()) return;
        m_targetTemp = targetTemperature;
        pushRecordState();
    }

    @Override
    public KiloWattHours calculateEnergyConsumed() {
        if (m_updates.isEmpty()) {
            return new KiloWattHours(0.0);
        }

        double totalKwh = 0.0;
        double baseKw = m_powerRating.getValue() / 1000.0;
        Instant endTime = Instant.now();

        List<Instant> timestamps = new ArrayList<>(m_updates.keySet());

        for (int i = 0; i < timestamps.size(); i++) {
            Instant currentTick = timestamps.get(i);

            // If it's the last recorded update, it ran from that timestamp until "now"
            Instant nextTick = (i == timestamps.size() - 1) ? endTime : timestamps.get(i + 1);

            double hours = Duration.between(currentTick, nextTick).getSeconds() / 3600.0;

            TempState state = m_updates.get(currentTick);

            if (state.turnedOn) {
                double tempDelta = Math.max(0.0, state.targetTemp - state.ambientTemp);
                double surgeFactor = 1.0 + (tempDelta * 0.01);

                totalKwh += baseKw * hours * surgeFactor;
            }
        }

        return new KiloWattHours(totalKwh);
    }
}
