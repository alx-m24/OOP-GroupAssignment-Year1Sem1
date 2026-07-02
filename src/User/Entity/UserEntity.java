package User.Entity;

import Utils.ID.HouseholdID;
import Utils.ID.UserID;

import java.util.ArrayList;

public class UserEntity {
    private UserID ID;
    private String userName;
    private String password;
    private ArrayList<HouseholdID> households;

    public UserEntity(String userName, String password) {
        this.ID = new UserID();
        this.userName = userName;
        this.password = password;
        this.households = new ArrayList<>();
    }

    public UserEntity(UserID id, String userName, String password) {
        this.ID = id;
        this.userName = userName;
        this.password = password;
        this.households = new ArrayList<>();
    }

    // --- Getters ---
    public UserID getID() {
        return ID;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public ArrayList<HouseholdID> getHouseholds() {
        return households;
    }

    // --- Household management ---
    public void addHousehold(HouseholdID id) {
        households.add(id);
    }

    public void removeHousehold(HouseholdID id) {
        households.removeIf(h -> h.equals(id));
    }

    public boolean hasHousehold(HouseholdID id) {
        return households.stream().anyMatch(h -> h.equals(id));
    }

    // --- Auth helper (used by UserService) ---
    public boolean checkPassword(String input) {
        return this.password.equals(input);
    }

    @Override
    public String toString() {
        return "[" + ID.getID() + "] " + userName;
    }
}