package APPLIANCE.CONTROLLER;

import java.util.ArrayList;
import java.util.List;

import APPLIANCE.ENTITY.Appliance;
import APPLIANCE.SERVICE.ApplianceService;

public class ApplianceController {

    private ApplianceService applianceService;

    // Constructor
    public ApplianceController() {
        this.applianceService = new ApplianceService();
    }

    // Filter appliances by type (MAIN USE CASE)
    public ArrayList<Appliance> getAppliancesByType(ArrayList<Appliance> appliances, String type) {
        return applianceService.getAppliancesByType(appliances, type);
    }

}