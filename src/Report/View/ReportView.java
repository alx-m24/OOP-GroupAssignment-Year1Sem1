package Report.View;

import Report.Entity.ReportEntity;

import java.util.ArrayList;

public class ReportView {
    public void showMenu() {
        System.out.println("\n=== Report Menu ===");
        System.out.println("1. Generate report");
        System.out.println("0. Back");
        System.out.print("Choice: ");
    }

    public void showReport(ReportEntity report) {
        System.out.println("\n=============================");
        System.out.println("       ENERGY REPORT         ");
        System.out.println("=============================");
        System.out.println("Household : " + report.getHouseholdID().getID());
        System.out.println("Total kWh : " + report.getTotalKWh() + " kWh");
        System.out.printf ("Total Cost: RM %.2f%n", report.getTotalCost());
        System.out.println("-----------------------------");
        System.out.println("Suggestions:");
        ArrayList<String> suggestions = report.getSuggestions();
        for (int i = 0; i < suggestions.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + suggestions.get(i));
        }
        System.out.println("=============================");
    }

    public void showReportError() {
        System.out.println("Could not generate report. Household not found.");
    }

    public void showInvalidChoice() {
        System.out.println("Invalid choice. Please try again.");
    }
}
