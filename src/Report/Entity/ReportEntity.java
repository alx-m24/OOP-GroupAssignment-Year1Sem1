package Report.Entity;

import Utils.ID.HouseholdID;
import Utils.ID.ReportID;
import Utils.Units.KiloWattsHour;

import java.util.ArrayList;

public class ReportEntity {
    private ReportID ID;
    private HouseholdID householdID;
    private KiloWattsHour totalKWh;
    private double totalCost;
    private ArrayList<String> suggestions;

    public ReportEntity(HouseholdID householdID, KiloWattsHour totalKWh, double totalCost, ArrayList<String> suggestions) {
        this.ID = new ReportID();
        this.householdID = householdID;
        this.totalKWh = totalKWh;
        this.totalCost = totalCost;
        this.suggestions = suggestions;
    }

    // --- Getters ---
    public ReportID getID() { return ID; }
    public HouseholdID getHouseholdID() { return householdID; }
    public double getTotalKWh() { return totalKWh.getValue(); }
    public double getTotalCost() { return totalCost; }
    public ArrayList<String> getSuggestions() { return suggestions; }

    @Override
    public String toString() {
        return "[" + ID.getID() + "] Household: " + householdID.getID() +
                " | " + totalKWh + " kWh | RM " + totalCost;
    }
}