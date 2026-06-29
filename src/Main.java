import java.util.Scanner;

import Cost.View.CostView;
import Household.Controller.HouseholdController;

import Household.View.Householdview;
import APPLIANCE.VIEW.ApplianceView;
import User.Controller.UserController;
import User.Entity.UserEntity;
import Household.Controller.HouseholdController;
import Household.View.Householdview;
import APPLIANCE.VIEW.ApplianceView;
import Cost.View.CostView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Shared controllers
        UserController userController         = new UserController();
        HouseholdController householdController = new HouseholdController();
        CostView costView                     = new CostView();

        UserEntity loggedInUser = null;
        boolean appRunning      = true;

        while (appRunning) {

            // --- Login / Register loop ---
            while (loggedInUser == null) {
                System.out.println("\n=============================");
                System.out.println("         WELCOME             ");
                System.out.println("=============================");
                System.out.println(" 1. Login");
                System.out.println(" 2. Register");
                System.out.println(" 0. Exit App");
                System.out.println("=============================");
                System.out.print("Enter choice: ");

                int choice;
                try {
                    choice = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    choice = -1;
                }

                switch (choice) {
                    case 1:
                        System.out.print("Enter user name: ");
                        String loginName = scanner.nextLine().trim();
                        loggedInUser   = userController.getUser(loginName);
                        if (loggedInUser == null) {
                            System.out.println("User not found. Try again.");
                        } else {
                            System.out.println("Welcome back, " + loggedInUser.getName() + "!");
                        }
                        break;
                    case 2:
                        System.out.print("Enter name: ");
                        String newName = scanner.nextLine().trim();
                        System.out.print("Enter password (min 6 chars): ");
                        String newPass = scanner.nextLine().trim();
                        boolean success = userController.addUser(newName, newPass);
                        if (success) {
                            System.out.println("Registered! Please login.");
                        } else {
                            System.out.println("Failed. ID may already exist or invalid input.");
                        }
                        break;
                    case 0:
                        appRunning = false;
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option.");
                }

                // Exit app from login screen
                if (!appRunning) break;
            }

            // --- User session loop ---
            if (loggedInUser != null) {
                loggedInUser = userController.run(
                        loggedInUser,
                        householdController,
                        costView
                );
                // run() returns null on logout, a different user on switch
            }
        }

        scanner.close();
    }
}