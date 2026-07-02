package Household.Controller;

import Household.Entity.HouseholdEntity;
import Household.Service.HouseholdService;
import Household.View.HouseholdView;
import Utils.ID.CostRegionID;
import Utils.ID.HouseholdID;
import Utils.ID.UserID;

import java.util.ArrayList;
import java.util.Scanner;

public class HouseholdController {
    final private HouseholdService householdService;
    final private HouseholdView householdView;
    final private Scanner scanner;

    public HouseholdController(HouseholdService householdService, Scanner scanner) {
        this.householdService = householdService;
        this.scanner = scanner;
        this.householdView = new HouseholdView();
    }

    public void showMenu(UserID userID, CostRegionID costRegionID) {
        householdView.showMenu();
        String choice = scanner.nextLine();

        switch (choice) {
            case "1": viewHouseholds(userID); break;
            case "2": addHousehold(userID, costRegionID); break;
            case "3": removeHousehold(userID); break;
            case "0": return;
            default:
                householdView.showInvalidChoice();
        }
        showMenu(userID, costRegionID); // loop back
    }

    public void viewHouseholds(UserID userID) {
        ArrayList<HouseholdEntity> households = householdService.getByUser(userID);
        if (households.isEmpty()) {
            householdView.showNoHouseholds();
            return;
        }
        householdView.showHouseholds(households);
    }

    public void addHousehold(UserID userID, CostRegionID costRegionID) {
        householdView.showAddHouseholdPrompt();
        String name = scanner.nextLine();

        HouseholdEntity household = householdService.add(userID, name, costRegionID);
        householdView.showAddHouseholdSuccess(household);
    }

    public void removeHousehold(UserID userID) {
        ArrayList<HouseholdEntity> households = householdService.getByUser(userID);
        if (households.isEmpty()) {
            householdView.showNoHouseholds();
            return;
        }

        householdView.showHouseholds(households);
        householdView.showRemoveHouseholdPrompt();
        String input = scanner.nextLine();

        try {
            int index = Integer.parseInt(input) - 1;
            if (index < 0 || index >= households.size()) {
                householdView.showInvalidChoice();
                return;
            }
            HouseholdEntity household = households.get(index);
            householdService.remove(household.getID());
            householdView.showRemoveHouseholdSuccess(household);
        } catch (NumberFormatException e) {
            householdView.showInvalidChoice();
        }
    }

    // Returns selected HouseholdID for MainApp to pass downstream
    public HouseholdID selectHousehold(UserID userID) {
        ArrayList<HouseholdEntity> households = householdService.getByUser(userID);
        if (households.isEmpty()) {
            householdView.showNoHouseholds();
            return null;
        }

        householdView.showHouseholds(households);
        householdView.showSelectHouseholdPrompt();
        String input = scanner.nextLine();

        try {
            int index = Integer.parseInt(input) - 1;
            if (index < 0 || index >= households.size()) {
                householdView.showInvalidChoice();
                return null;
            }
            return households.get(index).getID();
        } catch (NumberFormatException e) {
            householdView.showInvalidChoice();
            return null;
        }
    }
}