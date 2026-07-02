package Appliance.View;

import Appliance.Entity.ApplianceEntity;

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

    public void showAppliances(ArrayList<ApplianceEntity> appliances) {
        System.out.println("\n=== Appliances ===");
        for (int i = 0; i < appliances.size(); i++) {
            System.out.println((i + 1) + ". " + appliances.get(i));
        }
    }

    public void showNoAppliances() {
        System.out.println("No appliances found for this household.");
    }

    public void showAddApplianceNamePrompt() {
        System.out.print("Appliance name: ");
    }

    public void showAddAppliancePowerPrompt() {
        System.out.print("Power rating (Watts): ");
    }

    public void showAddApplianceUsagePrompt() {
        System.out.print("Daily usage (Hours): ");
    }

    public void showAddApplianceSuccess(ApplianceEntity appliance) {
        System.out.println("Appliance \"" + appliance.getApplianceName() + "\" added successfully.");
    }

    public void showRemoveAppliancePrompt() {
        System.out.print("Select appliance to remove: ");
    }

    public void showRemoveApplianceSuccess(ApplianceEntity appliance) {
        System.out.println("Appliance \"" + appliance.getApplianceName() + "\" removed.");
    }

    public void showInvalidChoice() {
        System.out.println("Invalid choice. Please try again.");
    }

    public void showInvalidInput() {
        System.out.println("Invalid input. Values must be positive numbers.");
    }
}