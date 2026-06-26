package APPLIANCE.VIEW;

import java.util.List;
import java.util.Scanner;

import APPLIANCE.CONTROLLER.ApplianceController;
import APPLIANCE.ENTITY.Appliance;
import APPLIANCE.ENTITY.CoolingAppliances;
import APPLIANCE.ENTITY.HeatingAppliance;
import APPLIANCE.ENTITY.LightAppliance;
import  APPLIANCE.Utility.ApplianceUtility;



public class ApplianceView {

    private ApplianceController controller;
    private Scanner scanner;

    public ApplianceView() {
        controller = new ApplianceController();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int choice;

        do {
            System.out.println("\n=== Appliance Menu ===");
            System.out.println("1. Add Appliance");
            System.out.println("2. View All Appliances");
            System.out.println("3. Search Appliance by Name");
            System.out.println("4. Update Appliance");
            System.out.println("5. Delete Appliance");
            System.out.println("6. Search by Type");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    addAppliance();
                    break;
                case 2:
                    displayAll();
                    break;
                case 3:
                    searchByName();
                    break;
                case 4:
                    updateAppliance();
                    break;
                case 5:
                    deleteAppliance();
                    break;
                case 6:
                    searchByType();
                    break;
            }

        } while (choice != 0);
    }

    // Add appliance
    private void addAppliance() {
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

        if (type.equalsIgnoreCase(ApplianceUtility.HEATING)) {
            System.out.print("Enter standby loss: ");
            double loss = scanner.nextDouble();
            scanner.nextLine();
            appliance = new HeatingAppliance(name, power, loss);

        }
        else if (type.equalsIgnoreCase(ApplianceUtility.COOLING)) {
            System.out.print("Enter efficiency rating: ");
            double eff = scanner.nextDouble();
            scanner.nextLine();
            appliance = new CoolingAppliances(name, power, eff);
        }
        else if (type.equalsIgnoreCase(ApplianceUtility.LIGHTING)) {
            System.out.print("Enter quantity: ");
            int qty = scanner.nextInt();
            scanner.nextLine();
            appliance = new LightAppliance(name, power, qty);
        }

        if (appliance != null) {
            appliance.setUsageDuration(duration);

            boolean success = controller.addAppliance(appliance);

            if (success)
                System.out.println("Appliance added successfully!");
        }
    }

    //  View all
    private void displayAll() {
        List<Appliance> list = controller.getAllAppliances();

        for (Appliance app : list) {
            System.out.println(app.getName() +
                    " | Energy: " + app.calculateEnergyConsumption());
        }
    }

    // Search by name
    private void searchByName() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        Appliance app = controller.getAppliance(name);

        if (app != null)
            System.out.println("Found: " + app.getName());
        else
            System.out.println("Not found");
    }

    // Update
    private void updateAppliance() {
        System.out.print("Enter current name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();

        System.out.print("Enter new power rating: ");
        double power = scanner.nextDouble();

        System.out.print("Enter new duration: ");
        double duration = scanner.nextDouble();
        scanner.nextLine();

        boolean success = controller.updateAppliance(name, newName, power, duration);

        if (success)
            System.out.println("Updated successfully!");
        else
            System.out.println("Update failed!");
    }

    // Delete
    private void deleteAppliance() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        boolean success = controller.deleteAppliance(name);

        if (success)
            System.out.println("Deleted successfully!");
        else
            System.out.println("Delete failed!");
    }

    // Search by type
    private void searchByType() {
        System.out.print("Enter type (heating/cooling/lighting): ");
        String type = scanner.nextLine();

        List<Appliance> list = controller.getAppliancesByType(type);

        if (list != null && !list.isEmpty()) {
            for (Appliance app : list) {
                System.out.println(app.getName() +
                        " | Energy: " + app.calculateEnergyConsumption());
            }
        } else {
            System.out.println("No appliances found.");
        }
    }


}
