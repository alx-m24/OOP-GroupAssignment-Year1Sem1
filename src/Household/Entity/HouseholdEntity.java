package Household.Entity;

import APPLIANCE.ENTITY.Appliance;

import java.util.ArrayList;
import java.util.List;

public class HouseholdEntity {

        private String householdId;
        private String name;
        private String username;
        private ArrayList<Appliance> appliances;

        // CONSTRUCTOR

        public HouseholdEntity(String householdId, String name, String username) {
            this.householdId = householdId;
            this.name = name;
            this.username = username;
            this.appliances = new ArrayList<>();
        }
        //GETTERS

        public String getHouseholdId() {
            return householdId;
        }

        public String getName() {
            return name;
        }

        public String getUsername() {
            return username;
        }

        public ArrayList<Appliance> getAppliances() {
            return new ArrayList<>(appliances); // safe copy
        }

        // SETTERS

        public void setName(String name) {
            this.name = name;
        }

        public void setUsername(String username) {
            this.username = username;
        }


        // CORE METHODS


    //Create :adding new appliance
    public void addAppliance(Appliance appliance){
        appliances.add(appliance);
    }
    //Read :read each appliance by id(name)
    public Appliance readAppliance(String name){
        for(Appliance app:appliances){
            if(app.getName().equalsIgnoreCase(name)){
                return app;
            }
        }
        return null;

    }
    //Read: read whole array
    public ArrayList<Appliance> getApplianceList(){
        return new ArrayList<>(appliances);
    }

    //Update :edit appliance details
    public boolean updateAppliance(
            String name, String newName, double newPowerRating, double newDuration) {
        for (Appliance app : appliances) {
            if (app.getName().equals(name)) {
                app.setName(newName);
                app.setPowerRating(newPowerRating);
                app.setUsageDuration(newDuration);
                return true;
            }
        }
        return false;

    }


    //Delete:
    public boolean removeAppliance (String name){
        return appliances.removeIf(appliance1 -> appliance1.getName().equals(name));

    }

        // Get total energy consumption of household
        public double getTotalEnergyConsumption() {
            double total = 0;

            for (Appliance app : appliances) {
                total += app.calculateEnergyConsumption();
            }

            return total;
        }
    }

