import java.util.Scanner;
import Appliances.*;
import EnergyReport.*;
import HouseHold.HouseHold;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HouseHold houseHold = new HouseHold();


        int choice;

        do {
            System.out.println("\n===== Household Energy Tracker =====");
            System.out.println("1. Add Light Appliance");
            System.out.println("2. Add Cooling Appliance");
            System.out.println("3. Add Heating Appliance");
            System.out.println("4. Remove Appliance");
            System.out.println("5. View Total Energy");
            System.out.println("6. View Total Cost");
            System.out.println("7. View High Consumers");
            System.out.println("8. Exit");

            choice = getInt(scanner, "Enter choice: ");

            switch (choice) {
                case 1:
                    addLight(scanner, houseHold);
                    break;

                case 2:
                    addCooling(scanner, houseHold);
                    break;

                case 3:
                    addHeating(scanner, houseHold);
                    break;

                case 4:
                    removeAppliance(scanner, houseHold);
                    break;

                case 5:
                    System.out.printf("Total energy: %.2f kWh%n",
                            houseHold.calculateTotalEnergy());
                    break;

                case 6:
                    double rate = getDouble(scanner, "Enter tariff rate per kWh: ");
                    System.out.printf("Estimated cost: RM %.2f%n",
                            houseHold.calculateTotalCost(rate));
                    break;

                case 7:
                    HighConsumption highConsumption=new HighConsumption(houseHold);
                    EnergyReport report = new EnergyReport(highConsumption);
                    report.DisplayReport();


                    break;
                case 8:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 8);

        scanner.close();
    }

    private static void addLight(Scanner scanner, HouseHold houseHold) {
        scanner.nextLine();

        String name = getText(scanner, "Enter light name: ");
        double watts = getDouble(scanner, "Enter power rating in watts: ");
        int quantity = getInt(scanner, "Enter quantity: ");
        double hours = getDouble(scanner, "Enter usage hours: ");

        LightAppliance light = new LightAppliance(name, watts, quantity);
        light.setUsageDuration(hours);

        if (houseHold.addAppliance(light)) {
            System.out.println("Light appliance added.");
        } else {
            System.out.println("Failed to add appliance.");
        }
    }

    private static void addCooling(Scanner scanner, HouseHold houseHold) {
        scanner.nextLine();

        String name = getText(scanner, "Enter cooling appliance name: ");
        double watts = getDouble(scanner, "Enter power rating in watts: ");
        double efficiency = getDouble(scanner, "Enter efficiency rating: ");
        double hours = getDouble(scanner, "Enter usage hours: ");

        CoolingAppliance cooling = new CoolingAppliance(name, watts, efficiency);
        cooling.setUsageDuration(hours);

        if (houseHold.addAppliance(cooling)) {
            System.out.println("Cooling appliance added.");
        } else {
            System.out.println("Failed to add appliance.");
        }
    }

    private static void addHeating(Scanner scanner, HouseHold houseHold) {
        scanner.nextLine();

        String name = getText(scanner, "Enter heating appliance name: ");
        double watts = getDouble(scanner, "Enter power rating in watts: ");
        double standbyLoss = getDouble(scanner, "Enter standby loss in watts: ");
        double hours = getDouble(scanner, "Enter usage hours: ");

        HeatingAppliance heating = new HeatingAppliance(name, watts, standbyLoss);
        heating.setUsageDuration(hours);

        if (houseHold.addAppliance(heating)) {
            System.out.println("Heating appliance added.");
        } else {
            System.out.println("Failed to add appliance.");
        }
    }

    private static void removeAppliance(Scanner scanner, HouseHold houseHold) {
        scanner.nextLine();

        String name = getText(scanner, "Enter appliance name to remove: ");

        if (houseHold.hasAppliance(name)) {
            houseHold.removeAppliance(name);
            System.out.println("Appliance removed.");
        } else {
            System.out.println("Appliance not found.");
        }
    }

    private static String getText(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private static int getInt(Scanner scanner, String message) {
        System.out.print(message);

        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid whole number.");
            scanner.next();
            System.out.print(message);
        }

        return scanner.nextInt();
    }

    private static double getDouble(Scanner scanner, String message) {
        System.out.print(message);

        while (!scanner.hasNextDouble()) {
            System.out.println("Please enter a valid number.");
            scanner.next();
            System.out.print(message);
        }

        return scanner.nextDouble();
    }
}