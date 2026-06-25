package Cost.Entity.Base;

public class CostEstimator {
    private String tariffName;
    private double ratePerKwH;

    public CostEstimator(String tariffName, double ratePerKwH) {
        setTariffName(tariffName);
        setRatePerKwH(ratePerKwH);
    }

    public String getTariffName() {
        return tariffName;
    }

    public double getRatePerKwH() {
        return ratePerKwH;
    }

    public void setTariffName(String tariffName) {
        if (tariffName != null) {
            this.tariffName = tariffName;
        }
    }

    public void setRatePerKwH(double ratePerKwH) {
        if (ratePerKwH > 0.0) {
            this.ratePerKwH = ratePerKwH;
        }
    }

    public double calculateCost(double kwh) {
        return ratePerKwH * kwh;
    }
}
