package CostRegion.View;

import CostRegion.Entity.CostRegionEntity;

import java.util.ArrayList;

public class CostRegionView {
    public void showRegions(ArrayList<CostRegionEntity> regions) {
        System.out.println("=== Select Your Region ===");
        for (int i = 0; i < regions.size(); i++) {
            System.out.println((i + 1) + ". " + regions.get(i).getRegionName());
        }
        System.out.print("Choice: ");
    }

    public void showInvalidChoice() {
        System.out.println("Invalid choice. Please select a valid region.");
    }
}