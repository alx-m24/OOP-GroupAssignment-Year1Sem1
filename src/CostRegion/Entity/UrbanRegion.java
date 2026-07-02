package CostRegion.Entity;

import Utils.Units.KiloWattsHour;

public class UrbanRegion extends CostRegionEntity {
    public UrbanRegion() {
        super("Urban");
    }

    @Override
    public double getRatePerKWh() {
        return 0.21; // base rate
    }

    @Override
    public double calculateCost(KiloWattsHour energy) {
        // tiered: first 200kWh at base, anything above at premium
        if (energy.getValue() <= 200) return energy.getValue() * 0.21;
        return (200 * 0.21) + ((energy.getValue() - 200) * 0.35);
    }
}