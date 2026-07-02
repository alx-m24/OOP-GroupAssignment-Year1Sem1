package CostRegion.Entity;

import Utils.ID.CostRegionID;
import Utils.Units.KiloWattsHour;

public class CostRegionEntity {
    private CostRegionID ID;
    private String regionName;

    public CostRegionEntity(String regionName) {
        this.ID = new CostRegionID();
        this.regionName = regionName;
    }

    // --- Getters ---
    public CostRegionID getID() { return ID; }
    public String getRegionName() { return regionName; }

    // --- Abstract: each region defines its own rate ---
    public double getRatePerKWh() {
        return 1.0;
    };

    // --- Shared cost calculation ---
    public double calculateCost(KiloWattsHour energy) {
        return energy.getValue() * getRatePerKWh();
    }

    @Override
    public String toString() {
        return "[" + ID.getID() + "] " + regionName + " @ RM " + getRatePerKWh() + "/kWh";
    }
}