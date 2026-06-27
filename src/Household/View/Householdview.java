package Household.View;
import java.util.List;
import java.util.Scanner;

import APPLIANCE.CONTROLLER.ApplianceController;
import Household.Controller.HouseholdController;
import Household.Entity.HouseholdEntity;


    public class Householdview {

        private HouseholdController controller;
        private Scanner scanner;
        private ApplianceController appController;

        public Householdview() {
            controller = new HouseholdController();
            scanner = new Scanner(System.in);
        }

        public void showMenu() {

            int choice;

            do {
                System.out.println("\n=== Household Menu ===");
                System.out.println("1. Add Household");
                System.out.println("2. View All Households");
                System.out.println("3. Search Household by ID");
                System.out.println("4. Update Household");
                System.out.println("5. Delete Household");
                System.out.println("0. Exit");

                System.out.print("Enter choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                            case 1:
                                addHousehold();
                                break;

                            case 2:
                                displayAllHouseholds();
                                break;

                            case 3:
                                searchHousehold();
                                break;

                            case 4:
                                updateHousehold();
                                break;

                                case 5:
                                    deleteHousehold();
                                    break;
                                        default:

                                System.out.println("Invalid choice!");
                                break;
                }

            } while (choice != 0);
        }

        // ✅ ADD household
        private void addHousehold() {

            System.out.print("Enter Household ID: ");
            String id = scanner.nextLine();

            System.out.print("Enter Household Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Username: ");
            String username = scanner.nextLine();

            boolean success = controller.addHousehold(id, name, username);

            if (success) {
                System.out.println("✅ Household added successfully!");
            } else {
                System.out.println("❌ Failed (duplicate ID or invalid input)");
            }
        }

        // ✅ VIEW ALL households
        private void displayAllHouseholds() {

            List<HouseholdEntity> list = controller.getAllHouseholds();

            if (list.isEmpty()) {
                System.out.println("No households found.");
                return;
            }

            for (HouseholdEntity house : list) {
                System.out.println("ID: " + house.getHouseholdId()
                        + " | Name: " + house.getName()
                        + " | Username: " + house.getUsername()
                        + " | Appliances: " + house.getAppliances().size());
            }
        }

        // ✅ SEARCH household
        private void searchHousehold() {

            System.out.print("Enter Household ID: ");
            String id = scanner.nextLine();

            HouseholdEntity house = controller.getHousehold(id);

            if (house != null) {
                System.out.println("✅ Found:");
                System.out.println("ID: " + house.getHouseholdId());
                System.out.println("Name: " + house.getName());
                System.out.println("Username: " + house.getUsername());
                System.out.println("Total Energy: " +
                        house.getTotalEnergyConsumption());
            } else {
                System.out.println("Household not found.");
            }
        }

        // ✅ UPDATE household
        private void updateHousehold() {

            System.out.print("Enter Household ID: ");
            String id = scanner.nextLine();

            System.out.print("Enter new Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter new Username: ");
            String username = scanner.nextLine();

            boolean success = controller.updateHousehold(id, name, username);

            if (success) {
                System.out.println("✅ Household updated successfully!");
            } else {
                System.out.println("Update failed (not found).");
            }
        }

        // ✅ DELETE household
        private void deleteHousehold() {

            System.out.print("Enter Household ID: ");
            String id = scanner.nextLine();

            boolean success = controller.deleteHousehold(id);

            if (success) {
                System.out.println("✅ Household deleted successfully!");
            } else {
                System.out.println("Delete failed (not found).");
            }
        }
    }

