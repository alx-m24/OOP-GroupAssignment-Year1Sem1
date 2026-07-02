package Appliance.Entity;

import Appliance.Entity.ApplianceEntity;
import Utils.ID.ApplianceID;
import Utils.ID.HouseholdID;
import Utils.Units.Hours;
import Utils.Units.KiloWattsHour;
import Utils.Units.Watts;

public class HeatingAppliance extends ApplianceEntity {
    private double heatingEfficiency; // 0.0 to 1.0, e.g. 0.9 = 90% efficient
    private double insulationFactor;  // 0.0 to 1.0, how well the space retains heat
    // lower = loses heat faster = heater works harder

    public HeatingAppliance(HouseholdID householdID, String name, Hours usage,
                            Watts powerRating, double heatingEfficiency, double insulationFactor) {
        super(householdID, name, usage, powerRating);
        this.heatingEfficiency = heatingEfficiency;
        this.insulationFactor = insulationFactor;
    }

    // Load constructor
    public HeatingAppliance(ApplianceID id, HouseholdID householdID, String name, Hours usage,
                            Watts powerRating, double heatingEfficiency, double insulationFactor) {
        super(id, householdID, name, usage, powerRating);
        this.heatingEfficiency = heatingEfficiency;
        this.insulationFactor = insulationFactor;
    }

    public double getHeatingEfficiency() { return heatingEfficiency; }
    public double getInsulationFactor() { return insulationFactor; }
    public void setHeatingEfficiency(double heatingEfficiency) { this.heatingEfficiency = heatingEfficiency; }
    public void setInsulationFactor(double insulationFactor) { this.insulationFactor = insulationFactor; }

    // Energy = base kWh / efficiency, scaled up by poor insulation
    // Poor insulation (low factor) means the heater runs longer to compensate
    @Override
    public KiloWattsHour getEnergyUsed() {
        double baseKwh = getPowerRating().toKiloWatts().getValue() * getUsage().getValue();
        double adjusted = (baseKwh / heatingEfficiency) * (2.0 - insulationFactor);
        return new KiloWattsHour(adjusted);
    }

    @Override
    public String toString() {
        return super.toString() + " | efficiency: " + heatingEfficiency + " | insulation: " + insulationFactor;
    }
}