package Utils.DataManager;

import Appliance.Entity.ApplianceEntity;
import Appliance.Entity.CoolingAppliance;
import Appliance.Entity.HeatingAppliance;
import Appliance.Entity.LightingAppliance;
import Appliance.Service.ApplianceService;
import CostRegion.Entity.CostRegionEntity;
import CostRegion.Service.CostRegionService;
import Household.Entity.HouseholdEntity;
import Household.Service.HouseholdService;
import User.Entity.UserEntity;
import User.Service.UserService;
import Utils.ID.*;
import Utils.ID.Base.ID;
import Utils.Units.Hours;
import Utils.Units.Watts;

import java.io.*;
import java.util.ArrayList;

public class DataManager {
    final private HouseholdService householdService;
    final private ApplianceService applianceService;
    final private UserService userService;
    final private ID id_base;

    private static final String USERS_FILE      = "data/users.txt";
    private static final String HOUSEHOLDS_FILE = "data/households.txt";
    private static final String APPLIANCES_FILE = "data/appliances.txt";

    public DataManager(ID idBase, HouseholdService householdService, ApplianceService applianceService,
                       UserService userService) {
        this.id_base = idBase;
        this.householdService = householdService;
        this.applianceService = applianceService;
        this.userService = userService;
    }

    // -------------------------------------------------------------------------
    // Public entry points
    // -------------------------------------------------------------------------

    public void saveAll() {
        saveUsers();
        saveHouseholds();
        saveAppliances();
    }

    public void loadAll() {
        loadUsers();
        loadHouseholds();
        loadAppliances();

        // Restore the global ID counter to one past the highest value seen
        // so new objects don't collide with loaded ones
        syncIDCounter();
    }

    // -------------------------------------------------------------------------
    // Save
    // -------------------------------------------------------------------------

    private void saveUsers() {
        File file = new File(USERS_FILE);
        file.getParentFile().mkdirs();
        clearFile(file);

        for (UserEntity u : userService.getAllUsers()) {
            // Format: ID,userName,password,H_0;H_1;H_2
            StringBuilder householdIDs = new StringBuilder();
            ArrayList<HouseholdID> ids = u.getHouseholds();
            for (int i = 0; i < ids.size(); i++) {
                householdIDs.append(ids.get(i).getID());
                if (i < ids.size() - 1) householdIDs.append(";");
            }
            append(file, u.getID().getID() + "," + u.getUserName() + "," + u.getPassword() + "," + householdIDs);
        }
    }

    private void saveHouseholds() {
        File file = new File(HOUSEHOLDS_FILE);
        file.getParentFile().mkdirs();
        clearFile(file);

        for (HouseholdEntity h : householdService.getAllHouseholds()) {
            // Format: ID,ownerID,householdName,costRegionID
            append(file, h.getID().getID() + "," +
                    h.getOwnerID().getID() + "," +
                    h.getHouseholdName() + "," +
                    h.getCostRegionID().getID());
        }
    }

    private void saveAppliances() {
        File file = new File(APPLIANCES_FILE);
        file.getParentFile().mkdirs();
        clearFile(file);

        for (ApplianceEntity a : applianceService.getAllAppliances()) {
            String base = a.getID().getID() + "," +
                    a.getHouseholdID().getID() + "," +
                    a.getApplianceName() + "," +
                    a.getPowerRating().getValue() + "," +
                    a.getUsage().getValue();

            if (a instanceof LightingAppliance) {
                LightingAppliance l = (LightingAppliance) a;
                append(file, "LIGHT," + base + "," + l.getLightCount() + "," + l.getEfficiencyRating());
            } else if (a instanceof CoolingAppliance) {
                CoolingAppliance c = (CoolingAppliance) a;
                append(file, "COOLING," + base + "," + c.getStandbyPower().getValue() + "," + c.getStandbyHours().getValue());
            } else if (a instanceof HeatingAppliance) {
                HeatingAppliance h = (HeatingAppliance) a;
                append(file, "HEATING," + base + "," + h.getHeatingEfficiency() + "," + h.getInsulationFactor());
            } else {
                append(file, "GENERIC," + base);
            }
        }
    }

    private void saveRegions() {
        // Regions are hardcoded in CostRegionService constructor so nothing to save
    }

    // -------------------------------------------------------------------------
    // Load
    // -------------------------------------------------------------------------

    private void loadUsers() {
        File file = new File(USERS_FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",", -1);

                int idValue = parseIDValue(parts[0]);
                UserID id = new UserID(idValue);
                String userName = parts[1];
                String password = parts[2];

                UserEntity user = userService.load(id, userName, password);

                // Restore household ID references
                if (parts.length > 3 && !parts[3].isEmpty()) {
                    for (String hid : parts[3].split(";")) {
                        int hValue = parseIDValue(hid);
                        user.addHousehold(new HouseholdID(hValue));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Warning: could not load users — " + e.getMessage());
        }
    }

    private void loadHouseholds() {
        File file = new File(HOUSEHOLDS_FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",", -1);

                int idValue       = parseIDValue(parts[0]);
                int ownerValue    = parseIDValue(parts[1]);
                String name       = parts[2];
                int regionValue   = parseIDValue(parts[3]);

                HouseholdID householdID   = new HouseholdID(idValue);
                UserID ownerID            = new UserID(ownerValue);
                CostRegionID costRegionID = new CostRegionID(regionValue);

                householdService.load(householdID, ownerID, name, costRegionID);
            }
        } catch (IOException e) {
            System.out.println("Warning: could not load households — " + e.getMessage());
        }
    }

    private void loadAppliances() {
        File file = new File(APPLIANCES_FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] p = line.split(",", -1);

                // p[0] = type tag, p[1] = applianceID, p[2] = householdID,
                // p[3] = name, p[4] = watts, p[5] = hours, p[6]+ = type-specific fields
                String type        = p[0];
                ApplianceID aID    = new ApplianceID(parseIDValue(p[1]));
                HouseholdID hID    = new HouseholdID(parseIDValue(p[2]));
                String name        = p[3];
                Watts watts        = new Watts(Double.parseDouble(p[4]));
                Hours hours        = new Hours(Double.parseDouble(p[5]));

                switch (type) {
                    case "LIGHT":
                        int lightCount       = Integer.parseInt(p[6]);
                        double efficiency    = Double.parseDouble(p[7]);
                        applianceService.add(hID, new LightingAppliance(aID, hID, name, hours, watts, lightCount, efficiency));
                        break;

                    case "COOLING":
                        Watts standbyPower   = new Watts(Double.parseDouble(p[6]));
                        Hours standbyHours   = new Hours(Double.parseDouble(p[7]));
                        applianceService.add(hID, new CoolingAppliance(aID, hID, name, hours, watts, standbyPower, standbyHours));
                        break;

                    case "HEATING":
                        double heatingEff    = Double.parseDouble(p[6]);
                        double insulation    = Double.parseDouble(p[7]);
                        applianceService.add(hID, new HeatingAppliance(aID, hID, name, hours, watts, heatingEff, insulation));
                        break;

                    default: // GENERIC
                        applianceService.load(aID, hID, name, watts, hours);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Warning: could not load appliances — " + e.getMessage());
        }
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    // Syncs the global ID counter to one past the highest loaded ID value
    private void syncIDCounter() {
        int max = 0;
        for (UserEntity u : userService.getAllUsers())
            max = Math.max(max, u.getID().getValue());
        for (HouseholdEntity h : householdService.getAllHouseholds())
            max = Math.max(max, h.getID().getValue());
        for (ApplianceEntity a : applianceService.getAllAppliances())
            max = Math.max(max, a.getID().getValue());

        id_base.setNextIndex(max + 1);
    }

    private void clearFile(File file) {
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.print("");
        } catch (IOException e) {
            System.out.println("Warning: could not clear file — " + e.getMessage());
        }
    }

    public static void append(File file, String line) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Warning: could not write to file — " + e.getMessage());
        }
    }

    // Strips the prefix and parses the numeric value e.g. "U_3" → 3
    private int parseIDValue(String rawID) {
        String[] parts = rawID.split("_");
        return Integer.parseInt(parts[parts.length - 1]);
    }
}