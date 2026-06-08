package HouseHold;

import Appliances.*;

import java.util.ArrayList;

public class HouseHold {
    ArrayList<Appliance> m_appliances;

    public HouseHold() {
        m_appliances = new ArrayList<>();
    }

    // Adds an appliance to the tracker
    public boolean addAppliance(Appliance appliance) {
        if (appliance == null) {
            return false;
        }

        m_appliances.add(appliance);

        return true;
    }

    public Appliance getAppliance(String name) {
        for (int i = 0; i < m_appliances.size(); ++i) {
            if (m_appliances.get(i).getName().equals(name)) return m_appliances.get(i);
        }
        return null;
    }

    public Boolean hasAppliance(String name) {
        return getAppliance(name) != null;
    }

    public void removeAppliance(String name) {
        for (int i = 0; i < m_appliances.size(); ++i) {
            if (m_appliances.get(i).getName().equals(name)) {
                m_appliances.remove(i);
                break;
            }
        }
    }

    // Uses Polymorphism for each appliance classes
    public double calculateTotalEnergy() {
        double totalEnergy = 0.0;
        for (int i = 0; i < m_appliances.size(); i++) {
            totalEnergy += m_appliances.get(i).calculateEnergyConsumption();
        }
        return totalEnergy;
    }

    // Calculates estimated cost based on a utility rate
    public double calculateTotalCost(double tariffRate) {
        if (tariffRate < 0) return 0.0;
        return calculateTotalEnergy() * tariffRate;
    }

    // Getters for Menu/Navigation module to interact with data safely
    public int getApplianceCount() {
        return m_appliances.size();
    }

    // Returns the entire array, but user should only iterate up to m_applianceCount
    public ArrayList<Appliance> getAppliances() {
        return m_appliances;
    }
}