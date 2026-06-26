package APPLIANCE.CONTROLLER;

import APPLIANCE.ENTITY.Appliance;
import APPLIANCE.SERVICE.ApplianceService;
import APPLIANCE.Utility.ApplianceUtility;

import java.util.List;

public class ApplianceController {

    private ApplianceService applianceService;

    // Constructor
    public ApplianceController() {
        this.applianceService = new ApplianceService();
    }

    //  Add appliance
    public boolean addAppliance(Appliance appliance) {
        ApplianceUtility.isValidString(appliance.getName());
        ApplianceUtility.equalZero(appliance.getUsageDuration());
        ApplianceUtility.equalZero(appliance.getPowerRating());

        applianceService.addAppliance(appliance);
        return true;
    }

    // Get appliance by name
    public Appliance getAppliance(String name) {
        ApplianceUtility.isValidString(name);
        return applianceService.readAppliance(name);
    }

    // Get all appliances
    public List<Appliance> getAllAppliances() {
        return applianceService.getApplianceList();
    }

    // Update appliance
    public boolean updateAppliance(String name, String newName,
                                   double newPowerRating, double newDuration) {
        ApplianceUtility.isValidString(name);
        ApplianceUtility.isValidString(newName);

        ApplianceUtility.isNegative(newPowerRating);
        ApplianceUtility.isNegative(newDuration);

        return applianceService.updateAppliance(name, newName, newPowerRating, newDuration);
    }

    // Delete appliance
    public boolean deleteAppliance(String name) {
        ApplianceUtility.isValidString(name);
        return applianceService.deleteAppliance(name);
    }

    //Search appliances by type
    public List<Appliance> getAppliancesByType(String type) {
       ApplianceUtility.isValidType(type);
        return applianceService.getAppliancesByType(type);
    }

}
