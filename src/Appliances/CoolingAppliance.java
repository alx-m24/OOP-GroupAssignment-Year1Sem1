package Appliances;

public class CoolingAppliance extends Appliance {
    private double m_efficiencyRating;


    public CoolingAppliance(String name, double powerRating, double efficiencyRating) {
        super(name, powerRating);
        setEfficiencyRating(efficiencyRating);
    }

    public double getEfficiencyRating() {
        return m_efficiencyRating;
    }




    public void setEfficiencyRating(double efficiencyRating) {
        if (efficiencyRating <= 0) {
            this.m_efficiencyRating = 1.0; // Default factor if invalid
        } else {
            this.m_efficiencyRating = efficiencyRating;
        }
    }

    @Override
    public double calculateEnergyConsumption() {
        // Polymorphism: Custom energy math using the base calculation
        return super.calculateEnergyConsumption() * m_efficiencyRating;
    }
}
