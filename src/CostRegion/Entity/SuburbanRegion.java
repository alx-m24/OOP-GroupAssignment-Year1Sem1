package CostRegion.Entity;

public class SuburbanRegion extends CostRegionEntity {
    public SuburbanRegion() {
        super("Suburban");
    }

    @Override
    public double getRatePerKWh() {
        return 0.25;
    }

    // no override needed — flat rate, base calculateCost works fine
}