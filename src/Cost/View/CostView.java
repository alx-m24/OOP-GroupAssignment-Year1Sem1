package Cost.View;

import Cost.Entity.Base.CostEstimator;
import java.util.Scanner;

public class CostView {
    final private Scanner scanner = new Scanner(System.in);

    // --- Menu ---

    public int showMenu() {
        System.out.println("\n=============================");
        System.out.println("       COST ESTIMATION       ");
        System.out.println("=============================");
        System.out.println(" 1. Select Tariff Zone");
        System.out.println(" 2. Calculate Cost");
        System.out.println(" 3. Compare All Tariffs");
        System.out.println(" 4. Back to Main Menu");
        System.out.println("=============================");
        System.out.print("Enter choice: ");
        return promptInt();
    }

    // --- Prompts ---

    public String promptTariffType() {
        System.out.println("\nAvailable tariff zones:");
        System.out.println("  [1] City");
        System.out.println("  [2] Suburban");
        System.out.println("  [3] Rural");
        System.out.print("Select zone: ");
        int choice = promptInt();
        switch (choice) {
            case 1:  return "city";
            case 2:  return "suburban";
            case 3:  return "rural";
            default: return "suburban";
        }
    }

    public double promptKwh() {
        System.out.print("Enter energy usage (kWh): ");
        return promptDouble();
    }

    public double promptBudgetLimit() {
        System.out.print("Enter monthly budget limit (RM): ");
        return promptDouble();
    }

    // --- Display ---

    public void displayCostResult(double daily, double monthly, CostEstimator tariff) {
        System.out.println("\n-----------------------------");
        System.out.println("       COST BREAKDOWN        ");
        System.out.println("-----------------------------");
        System.out.println("Tariff     : " + tariff.getTariffName());
        System.out.printf ("Rate       : RM %.2f / kWh%n", tariff.getRatePerKwH());
        System.out.printf ("Daily Cost : RM %.2f%n", daily);
        System.out.printf ("Monthly Est: RM %.2f%n", monthly);
        System.out.println("-----------------------------");
    }

    public void displayTariffComparison(double kwh, double[] results) {
        System.out.println("\n-----------------------------");
        System.out.printf ("   TARIFF COMPARISON (%.1f kWh)%n", kwh);
        System.out.println("-----------------------------");
        System.out.printf ("City        : RM %.2f%n", results[0]);
        System.out.printf ("Suburban    : RM %.2f%n", results[1]);
        System.out.printf ("Rural       : RM %.2f%n", results[2]);
        System.out.println("-----------------------------");
        int cheapest = findCheapest(results);
        String[] names = {"City", "Suburban", "Rural"};
        System.out.println("Cheapest    : " + names[cheapest]);
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

    private double promptDouble() {
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            displayMessage("Invalid input. Defaulting to 0.");
            return 0.0;
        }
    }

    private int findCheapest(double[] results) {
        int idx = 0;
        for (int i = 1; i < results.length; i++) {
            if (results[i] < results[idx]) idx = i;
        }
        return idx;
    }
}