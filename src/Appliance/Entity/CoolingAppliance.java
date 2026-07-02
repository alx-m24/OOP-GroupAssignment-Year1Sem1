package Appliance.Entity;

import Appliance.Entity.ApplianceEntity;
import Utils.ID.ApplianceID;
import Utils.ID.HouseholdID;
import Utils.Units.Hours;
import Utils.Units.KiloWattsHour;
import Utils.Units.Watts;

public class CoolingAppliance extends ApplianceEntity {
    private Watts standbyPower;   // power consumed when off but plugged in
    private Hours standbyHours;   // hours per day spent in standby

    public CoolingAppliance(HouseholdID householdID, String name, Hours usage,
                            Watts powerRating, Watts standbyPower, Hours standbyHours) {
        super(householdID, name, usage, powerRating);
        this.standbyPower = standbyPower;
        this.standbyHours = standbyHours;
    }

    // Load constructor
    public CoolingAppliance(ApplianceID id, HouseholdID householdID, String name, Hours usage,
                            Watts powerRating, Watts standbyPower, Hours standbyHours) {
        super(id, householdID, name, usage, powerRating);
        this.standbyPower = standbyPower;
        this.standbyHours = standbyHours;
    }

    public Watts getStandbyPower() { return standbyPower; }
    public Hours getStandbyHours() { return standbyHours; }
    public void setStandbyPower(Watts standbyPower) { this.standbyPower = standbyPower; }
    public void setStandbyHours(Hours standbyHours) { this.standbyHours = standbyHours; }

    // Energy = active kWh + standby kWh
    @Override
    public KiloWattsHour getEnergyUsed() {
        double activeKwh  = getPowerRating().toKiloWatts().getValue() * getUsage().getValue();
        double standbyKwh = standbyPower.toKiloWatts().getValue() * standbyHours.getValue();
        return new KiloWattsHour(activeKwh + standbyKwh);
    }

    @Override
    public String toString() {
        return super.toString() + " | standby: " + standbyPower.getValue() + "W x " + standbyHours.getValue() + "h";
    }
}