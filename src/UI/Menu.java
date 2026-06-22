package UI;

import Appliances.*;
import EnergyReport.HighConsumption;
import HouseHold.HouseHold;

public class Menu {
    private final HouseHold houseHold;
    private final InputHelper input;

    public Menu(HouseHold houseHold, InputHelper input) {
        this.houseHold = houseHold;
        this.input = input;
    }

    public void show() {
        System.out.println("\n===== Household Energy Tracker =====");
        System.out.println("1. Add Default Appliance");
        System.out.println("2. Add Light Appliance");
        System.out.println("3. Add Cooling Appliance");
        System.out.println("4. Add Heating Appliance");
        System.out.println("5. Remove Appliance");
        System.out.println("6. View Total Energy");
        System.out.println("7. View Total Cost");
        System.out.println("8. Analyze energy consumption");
        System.out.println("9. Exit");
    }

    public void handleChoice(int choice) {
        switch (choice) {
            case 1:
                addOtherAppliance();
                break;
            case 2:
                addLight();
                break;
            case 3:
                addCooling();
                break;
            case 4:
                addHeating();
                break;
            case 5:
                removeAppliance();
                break;
            case 6:
                System.out.printf("Total energy: %.2f kWh%n", houseHold.calculateTotalEnergy());
                break;
            case 7:
                double rate = input.getDouble("Enter tariff rate per kWh: ");
                System.out.printf("Estimated cost: RM %.2f%n", houseHold.calculateTotalCost(rate));
                break;
            case 8:
                displayHighConsumption();
                break;
            case 9:
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void addOtherAppliance() {
        input.getText(""); // flush newline
        String name   = input.getText("Enter appliance name: ");
        double watts  = input.getDouble("Enter power rating in watts: ");
        double hours  = input.getDouble("Enter usage hours: ");

        Appliance appliance = new Appliance(name, watts);
        appliance.setUsageDuration(hours);
        System.out.println(houseHold.addAppliance(appliance) ? "Appliance added." : "Failed to add appliance.");
    }

    private void addLight() {
        input.getText(""); // flush newline
        String name   = input.getText("Enter light name: ");
        double watts  = input.getDouble("Enter power rating in watts: ");
        int quantity  = input.getInt("Enter quantity: ");
        double hours  = input.getDouble("Enter usage hours: ");

        LightAppliance light = new LightAppliance(name, watts, quantity);
        light.setUsageDuration(hours);
        System.out.println(houseHold.addAppliance(light) ? "Light appliance added." : "Failed to add appliance.");
    }

    private void addCooling() {
        input.getText("");
        String name       = input.getText("Enter cooling appliance name: ");
        double watts      = input.getDouble("Enter power rating in watts: ");
        double efficiency = input.getDouble("Enter efficiency rating: ");
        double hours      = input.getDouble("Enter usage hours: ");

        CoolingAppliance cooling = new CoolingAppliance(name, watts, efficiency);
        cooling.setUsageDuration(hours);
        System.out.println(houseHold.addAppliance(cooling) ? "Cooling appliance added." : "Failed to add appliance.");
    }

    private void addHeating() {
        input.getText("");
        String name       = input.getText("Enter heating appliance name: ");
        double watts      = input.getDouble("Enter power rating in watts: ");
        double standbyLoss = input.getDouble("Enter standby loss in watts: ");
        double hours      = input.getDouble("Enter usage hours: ");

        HeatingAppliance heating = new HeatingAppliance(name, watts, standbyLoss);
        heating.setUsageDuration(hours);
        System.out.println(houseHold.addAppliance(heating) ? "Heating appliance added." : "Failed to add appliance.");
    }

    private void removeAppliance() {
        input.getText("");
        String name = input.getText("Enter appliance name to remove: ");
        if (houseHold.hasAppliance(name)) {
            houseHold.removeAppliance(name);
            System.out.println("Appliance removed.");
        } else {
            System.out.println("Appliance not found.");
        }
    }

    private void displayHighConsumption() {
        Appliance[] appliances = new HighConsumption(houseHold).QueryArray();
        System.out.println("\n===== High Consumption Appliances =====");
        for (int i = 0; i < appliances.length; i++) {
            System.out.println((i + 1) + ". " + appliances[i].getName()
                    + " Total Consumption: " + appliances[i].calculateEnergyConsumption());
        }
    }
}