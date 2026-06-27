package Household.Controller;

import java.util.List;

import Household.Entity.HouseholdEntity;
import Household.Service.HouseholdService;

import APPLIANCE.ENTITY.Appliance;

public class HouseholdController {

    private HouseholdService householdService;

    // ✅ Constructor
    public HouseholdController() {
        this.householdService = new HouseholdService();
    }

    // =========================
    // ✅ HOUSEHOLD OPERATIONS
    // =========================

    // ✅ Add household
    public boolean addHousehold(String id, String name, String username) {

        HouseholdEntity household = new HouseholdEntity(id, name, username);
        return householdService.addHousehold(household);
    }

    // ✅ Get household by ID
    public HouseholdEntity getHousehold(String id) {
        return householdService.getHouseholdById(id);
    }

    // ✅ Get all households
    public List<HouseholdEntity> getAllHouseholds() {
        return householdService.getAllHouseholds();
    }

    // ✅ Update household
    public boolean updateHousehold(String id, String newName, String newUsername) {
        return householdService.updateHousehold(id, newName, newUsername);
    }

    // ✅ Delete household
    public boolean deleteHousehold(String id) {
        return householdService.deleteHousehold(id);
    }

    // =========================
    // ✅ APPLIANCE OPERATIONS (IMPORTANT 🔥)
    // =========================

    // ✅ Add appliance to a household
    public boolean addApplianceToHousehold(String householdId, Appliance appliance) {

        HouseholdEntity household = householdService.getHouseholdById(householdId);

        if (household != null) {
            household.addAppliance(appliance);
            return true;
        }

        return false;
    }

    // ✅ Remove appliance from household
    public boolean removeApplianceFromHousehold(String householdId, String applianceName) {

        HouseholdEntity household = householdService.getHouseholdById(householdId);

        if (household != null) {
            return household.removeAppliance(applianceName);
        }

        return false;
    }

    // ✅ Get appliances from household
    public List<Appliance> getAppliancesFromHousehold(String householdId) {

        HouseholdEntity household = householdService.getHouseholdById(householdId);

        if (household != null) {
            return household.getAppliances();
        }

        return null;
    }
}