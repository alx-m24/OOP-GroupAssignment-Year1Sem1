package Report.Controller;

import Report.Entity.ReportEntity;
import Report.View.ReportView;
import Household.Service.HouseholdService;
import Utils.ID.HouseholdID;

import java.util.Scanner;

public class ReportController {
    final private HouseholdService householdService;
    final private ReportView reportView;
    final private Scanner scanner;

    public ReportController(HouseholdService householdService, Scanner scanner) {
        this.householdService = householdService;
        this.scanner = scanner;
        this.reportView = new ReportView();
    }

    public void showMenu(HouseholdID householdID) {
        reportView.showMenu();
        String choice = scanner.nextLine();

        switch (choice) {
            case "1": generateReport(householdID); break;
            case "0": return;
            default:
                reportView.showInvalidChoice();
        }
        showMenu(householdID); // loop back
    }

    public void generateReport(HouseholdID householdID) {
        ReportEntity report = householdService.generateReport(householdID);
        if (report == null) {
            reportView.showReportError();
            return;
        }
        reportView.showReport(report);
    }
}