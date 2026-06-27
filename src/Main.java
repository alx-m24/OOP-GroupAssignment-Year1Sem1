import java.util.Scanner;

import Cost.View.CostView;
import Household.Controller.HouseholdController;

import Household.View.Householdview;
import APPLIANCE.VIEW.ApplianceView;
import Energy.View.EnergyView;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // SHARED controller
        HouseholdController sharedController = new HouseholdController();

        //  PASS SAME controller to ALL views
        Householdview householdView = new Householdview(sharedController);
        ApplianceView applianceView = new ApplianceView(sharedController);
        EnergyView energyView = new EnergyView(sharedController);
        CostView costView = new CostView();

        int choice;

        do {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. MANAGE Household ");
            System.out.println("2. MANAGE Appliance ");
            System.out.println("3. Energy Analysis ");
            System.out.println("4. Cost Module");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    householdView.showMenu();
                    break;
                case 2:
                    applianceView.showMenu();
                    break;
                case 3:
                    energyView.showMenu();
                    break;
                case 4:
                    costView.showMenu();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        scanner.close();
    }
}
