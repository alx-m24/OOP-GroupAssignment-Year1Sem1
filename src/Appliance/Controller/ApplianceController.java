package Appliance.Controller;

import Appliance.Entity.ApplianceEntity;
import Appliance.Entity.CoolingAppliance;
import Appliance.Entity.HeatingAppliance;
import Appliance.Entity.LightingAppliance;
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
        showMenu(householdID);
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
        applianceView.showApplianceTypeMenu();
        String typeChoice = scanner.nextLine();

        switch (typeChoice) {
            case "1": addLighting(householdID); break;
            case "2": addCooling(householdID); break;
            case "3": addHeating(householdID); break;
            case "4": addGeneric(householdID); break;
            default:
                applianceView.showInvalidChoice();
        }
    }

    private void addGeneric(HouseholdID householdID) {
        String name = promptName();
        double watts = promptWatts();
        double hours = promptHours();
        if (watts < 0 || hours < 0) { applianceView.showInvalidInput(); return; }

        ApplianceEntity a = householdService.addAppliance(householdID, name, new Hours(hours), new Watts(watts));
        applianceView.showAddApplianceSuccess(a);
    }

    private void addLighting(HouseholdID householdID) {
        String name = promptName();
        double watts = promptWatts();
        double hours = promptHours();

        applianceView.showLightCountPrompt();
        int lightCount = parseInt(scanner.nextLine());

        applianceView.showEfficiencyPrompt();
        double efficiency = parseDouble(scanner.nextLine());

        if (watts < 0 || hours < 0 || lightCount < 0 || efficiency < 0) {
            applianceView.showInvalidInput();
            return;
        }

        LightingAppliance a = new LightingAppliance(
                householdID, name, new Hours(hours), new Watts(watts), lightCount, efficiency
        );
        householdService.addExistingAppliance(householdID, a);
        applianceView.showAddApplianceSuccess(a);
    }

    private void addCooling(HouseholdID householdID) {
        String name = promptName();
        double watts = promptWatts();
        double hours = promptHours();

        applianceView.showStandbyPowerPrompt();
        double standbyWatts = parseDouble(scanner.nextLine());

        applianceView.showStandbyHoursPrompt();
        double standbyHours = parseDouble(scanner.nextLine());

        if (watts < 0 || hours < 0 || standbyWatts < 0 || standbyHours < 0) {
            applianceView.showInvalidInput();
            return;
        }

        CoolingAppliance a = new CoolingAppliance(
                householdID, name, new Hours(hours), new Watts(watts),
                new Watts(standbyWatts), new Hours(standbyHours)
        );
        householdService.addExistingAppliance(householdID, a);
        applianceView.showAddApplianceSuccess(a);
    }

    private void addHeating(HouseholdID householdID) {
        String name = promptName();
        double watts = promptWatts();
        double hours = promptHours();

        applianceView.showHeatingEfficiencyPrompt();
        double heatingEfficiency = parseDouble(scanner.nextLine());

        applianceView.showInsulationFactorPrompt();
        double insulationFactor = parseDouble(scanner.nextLine());

        if (watts < 0 || hours < 0 || heatingEfficiency < 0 || insulationFactor < 0) {
            applianceView.showInvalidInput();
            return;
        }

        HeatingAppliance a = new HeatingAppliance(
                householdID, name, new Hours(hours), new Watts(watts),
                heatingEfficiency, insulationFactor
        );
        householdService.addExistingAppliance(householdID, a);
        applianceView.showAddApplianceSuccess(a);
    }

    public void removeAppliance(HouseholdID householdID) {
        ArrayList<ApplianceEntity> appliances = applianceService.getByHousehold(householdID);
        if (appliances.isEmpty()) {
            applianceView.showNoAppliances();
            return;
        }

        applianceView.showAppliances(appliances);
        applianceView.showRemoveAppliancePrompt();

        int index = parseInt(scanner.nextLine()) - 1;
        if (index < 0 || index >= appliances.size()) {
            applianceView.showInvalidChoice();
            return;
        }

        ApplianceEntity appliance = appliances.get(index);
        householdService.removeAppliance(householdID, appliance.getID());
        applianceView.showRemoveApplianceSuccess(appliance);
    }

    // --- Shared prompts ---
    private String promptName() {
        applianceView.showAddApplianceNamePrompt();
        return scanner.nextLine();
    }

    private double promptWatts() {
        applianceView.showAddAppliancePowerPrompt();
        return parseDouble(scanner.nextLine());
    }

    private double promptHours() {
        applianceView.showAddApplianceUsagePrompt();
        return parseDouble(scanner.nextLine());
    }

    // --- Helpers ---
    private double parseDouble(String input) {
        try { return Double.parseDouble(input); }
        catch (NumberFormatException e) { return -1; }
    }

    private int parseInt(String input) {
        try { return Integer.parseInt(input); }
        catch (NumberFormatException e) { return -1; }
    }
}