package Menu;

public class MenuAndNavigation {

    public static final String MAIN_MENU = "MAIN_MENU";
    public static final String ADD_APPLIANCE = "ADD_APPLIANCE";
    public static final String RECORD_USAGE = "RECORD_USAGE";
    public static final String VIEW_SUMMARY = "VIEW_SUMMARY";
    public static final String HIGH_CONSUMPTION = "HIGH_CONSUMPTION";
    public static final String EXIT = "EXIT";

    private String currentPage;

    public MenuAndNavigation() {
        this.currentPage = MAIN_MENU;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String targetPage) {
        if (targetPage == null || targetPage.trim().isEmpty()) {
            System.out.println("Invalid page selected.");
            return;
        }

        this.currentPage = targetPage;
        render();
    }

    public void switchPage(String targetPage) {
        setCurrentPage(targetPage);
    }

    public void render() {
        clearScreen();

        switch (currentPage) {
            case MAIN_MENU:
                renderMainMenu();
                break;

            case ADD_APPLIANCE:
                renderAddAppliancePage();
                break;

            case RECORD_USAGE:
                renderRecordUsagePage();
                break;

            case VIEW_SUMMARY:
                renderSummaryPage();
                break;

            case HIGH_CONSUMPTION:
                renderHighConsumptionPage();
                break;

            case EXIT:
                renderExitPage();
                break;

            default:
                System.out.println("Page not found.");
        }
    }

    private void clearScreen() {
        for (int i = 0; i < 40; i++) {
            System.out.println();
        }
    }

    private void renderHeader(String title) {
        System.out.printf("========================================%n");
        System.out.printf(" Household Energy Consumption Tracker%n");
        System.out.printf(" %s%n", title);
        System.out.printf("========================================%n%n");
    }

    private void renderMainMenu() {
        renderHeader("Main Menu");

        System.out.printf("1. Add Appliance%n");
        System.out.printf("2. Record Energy Usage%n");
        System.out.printf("3. View Consumption Summary%n");
        System.out.printf("4. Identify High Consumption Appliances%n");
        System.out.printf("5. Exit Program%n%n");
        System.out.printf("Enter your choice: ");
    }

    private void renderAddAppliancePage() {
        renderHeader("Add Appliance");

        System.out.printf("Enter appliance details below.%n%n");
        System.out.printf("Appliance name: ");
    }

    private void renderRecordUsagePage() {
        renderHeader("Record Energy Usage");

        System.out.printf("Record appliance usage in hours.%n%n");
        System.out.printf("Enter appliance name or ID: ");
    }

    private void renderSummaryPage() {
        renderHeader("Consumption Summary");

        System.out.printf("This page displays:%n");
        System.out.printf("- Total energy used%n");
        System.out.printf("- Average usage%n");
        System.out.printf("- Estimated cost%n%n");
        System.out.printf("Press Enter to return to the main menu.");
    }

    private void renderHighConsumptionPage() {
        renderHeader("High Consumption Appliances");

        System.out.printf("This page identifies appliances with high energy usage.%n%n");
        System.out.printf("Press Enter to return to the main menu.");
    }

    private void renderExitPage() {
        renderHeader("Exit");

        System.out.printf("Thank you for using the Household Energy Consumption Tracker.%n");
        System.out.printf("Save energy. Support SDG 7.%n");
    }
}