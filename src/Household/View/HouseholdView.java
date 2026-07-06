package Household.View;

import Appliance.Service.ApplianceService;
import Household.Entity.HouseholdEntity;
import Utils.ID.ApplianceID;

import java.util.ArrayList;

public class HouseholdView {
    public void showMenu() {
        System.out.println("\n=== Household Menu ===");
        System.out.println("1. View my households");
        System.out.println("2. Add household");
        System.out.println("3. Remove household");
        System.out.println("0. Back");
        System.out.print("Choice: ");
    }

    public void showHouseholds(ArrayList<HouseholdEntity> households, ApplianceService applianceService) {
        System.out.println("\n=== My Households ===");
        for (int i = 0; i < households.size(); i++) {
            System.out.println((i + 1) + ". " + households.get(i) + ": ");
            for (ApplianceID aID : households.get(i).getAppliances()) {
                System.out.println("\t" + applianceService.findByID(aID));
            }
        }
    }

    public void showNoHouseholds() {
        System.out.println("You have no households yet.");
    }

    public void showAddHouseholdPrompt() {
        System.out.print("Household name: ");
    }

    public void showAddHouseholdSuccess(HouseholdEntity household) {
        System.out.println("Household \"" + household.getHouseholdName() + "\" added successfully.");
    }

    public void showRemoveHouseholdPrompt() {
        System.out.print("Select household to remove: ");
    }

    public void showRemoveHouseholdSuccess(HouseholdEntity household) {
        System.out.println("Household \"" + household.getHouseholdName() + "\" removed.");
    }

    public void showSelectHouseholdPrompt() {
        System.out.print("Select a household: ");
    }

    public void showInvalidChoice() {
        System.out.println("Invalid choice. Please try again.");
    }
}