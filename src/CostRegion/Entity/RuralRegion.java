package CostRegion.Entity;

import Utils.Units.KiloWattsHour;

public class RuralRegion extends CostRegionEntity {
    private static final double CAP = 50.00; // max bill RM50

    public RuralRegion() {
        super("Rural");
    }

    @Override
    public double getRatePerKWh() {
        return 0.15;
    }

    @Override
    public double calculateCost(KiloWattsHour energy) {
        return Math.min(energy.getValue() * 0.15, CAP);
    }
}