package APPLIANCE.VIEW;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import APPLIANCE.ENTITY.Appliance;
import APPLIANCE.ENTITY.CoolingAppliances;
import APPLIANCE.ENTITY.HeatingAppliance;
import APPLIANCE.ENTITY.LightAppliance;

import Household.Controller.HouseholdController;
import Household.Entity.HouseholdEntity;

import APPLIANCE.Utility.ApplianceUtility;

public class ApplianceView {

    private HouseholdController controller;
    private Scanner scanner;



    public ApplianceView(HouseholdController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }


    public void showMenu() {
        int choice;

        do {
            System.out.println("\n=== Appliance Menu ===");
            System.out.println("1. Add Appliance");
            System.out.println("2. View Appliances (by Household)");
            System.out.println("3. Delete Appliance (by Household)");
            System.out.println("4. Search by Type (by Household)");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addAppliance();
                    break;
                case 2:
                    displayAppliances();
                    break;
                case 3:
                    deleteAppliance();
                    break;
                case 4:
                    searchByType();
                    break;
            }

        } while (choice != 0);
    }

    // ✅ ADD appliance
    private void addAppliance() {

        System.out.print("Enter Household ID: ");
        String householdId = scanner.nextLine();

        if (!ApplianceUtility.isValidString(householdId)) {
            System.out.println("Invalid household ID!");
            return;
        }

        HouseholdEntity household = controller.getHousehold(householdId);

        if (household == null) {
            System.out.println("Household not found!");
            return;
        }

        System.out.print("Enter type (heating/cooling/lighting): ");
        String type = scanner.nextLine();


        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter power rating: ");
        double power = scanner.nextDouble();

        System.out.print("Enter usage duration: ");
        double duration = scanner.nextDouble();
        scanner.nextLine();

        Appliance appliance = null;

        if (ApplianceUtility.HEATING.equalsIgnoreCase(type)) {
            System.out.print("Enter standby loss: ");
            double loss = scanner.nextDouble();
            scanner.nextLine();
            appliance = new HeatingAppliance(name, power, loss);

        } else if (ApplianceUtility.COOLING.equalsIgnoreCase(type)) {
            System.out.print("Enter efficiency rating: ");
            double eff = scanner.nextDouble();
            scanner.nextLine();
            appliance = new CoolingAppliances(name, power, eff);

        } else if (ApplianceUtility.LIGHTING.equalsIgnoreCase(type)) {
            System.out.print("Enter quantity: ");
            int qty = scanner.nextInt();
            scanner.nextLine();
            appliance = new LightAppliance(name, power, qty);

        } else {
            System.out.println("Invalid type!");
            return;
        }

        appliance.setUsageDuration(duration);

        household.addAppliance(appliance);

        System.out.println("Appliance added!");

    }
    private void displayAppliances() {

        System.out.print("Enter Household ID: ");
        String householdId = scanner.nextLine();

        HouseholdEntity household = controller.getHousehold(householdId);

        if (household == null) {
            System.out.println("Household not found!");
            return;
        }

        ArrayList<Appliance> list = household.getAppliances();

        if (list.isEmpty()) {
            System.out.println("No appliances found.");
            return;
        }

        for (Appliance app : list) {
            System.out.println(app.getName() +
                    " | Energy: " +
                  app.calculateEnergyConsumption());
        }
    }
    private void deleteAppliance() {

        System.out.print("Enter Household ID: ");
        String householdId = scanner.nextLine();

        HouseholdEntity household = controller.getHousehold(householdId);

        if (household == null) {
            System.out.println("Household not found!");
            return;
        }

        System.out.print("Enter appliance name: ");
        String name = scanner.nextLine();

        boolean removed = controller.removeApplianceFromHousehold(householdId, name);

        if (removed) {
            System.out.println("Appliance removed successfully!");
        } else {
            System.out.println("Appliance not found!");
        }
    }
    private void searchByType() {

        System.out.print("Enter Household ID: ");
        String householdId = scanner.nextLine();

        HouseholdEntity household = controller.getHousehold(householdId);

        if (household == null) {
            System.out.println("Household not found!");
            return;
        }

        System.out.print("Enter type (heating/cooling/lighting): ");
        String type = scanner.nextLine();

        ArrayList<Appliance> list = household.getAppliances();

        boolean found = false;

        for (Appliance app : list) {

            if (ApplianceUtility.HEATING.equalsIgnoreCase(type)
                    && app instanceof HeatingAppliance) {

                System.out.println(app.getName());
                found = true;

            } else if (ApplianceUtility.COOLING.equalsIgnoreCase(type)
                    && app instanceof CoolingAppliances) {

                System.out.println(app.getName());
                found = true;

            } else if (ApplianceUtility.LIGHTING.equalsIgnoreCase(type)
                    && app instanceof LightAppliance) {

                System.out.println(app.getName());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching appliances found.");
        }
    }
}
