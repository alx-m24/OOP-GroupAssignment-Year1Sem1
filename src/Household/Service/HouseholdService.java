package Household.Service;

import Household.Entity.HouseholdEntity;

import java.util.ArrayList;
import java.util.List;

public class HouseholdService {

        // Stores all households
        private ArrayList<HouseholdEntity> householdList;

        // Constructor
        public HouseholdService() {
            householdList = new ArrayList<>();
        }


        // CREATE


        public boolean addHousehold(HouseholdEntity household) {
            if (household == null) {
                return false;
            }

            // prevent duplicate IDs
            if (getHouseholdById(household.getHouseholdId()) != null) {
                return false;
            }

            householdList.add(household);
            return true;
        }


        // READ


        public HouseholdEntity getHouseholdById(String id) {
            for (HouseholdEntity house : householdList) {
                if (house.getHouseholdId().equalsIgnoreCase(id)) {
                    return house;
                }
            }
            return null;
        }

        public ArrayList<HouseholdEntity> getAllHouseholds() {
            return new ArrayList<>(householdList); // safe copy
        }

        // UPDATE


        public boolean updateHousehold(String id, String newName, String newUsername) {
            HouseholdEntity house = getHouseholdById(id);

            if (house != null) {
                house.setName(newName);
                house.setUsername(newUsername);
                return true;
            }
            return false;
        }


        //DELETE

        public boolean deleteHousehold(String id) {
            return householdList.removeIf(
                    house -> house.getHouseholdId().equalsIgnoreCase(id)
            );
        }
    }

