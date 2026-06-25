package Cost.Entity;

import Cost.Entity.Base.CostEstimator;

public class RuralTariff extends CostEstimator {
    private double subsidyDiscountRate;
    private double subsidyThreshold;

    public RuralTariff(double subsidyDiscountRate, double subsidyThreshold) {
        super("RuralTariff", 0.38);
        this.subsidyDiscountRate = subsidyDiscountRate;
        this.subsidyThreshold = subsidyThreshold;
    }

    public void setSubsidyDiscountRate(double subsidyDiscountRate) {
        if (subsidyDiscountRate > 0.0) {
            this.subsidyDiscountRate = subsidyDiscountRate;
        }
    }

    public void setSubsidyThreshold(double subsidyThreshold) {
        if (subsidyThreshold > 0.0) {
            this.subsidyThreshold = subsidyThreshold;
        }
    }

    public double getSubsidyDiscountRate() {
        return subsidyDiscountRate;
    }

    public double getSubsidyThreshold() {
        return subsidyThreshold;
    }

    @Override
    public double calculateCost(double kwh) {
        double baseCost = super.calculateCost(kwh);
        if (kwh > subsidyThreshold) {
            double excessKwh = kwh - subsidyThreshold;
            double discount = excessKwh * super.getRatePerKwH() * subsidyDiscountRate;
            return baseCost - discount;
        }
        return baseCost;
    }
}