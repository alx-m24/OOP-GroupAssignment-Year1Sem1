package Household.Service;

import Appliance.Entity.ApplianceEntity;
import Appliance.Service.ApplianceService;
import Household.Entity.HouseholdEntity;
import Report.Entity.ReportEntity;
import Report.Service.ReportService;
import Utils.ID.ApplianceID;
import Utils.ID.CostRegionID;
import Utils.ID.HouseholdID;
import Utils.ID.UserID;
import Utils.Units.Hours;
import Utils.Units.Watts;

import java.util.ArrayList;

public class HouseholdService {
    private final ArrayList<HouseholdEntity> households = new ArrayList<>();
    private final ApplianceService applianceService;
    private final ReportService reportService;

    public HouseholdService(ApplianceService applianceService, ReportService reportService) {
        this.applianceService = applianceService;
        this.reportService = reportService;
    }

    // --- CRUD ---
    public HouseholdEntity add(UserID ownerID, String householdName, CostRegionID costRegionID) {
        HouseholdEntity household = new HouseholdEntity(ownerID, householdName, costRegionID);
        households.add(household);
        return household;
    }

    public void remove(HouseholdID id) {
        households.removeIf(h -> h.getID().equals(id));
    }

    public HouseholdEntity findByID(HouseholdID id) {
        for (HouseholdEntity h : households) {
            if (h.getID().equals(id)) return h;
        }
        return null;
    }

    public ArrayList<HouseholdEntity> getByUser(UserID userID) {
        ArrayList<HouseholdEntity> result = new ArrayList<>();
        for (HouseholdEntity h : households) {
            if (h.getOwnerID().equals(userID)) result.add(h);
        }
        return result;
    }

    // --- Appliance delegation ---
    public ApplianceEntity addAppliance(HouseholdID householdID, String name, Hours usage, Watts powerRating) {
        HouseholdEntity household = findByID(householdID);
        if (household == null) return null;

        ApplianceEntity appliance = applianceService.add(householdID, name, usage, powerRating);
        household.addAppliance(appliance.getID());
        return appliance;
    }

    public void removeAppliance(HouseholdID householdID, ApplianceID applianceID) {
        HouseholdEntity household = findByID(householdID);
        if (household == null) return;

        applianceService.remove(applianceID);
        household.removeAppliance(applianceID);
    }

    // --- Report generation ---
    public ReportEntity generateReport(HouseholdID householdID) {
        HouseholdEntity household = findByID(householdID);
        if (household == null) return null;

        return reportService.generate(householdID, household.getCostRegionID());
    }

    public HouseholdEntity[] getAllHouseholds() {
        HouseholdEntity[] ans = new HouseholdEntity[this.households.size()];
        households.toArray(ans);
        return ans;
    }

    public void load(HouseholdID householdID, UserID ownerID, String name, CostRegionID costRegionID) {
        households.add(new HouseholdEntity(householdID, ownerID, name, costRegionID));
    }
}
