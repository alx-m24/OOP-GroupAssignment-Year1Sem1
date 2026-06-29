// 1 user can have multiple households to monitor

package User.Entity;

import Household.Entity.HouseholdEntity;
import Household.Service.HouseholdService;

public class UserEntity {
    final private HouseholdService households;
    private String name;
    private String password;

    public UserEntity() {
        households = new HouseholdService();
    }

    public UserEntity(String name, String password) {
        households = new HouseholdService();
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addHousehold(HouseholdEntity household) {
        households.addHousehold(household);
    }

    public void removeHousehold(String id) {
        households.deleteHousehold(id);
    }

    public HouseholdEntity getHousehold(String id) {
        return households.getHouseholdById(id);
    }

    public HouseholdService getHouseholds() {
        return households;
    }

    public int getHouseholdCount() {
        return households.getAllHouseholds().size();
    }
}