package Cost.Entity;

import Cost.Entity.Base.CostEstimator;

public class SuburbanTariff extends CostEstimator {
    public SuburbanTariff() {
        super("SuburbanTariff", 0.44);
    }

    @Override
    public double calculateCost(double kwh) {
        return super.calculateCost(kwh);
    }
}
