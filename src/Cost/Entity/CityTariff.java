package Cost.Entity;

import Cost.Entity.Base.CostEstimator;

public class CityTariff extends CostEstimator {
    private double surchargeRate;
    private double surchargeThreshold;

    public CityTariff(double surchargeRate, double surchargeThreshold) {
        super("CityTariff", 0.57);
        setSurchargeRate(surchargeRate);
        setSurchargeThreshold(surchargeThreshold);
    }

    @Override
    public double calculateCost(double kwh) {
        double baseCost = super.calculateCost(kwh);
        if (kwh > surchargeThreshold) {
            double excessKwh = kwh - surchargeThreshold;
            return baseCost + (excessKwh * surchargeRate);
        }
        return baseCost;
    }

    public void setSurchargeRate(double surchargeRate) {
        if (surchargeRate > 0.0) {
            this.surchargeRate = surchargeRate;
        }
    }

    public void setSurchargeThreshold(double surchargeThreshold) {
        if (surchargeThreshold > 0.0) {
            this.surchargeRate = surchargeThreshold;
        }
    }

    public double getSurchargeRate() {
        return surchargeRate;
    }

    public double getSurchargeThreshold() {
        return surchargeThreshold;
    }
}
