package CostRegion.Controller;

import CostRegion.Entity.CostRegionEntity;
import CostRegion.Service.CostRegionService;
import CostRegion.View.CostRegionView;

import java.util.ArrayList;
import java.util.Scanner;

public class CostRegionController {
    final private CostRegionService costRegionService;
    final private CostRegionView costRegionView;
    final private Scanner scanner;

    public CostRegionController(CostRegionService costRegionService, Scanner scanner) {
        this.costRegionService = costRegionService;
        this.scanner = scanner;
        this.costRegionView = new CostRegionView();
    }

    public CostRegionEntity pickRegion() {
        ArrayList<CostRegionEntity> regions = costRegionService.getAllRegions();
        costRegionView.showRegions(regions);

        String choice = scanner.nextLine();

        int index = Integer.parseInt(choice) - 1;
        if (index < 0 || index >= regions.size()) {
            costRegionView.showInvalidChoice();
            return pickRegion(); // retry
        }
        return regions.get(index);
    }
}