package Appliance.Entity;

import Appliance.Entity.ApplianceEntity;
import Utils.ID.ApplianceID;
import Utils.ID.HouseholdID;
import Utils.Units.Hours;
import Utils.Units.KiloWattsHour;
import Utils.Units.Watts;

public class LightingAppliance extends ApplianceEntity {
    private int lightCount;
    private double efficiencyRating; // 0.0 to 1.0, higher = more efficient

    public LightingAppliance(HouseholdID householdID, String name, Hours usage,
                             Watts powerRating, int lightCount, double efficiencyRating) {
        super(householdID, name, usage, powerRating);
        this.lightCount = lightCount;
        this.efficiencyRating = efficiencyRating;
    }

    // Load constructor
    public LightingAppliance(ApplianceID id, HouseholdID householdID, String name, Hours usage,
                             Watts powerRating, int lightCount, double efficiencyRating) {
        super(id, householdID, name, usage, powerRating);
        this.lightCount = lightCount;
        this.efficiencyRating = efficiencyRating;
    }

    public int getLightCount() { return lightCount; }
    public double getEfficiencyRating() { return efficiencyRating; }
    public void setLightCount(int lightCount) { this.lightCount = lightCount; }
    public void setEfficiencyRating(double efficiencyRating) { this.efficiencyRating = efficiencyRating; }

    // Energy = (watts * count * hours * efficiency) / 1000
    // Lower efficiency rating = more energy wasted
    @Override
    public KiloWattsHour getEnergyUsed() {
        double kwh = (getPowerRating().toKiloWatts().getValue()
                * lightCount
                * getUsage().getValue())
                * (2.0 - efficiencyRating); // efficiency penalty
        return new KiloWattsHour(kwh);
    }

    @Override
    public String toString() {
        return super.toString() + " | " + lightCount + " lights | efficiency: " + efficiencyRating;
    }
}