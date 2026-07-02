package Appliance.View;

import Appliance.Entity.ApplianceEntity;
import Appliance.Entity.CoolingAppliance;
import Appliance.Entity.HeatingAppliance;
import Appliance.Entity.LightingAppliance;

import java.util.ArrayList;

public class ApplianceView {
    public void showMenu() {
        System.out.println("\n=== Appliance Menu ===");
        System.out.println("1. View appliances");
        System.out.println("2. Add appliance");
        System.out.println("3. Remove appliance");
        System.out.println("0. Back");
        System.out.print("Choice: ");
    }

    public void showApplianceTypeMenu() {
        System.out.println("\n=== Appliance Type ===");
        System.out.println("1. Lighting");
        System.out.println("2. Cooling");
        System.out.println("3. Heating");
        System.out.println("4. Generic");
        System.out.print("Choice: ");
    }

    public void showAppliances(ArrayList<ApplianceEntity> appliances) {
        System.out.println("\n=== Appliances ===");
        for (int i = 0; i < appliances.size(); i++) {
            ApplianceEntity a = appliances.get(i);
            System.out.println((i + 1) + ". " + a);
            System.out.printf("     Energy used: %.4f kWh/day%n", a.getEnergyUsed().getValue());
        }
    }

    public void showNoAppliances() {
        System.out.println("No appliances found for this household.");
    }

    // --- Shared prompts ---
    public void showAddApplianceNamePrompt()  { System.out.print("Appliance name: "); }
    public void showAddAppliancePowerPrompt() { System.out.print("Power rating (Watts): "); }
    public void showAddApplianceUsagePrompt() { System.out.print("Daily usage (Hours): "); }

    // --- Lighting prompts ---
    public void showLightCountPrompt()  { System.out.print("Number of lights: "); }
    public void showEfficiencyPrompt()  { System.out.print("Efficiency rating (0.0 - 1.0): "); }

    // --- Cooling prompts ---
    public void showStandbyPowerPrompt() { System.out.print("Standby power (Watts): "); }
    public void showStandbyHoursPrompt() { System.out.print("Standby hours per day: "); }

    // --- Heating prompts ---
    public void showHeatingEfficiencyPrompt() { System.out.print("Heating efficiency (0.0 - 1.0): "); }
    public void showInsulationFactorPrompt()  { System.out.print("Insulation factor (0.0 - 1.0): "); }

    // --- Results ---
    public void showAddApplianceSuccess(ApplianceEntity appliance) {
        System.out.println("\"" + appliance.getApplianceName() + "\" added successfully.");
    }

    public void showRemoveAppliancePrompt() { System.out.print("Select appliance to remove: "); }

    public void showRemoveApplianceSuccess(ApplianceEntity appliance) {
        System.out.println("\"" + appliance.getApplianceName() + "\" removed.");
    }

    public void showInvalidChoice() { System.out.println("Invalid choice. Please try again."); }
    public void showInvalidInput()  { System.out.println("Invalid input. Values must be positive numbers."); }
}