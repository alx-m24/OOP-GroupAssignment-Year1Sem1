package Cost.Service;

import Cost.Entity.*;
import Cost.Entity.Base.*;

public class CostService {
    private CostEstimator selectedTariff;

    public CostService(String type) {
        selectTariff(type);
    }

    public void selectTariff(String type) {
        switch (type.toLowerCase()) {
            case "city":
                selectedTariff = new CityTariff(0.15, 300);
                break;
            case "suburban":
                selectedTariff = new SuburbanTariff();
                break;
            case "rural":
                selectedTariff = new RuralTariff(0.20, 200);
                break;
            default:
                break;
        }
    }

    public CostEstimator getSelectedTariff() {
        return selectedTariff;
    }

    public double calculateDailyCost(double dailyKwh) {
        return selectedTariff.calculateCost(dailyKwh);
    }

    public double calculateMonthlyCost(double dailyKwh) {
        return selectedTariff.calculateCost(dailyKwh * 30);
    }

    public double[] compareTariffs(double kwh) {
        CostEstimator city     = new CityTariff(0.15, 300);
        CostEstimator suburban = new SuburbanTariff();
        CostEstimator rural    = new RuralTariff(0.20, 200);

        return new double[] {
                city.calculateCost(kwh),
                suburban.calculateCost(kwh),
                rural.calculateCost(kwh)
        };
    }
}