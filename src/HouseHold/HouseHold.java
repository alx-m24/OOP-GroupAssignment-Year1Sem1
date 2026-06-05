package HouseHold;

import Appliances.*;

public class HouseHold {
    private final int MAX_APPLIANCE_NUM = 100;
    private Appliance[] m_appliances;
    private int m_applianceCount; // Tracks the actual number of added appliances

    public HouseHold() {
        m_appliances = new Appliance[MAX_APPLIANCE_NUM];
        m_applianceCount = 0;
    }

    // Adds an appliance to the tracker
    public boolean addAppliance(Appliance appliance) {
        if (appliance == null) {
            return false;
        }
        if (m_applianceCount >= MAX_APPLIANCE_NUM) {
            return false;
        }

        m_appliances[m_applianceCount] = appliance;
        m_applianceCount++;
        return true;
    }

    public Appliance getAppliance(String name) {
        for (int i = 0; i < m_applianceCount; ++i) {
            if (m_appliances[i].getName().equals(name)) return m_appliances[i];
        }
        return null;
    }

    // Uses Polymorphism for each appliance classes
    public double calculateTotalEnergy() {
        double totalEnergy = 0.0;
        for (int i = 0; i < m_applianceCount; i++) {
            totalEnergy += m_appliances[i].calculateEnergyConsumption();
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
        return m_applianceCount;
    }

    // Returns the entire array, but user should only iterate up to m_applianceCount
    public Appliance[] getAppliances() {
        return m_appliances;
    }
}