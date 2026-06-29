package Cost.Controller;

import Cost.Service.CostService;
import Cost.View.CostView;

public class CostController {
    private CostService service;
    private CostView view;

    public CostController(CostService service, CostView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        int choice;
        do {
            choice = view.showMenu();
            switch (choice) {
                case 1: handleSelectTariff();   break;
                case 2: handleCalculateCost();  break;
                case 3: handleCompareTariffs(); break;
                case 4: view.displayMessage("Returning to main menu..."); break;
                default: view.displayMessage("Invalid option. Please try again.");
            }
        } while (choice != 5);
    }

    private void handleSelectTariff() {
        String type = view.promptTariffType();
        service.selectTariff(type);
        view.displayMessage("Tariff set to: " + type);
    }

    private void handleCalculateCost() {
        double kwh = view.promptKwh();
        double daily   = service.calculateDailyCost(kwh);
        double monthly = service.calculateMonthlyCost(kwh);
        view.displayCostResult(daily, monthly, service.getSelectedTariff());
    }

    private void handleCompareTariffs() {
        double kwh = view.promptKwh();
        double[] results = service.compareTariffs(kwh);
        view.displayTariffComparison(kwh, results);
    }
}