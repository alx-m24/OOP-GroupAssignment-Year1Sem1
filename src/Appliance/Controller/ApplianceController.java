package Appliance.Controller;

import Appliance.Entity.ApplianceEntity;
import Appliance.Service.ApplianceService;
import Appliance.View.ApplianceView;
import Household.Service.HouseholdService;
import Utils.ID.HouseholdID;
import Utils.Units.Hours;
import Utils.Units.Watts;

import java.util.ArrayList;
import java.util.Scanner;

public class ApplianceController {
    final private HouseholdService householdService;
    final private ApplianceService applianceService;
    final private ApplianceView applianceView;
    final private Scanner scanner;

    public ApplianceController(ApplianceService applianceService, HouseholdService householdService, Scanner scanner) {
        this.householdService = householdService;
        this.applianceService = applianceService;
        this.scanner = scanner;
        this.applianceView = new ApplianceView();
    }

    public void showMenu(HouseholdID householdID) {
        applianceView.showMenu();
        String choice = scanner.nextLine();

        switch (choice) {
            case "1": viewAppliances(householdID); break;
            case "2": addAppliance(householdID); break;
            case "3": removeAppliance(householdID); break;
            case "0": return;
            default:
                applianceView.showInvalidChoice();
        }
        showMenu(householdID); // loop back
    }

    public void viewAppliances(HouseholdID householdID) {
        ArrayList<ApplianceEntity> appliances = applianceService.getByHousehold(householdID);

        if (appliances.isEmpty()) {
            applianceView.showNoAppliances();
            return;
        }
        applianceView.showAppliances(appliances);
    }

    public void addAppliance(HouseholdID householdID) {
        applianceView.showAddApplianceNamePrompt();
        String name = scanner.nextLine();

        applianceView.showAddAppliancePowerPrompt();
        double watts = parseDouble(scanner.nextLine());

        applianceView.showAddApplianceUsagePrompt();
        double hours = parseDouble(scanner.nextLine());

        if (watts < 0 || hours < 0) {
            applianceView.showInvalidInput();
            return;
        }

        ApplianceEntity appliance = householdService.addAppliance(
                householdID, name, new Hours(hours), new Watts(watts)
        );
        applianceView.showAddApplianceSuccess(appliance);
    }

    public void removeAppliance(HouseholdID householdID) {
        ArrayList<ApplianceEntity> appliances = applianceService.getByHousehold(householdID);

        if (appliances.isEmpty()) {
            applianceView.showNoAppliances();
            return;
        }

        applianceView.showAppliances(appliances);
        applianceView.showRemoveAppliancePrompt();
        String input = scanner.nextLine();

        int index = Integer.parseInt(input) - 1;
        if (index < 0 || index >= appliances.size()) {
            applianceView.showInvalidChoice();
            return;
        }
        ApplianceEntity appliance = appliances.get(index);
        householdService.removeAppliance(householdID, appliance.getID());
        applianceView.showRemoveApplianceSuccess(appliance);
    }

    // --- Helper ---
    private double parseDouble(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
