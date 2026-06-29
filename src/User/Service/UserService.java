package User.Service;

import User.Entity.UserEntity;
import Household.Entity.HouseholdEntity;
import java.util.ArrayList;

public class UserService {
    private final ArrayList<UserEntity> users;

    public UserService() {
        users = new ArrayList<>();
    }

    // --- CREATE ---

    public boolean addUser(UserEntity user) {
        if (user == null) {
            return false;
        }
        // prevent duplicate IDs
        if (getUser(user.getName()) != null) {
            return false;
        }
        users.add(user);
        return true;
    }

    // --- READ ---

    public UserEntity getUser(String userName) {
        for (UserEntity user : users) {
            if (user.getName().equalsIgnoreCase(userName)) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<UserEntity> getAllUsers() {
        return new ArrayList<>(users); // safe copy
    }

    // --- UPDATE ---

    public boolean updateUserName(String userName, String newName) {
        UserEntity user = getUser(userName);
        if (user == null) {
            return false;
        }
        user.setName(newName);
        return true;
    }

    public boolean updateUserPassword(String userName, String newPassword) {
        UserEntity user = getUser(userName);
        if (user == null) {
            return false;
        }
        user.setPassword(newPassword);
        return true;
    }

    // --- DELETE ---

    public boolean deleteUser(String name) {
        return users.removeIf(
                user -> user.getName().equalsIgnoreCase(name)
        );
    }

    // --- HOUSEHOLD DELEGATION ---

    public boolean addHouseholdToUser(String userName, HouseholdEntity household) {
        UserEntity user = getUser(userName);
        if (user == null) {
            return false;
        }
        user.addHousehold(household);
        return true;
    }

    public boolean removeHouseholdFromUser(String userName, String householdId) {
        UserEntity user = getUser(userName);
        if (user == null) {
            return false;
        }
        user.removeHousehold(householdId);
        return true;
    }
}