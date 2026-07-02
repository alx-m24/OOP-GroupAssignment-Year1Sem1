package Household.Entity;

import Utils.ID.ApplianceID;
import Utils.ID.CostRegionID;
import Utils.ID.HouseholdID;
import Utils.ID.UserID;

import java.util.ArrayList;

public class HouseholdEntity {
    private HouseholdID ID;
    private UserID ownerID;
    private String householdName;
    private ArrayList<ApplianceID> appliances;
    private CostRegionID regionID;

    public HouseholdEntity(UserID ownerID, String householdName, CostRegionID regionID) {
        this.ID = new HouseholdID();
        this.ownerID = ownerID;
        this.householdName = householdName;
        this.regionID = regionID;
        this.appliances = new ArrayList<>();
    }

    public HouseholdEntity(HouseholdID ID, UserID ownerID, String householdName, CostRegionID regionID) {
        this.ID = ID;
        this.ownerID = ownerID;
        this.householdName = householdName;
        this.regionID = regionID;
        this.appliances = new ArrayList<>();
    }

    // --- Getters ---
    public HouseholdID getID() {
        return ID;
    }

    public UserID getOwnerID() {
        return ownerID;
    }

    public String getHouseholdName() {
        return householdName;
    }

    public CostRegionID getCostRegionID() {
        return regionID;
    }

    public ArrayList<ApplianceID> getAppliances() {
        return appliances;
    }

    // --- Appliance management ---
    public void addAppliance(ApplianceID id) {
        appliances.add(id);
    }

    public void removeAppliance(ApplianceID id) {
        appliances.removeIf(a -> a.equals(id));
    }

    public boolean hasAppliance(ApplianceID id) {
        return appliances.stream().anyMatch(a -> a.equals(id));
    }

    @Override
    public String toString() {
        return "[" + ID.getID() + "] " + householdName;
    }
}
