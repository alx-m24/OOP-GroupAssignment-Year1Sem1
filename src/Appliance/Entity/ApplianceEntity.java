package Appliance.Entity;

import Utils.ID.ApplianceID;
import Utils.ID.HouseholdID;
import Utils.Units.Hours;
import Utils.Units.KiloWattsHour;
import Utils.Units.Watts;

public class ApplianceEntity {
    private ApplianceID ID;
    private HouseholdID householdID;
    private String applianceName;
    private Hours usage;
    private Watts powerRating;

    public ApplianceEntity(HouseholdID householdID, String applianceName, Hours usage, Watts powerRating) {
        this.ID = new ApplianceID();
        this.householdID = householdID;
        this.applianceName = applianceName;
        this.usage = usage;
        this.powerRating = powerRating;
    }

    public ApplianceEntity(ApplianceID id, HouseholdID householdID, String applianceName, Hours usage, Watts powerRating) {
        this.ID = id;
        this.householdID = householdID;
        this.applianceName = applianceName;
        this.usage = usage;
        this.powerRating = powerRating;
    }

    // --- Getters ---
    public ApplianceID getID() { return ID; }
    public HouseholdID getHouseholdID() { return householdID; }
    public String getApplianceName() { return applianceName; }
    public Hours getUsage() { return usage; }
    public Watts getPowerRating() { return powerRating; }

    // --- Setters ---
    public void setApplianceName(String applianceName) { this.applianceName = applianceName; }
    public void setUsage(Hours usage) { this.usage = usage; }
    public void setPowerRating(Watts powerRating) { this.powerRating = powerRating; }

    public KiloWattsHour getEnergyUsed() {
        double kwh = getPowerRating().toKiloWatts().getValue() * getUsage().getValue();
        return new KiloWattsHour(kwh);
    }

    @Override
    public String toString() {
        return "[" + ID.getID() + "] " + applianceName + " | " + powerRating + " | " + usage + "/day";
    }
}