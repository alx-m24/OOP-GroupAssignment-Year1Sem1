package Appliance.Service;

import Appliance.Entity.ApplianceEntity;
import Utils.ID.ApplianceID;
import Utils.ID.HouseholdID;
import Utils.Units.Hours;
import Utils.Units.Watts;

import java.util.ArrayList;

public class ApplianceService {
    private final ArrayList<ApplianceEntity> appliances = new ArrayList<>();

    public ApplianceEntity add(HouseholdID householdID, String name, Hours usage, Watts powerRating) {
        ApplianceEntity appliance = new ApplianceEntity(householdID, name, usage, powerRating);
        appliances.add(appliance);
        return appliance;
    }

    public void remove(ApplianceID id) {
        appliances.removeIf(a -> a.getID().equals(id));
    }

    public ApplianceEntity findByID(ApplianceID id) {
        for (ApplianceEntity a : appliances) {
            if (a.getID().equals(id)) return a;
        }
        return null;
    }

    public ArrayList<ApplianceEntity> getByHousehold(HouseholdID householdID) {
        ArrayList<ApplianceEntity> result = new ArrayList<>();
        for (ApplianceEntity a : appliances) {
            if (a.getHouseholdID().equals(householdID)) result.add(a);
        }
        return result;
    }

    public ApplianceEntity[] getAllAppliances() {
        ApplianceEntity[] ans = new ApplianceEntity[appliances.size()];
        appliances.toArray(ans);
        return ans;
    }

    public void load(ApplianceID applianceID, HouseholdID householdID, String name, Watts watts, Hours hours) {
        appliances.add(new ApplianceEntity(applianceID, householdID, name, hours, watts));
    }

    public void add(HouseholdID householdID, ApplianceEntity appliance) {
       appliances.add(appliance);
    }
}