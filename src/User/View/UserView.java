package User.View;

import User.Entity.UserEntity;
import Household.Entity.HouseholdEntity;

import java.util.ArrayList;
import java.util.Scanner;

public class UserView {
    private Scanner scanner = new Scanner(System.in);

    // --- Menu ---

    public int showMenu() {
        System.out.println("\n=============================");
        System.out.println("        USER MANAGEMENT      ");
        System.out.println("=============================");
        System.out.println(" 1. Add User");
        System.out.println(" 2. Find User by ID");
        System.out.println(" 3. View All Users");
        System.out.println(" 4. Update User");
        System.out.println(" 5. Delete User");
        System.out.println(" 6. Add Household to User");
        System.out.println(" 7. Remove Household from User");
        System.out.println(" 8. Back to Main Menu");
        System.out.println("=============================");
        System.out.print("Enter choice: ");
        return promptInt();
    }

    public int showUpdateMenu() {
        System.out.println("\n--- Update Options ---");
        System.out.println(" 1. Update Name");
        System.out.println(" 2. Update Password");
        System.out.print("Enter choice: ");
        return promptInt();
    }

    // --- Prompts ---

    public String promptInput(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    // --- Display ---

    public void displayUser(UserEntity user) {
        System.out.println("\n-----------------------------");
        System.out.println("         USER DETAILS        ");
        System.out.println("-----------------------------");
        System.out.println("Name     : " + user.getName());
        System.out.println("Password : " + maskPassword(user.getPassword()));
        System.out.println("Households: " + user.getHouseholdCount());

        ArrayList<HouseholdEntity> households = user.getHouseholds().getAllHouseholds();
        if (!households.isEmpty()) {
            System.out.println("\n  Linked Households:");
            for (HouseholdEntity h : households) {
                System.out.println("  - [" + h.getHouseholdId() + "] " + h.getName());
            }
        }
        System.out.println("-----------------------------");
    }

    public void displayAllUsers(ArrayList<UserEntity> users) {
        System.out.println("\n-----------------------------");
        System.out.println("         ALL USERS           ");
        System.out.println("-----------------------------");
        for (int i = 0; i < users.size(); i++) {
            UserEntity user = users.get(i);
            System.out.println((i + 1) + ". [" + user.getName() + "] "
                    + " | Households: " + user.getHouseholdCount());
        }
        System.out.println("-----------------------------");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    // --- Private Helpers ---

    private int promptInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String maskPassword(String password) {
        if (password == null || password.isEmpty()) {
            return "";
        }
        String masked = "";
        for (int i = 0; i < password.length(); ++i) {
            masked += "*";
        }
        return masked;
    }
}