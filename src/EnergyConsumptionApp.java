import Appliance.Controller.ApplianceController;
import Appliance.Service.ApplianceService;
import CostRegion.Controller.CostRegionController;
import CostRegion.Service.CostRegionService;
import Household.Controller.HouseholdController;
import Household.Service.HouseholdService;
import Report.Controller.ReportController;
import Report.Service.ReportService;
import User.Controller.UserController;
import User.Entity.UserEntity;
import User.Service.UserService;
import Utils.DataManager.DataManager;
import Utils.ID.Base.ID;
import Utils.ID.HouseholdID;

import java.util.Scanner;

public class EnergyConsumptionApp {
    public static void main(String[] args) {
        // --- Scanner (one instance shared across all controllers) ---
        Scanner scanner = new Scanner(System.in);

        // --- Services (instantiate in dependency order) ---
        UserService userService = new UserService();
        CostRegionService costRegionService = new CostRegionService();
        ApplianceService applianceService = new ApplianceService();
        ReportService reportService = new ReportService(applianceService, costRegionService);
        HouseholdService householdService = new HouseholdService(applianceService, reportService);

        // --- Controllers ---
        UserController userController = new UserController(userService, scanner);
        CostRegionController costRegionController = new CostRegionController(costRegionService, scanner);
        HouseholdController householdController = new HouseholdController(householdService, scanner);
        ApplianceController applianceController = new ApplianceController(applianceService, householdService, scanner);
        ReportController reportController = new ReportController(householdService, scanner);

        DataManager dataManager = new DataManager(new ID(), householdService, applianceService, userService);
        dataManager.loadAll();

        // --- Session ---
        UserEntity currentUser = null;

        // --- Main loop ---
        boolean running = true;
        while (running) {
            if (currentUser == null) {
                currentUser = userController.loginOrRegister();
                if (currentUser == null) {
                    running = false;
                    break;
                }
            }


            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Manage Households");
            System.out.println("2. Manage Appliances");
            System.out.println("3. View Report");
            System.out.println("0. Logout");
            System.out.print("Choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    // MainApp resolves region first, then passes it to HouseholdController
                    householdController.showMenu(
                            currentUser.getID(),
                            costRegionController.pickRegion().getID()
                    );
                    break;

                case "2":
                    // MainApp resolves household first, then passes it to ApplianceController
                    HouseholdID selectedForAppliance = householdController.selectHousehold(currentUser.getID());
                    if (selectedForAppliance != null) {
                        applianceController.showMenu(selectedForAppliance);
                    }
                    break;

                case "3":
                    // MainApp resolves household first, then passes it to ReportController
                    HouseholdID selectedForReport = householdController.selectHousehold(currentUser.getID());
                    if (selectedForReport != null) {
                        reportController.showMenu(selectedForReport);
                    }
                    break;

                case "0":
                    currentUser = null;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Goodbye!");

        scanner.close();

        dataManager.saveAll();
    }
}