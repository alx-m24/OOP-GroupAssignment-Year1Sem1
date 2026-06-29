package User.Controller;

import User.Entity.UserEntity;
import User.Service.UserService;
import Household.Controller.HouseholdController;
import Household.View.Householdview;
import APPLIANCE.VIEW.ApplianceView;
import Cost.View.CostView;

import java.util.ArrayList;
import java.util.Scanner;

public class UserController {

    private UserService userService;
    private Scanner scanner;

    public UserController() {
        this.userService = new UserService();
        this.scanner     = new Scanner(System.in);
    }

    // Returns null on logout
    public UserEntity run(UserEntity user, HouseholdController householdController, CostView costView) {

        Householdview householdView = new Householdview(householdController);
        ApplianceView applianceView = new ApplianceView(householdController);
        CostView cost               = costView;

        int choice;
        do {
            System.out.println("\n=============================");
            System.out.println("  Hello, " + user.getName());
            System.out.println("=============================");
            System.out.println(" 1. Manage My Account");
            System.out.println(" 2. Manage Households");
            System.out.println(" 3. Manage Appliances");
            System.out.println(" 4. Cost Estimation");
            System.out.println(" 5. Logout");
            System.out.println("=============================");
            System.out.print("Enter choice: ");
            choice = promptInt();

            switch (choice) {
                case 1: handleManageAccount(user); break;
                case 2: householdView.showMenu();  break;
                case 3: applianceView.showMenu();  break;
                case 4: cost.showMenu();           break;
                case 5: System.out.println("Logging out..."); break;
                default: System.out.println("Invalid option.");
            }
        } while (choice != 5);

        return null; // signals logout to Main
    }


    // --- USER CRUD (called from Main or internally) ---

    public boolean addUser(String name, String password) {
        UserEntity user = new UserEntity(name, password);
        return userService.addUser(user);
    }

    public UserEntity getUser(String userName) {
        return userService.getUser(userName);
    }

    public ArrayList<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    public boolean updateUserName(String userId, String newName) {
        return userService.updateUserName(userId, newName);
    }

    public boolean updateUserPassword(String userId, String newPassword) {
        return userService.updateUserPassword(userId, newPassword);
    }

    public boolean deleteUser(String userId) {
        return userService.deleteUser(userId);
    }


    // --- ACCOUNT MANAGEMENT (in-session) ---

    private void handleManageAccount(UserEntity user) {
        int choice;
        do {
            System.out.println("\n--- My Account ---");
            System.out.println(" 1. View Profile");
            System.out.println(" 2. Update Name");
            System.out.println(" 3. Update Password");
            System.out.println(" 0. Back");
            System.out.print("Enter choice: ");
            choice = promptInt();

            switch (choice) {
                case 1:
                    System.out.println("Name    : " + user.getName());
                    System.out.println("Households: " + user.getHouseholdCount());
                    break;
                case 2:
                    System.out.print("Enter new name: ");
                    String newName  = scanner.nextLine().trim();
                    boolean nameOk  = userService.updateUserName(user.getName(), newName);
                    System.out.println(nameOk ? "Name updated." : "Failed. Invalid name.");
                    break;
                case 3:
                    System.out.print("Enter new password (min 6 chars): ");
                    String newPass  = scanner.nextLine().trim();
                    boolean passOk  = userService.updateUserPassword(user.getName(), newPass);
                    System.out.println(passOk ? "Password updated." : "Failed. Must be at least 6 chars.");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 0);
    }


    // --- PRIVATE HELPERS ---

    private int promptInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}