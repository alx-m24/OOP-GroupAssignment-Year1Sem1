package Report.Service;

import Appliance.Entity.ApplianceEntity;
import Appliance.Service.ApplianceService;
import CostRegion.Service.CostRegionService;
import Report.Entity.ReportEntity;
import Utils.ID.CostRegionID;
import Utils.ID.HouseholdID;
import Utils.Units.Hours;
import Utils.Units.KiloWattsHour;
import Utils.Units.Watts;

import java.util.ArrayList;

public class ReportService {
    private final ApplianceService applianceService;
    private final CostRegionService costRegionService;

    public ReportService(ApplianceService applianceService, CostRegionService costRegionService) {
        this.applianceService = applianceService;
        this.costRegionService = costRegionService;
    }

    public ReportEntity generate(HouseholdID householdID, CostRegionID costRegionID) {
        KiloWattsHour totalKWh = new KiloWattsHour(0.0);

        for (ApplianceEntity appliance : applianceService.getByHousehold(householdID)) {
            totalKWh.setValue(totalKWh.getValue() + appliance.getEnergyUsed().getValue());
        }

        double totalCost = costRegionService.calculateCostForRegion(costRegionID, totalKWh);
        ArrayList<String> suggestions = generateSuggestions(applianceService.getByHousehold(householdID));

        return new ReportEntity(householdID, totalKWh, totalCost, suggestions);
    }

    private ArrayList<String> generateSuggestions(ArrayList<ApplianceEntity> appliances) {
        ArrayList<String> suggestions = new ArrayList<>();

        for (ApplianceEntity a : appliances) {
            if (a.getUsage().getValue() > 8) {
                suggestions.add("Consider reducing usage of " + a.getApplianceName()
                        + " — currently running " + a.getUsage().getValue() + "h/day.");
            }
            if (a.getPowerRating().getValue() > 2000) {
                suggestions.add(a.getApplianceName()
                        + " is a high-power appliance. Use only when necessary.");
            }
        }

        if (suggestions.isEmpty()) {
            suggestions.add("Great job! Your energy consumption looks healthy.");
        }

        return suggestions;
    }
}