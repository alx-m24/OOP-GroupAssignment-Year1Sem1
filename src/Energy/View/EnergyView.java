package Energy.View;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Energy.Controller.EnergyController;
import Energy.Entity.EnergyEntity;

import Household.Controller.HouseholdController;
import Household.Entity.HouseholdEntity;

import APPLIANCE.ENTITY.Appliance;
import Energy.Utility.EnergyUtility;

public class EnergyView {

    private EnergyController controller;
    private HouseholdController householdController;
    private Scanner scanner;

    //  Constructor

    public EnergyView(HouseholdController householdController) {
        this.controller = new EnergyController();
        this.householdController = householdController;
        this.scanner = new Scanner(System.in);
    }


    private void showTop3All() {

        ArrayList<HouseholdEntity> households =
                householdController.getAllHouseholds();

        ArrayList<Appliance> list =
                controller.getTop3All(households);

        for (Appliance app : list) {
            System.out.println(app.getName() + " | Energy: " +
                    EnergyUtility.formatEnergy(
                            app.calculateEnergyConsumption()));
        }
        System.out.println(controller.generateAdvice(list));
    }


    private void viewReports() {

        ArrayList<EnergyEntity> list = controller.getAllReports();

        if (list.isEmpty()) {
            System.out.println("No reports available.");
            return;
        }

        for (EnergyEntity r : list) {

            System.out.println("\nReport ID: " + r.getReportId());
            System.out.println("Date: " + r.getDate());
            System.out.println("Household: " +
                    r.getHousehold().getHouseholdId());

            for (String advice : r.getAdvice()) {
                System.out.println("- " + advice);
            }
        }
    }


    private void generateReport() {

        System.out.print("Enter Household ID: ");
        String id = scanner.nextLine();

        HouseholdEntity household =
                householdController.getHousehold(id);

        if (household == null) {
            System.out.println("Household not found!");
            return;
        }

        System.out.print("Enter Report ID: ");
        String reportId =String.valueOf((int) (Math.random() * 10000) +1);
        String date = LocalDate.now().toString();

        EnergyEntity report =
                controller.generateReport(reportId, date, household);

        System.out.println(" Report generated!");


        for (Appliance app : report.getTopAppliances()) {
            System.out.println(app.getName() + " | Energy: " +
                    EnergyUtility.formatEnergy(
                            app.calculateEnergyConsumption()));
        }

    }





    public void showMenu() {

        int choice;

        do {
            System.out.println("\n=== Energy Menu ===");
            System.out.println("1. Generate Report (Household)");
            System.out.println("2. View All Reports");
            System.out.println("3. Top 3 Appliances (All Households)");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    generateReport();
                    break;
                case 2:
                    viewReports();
                    break;
                case 3:
                    showTop3All();
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }

        } while (choice != 0);
    }
}

