package Appliances;

public class HeatingAppliance extends Appliance {
    private double m_standbyLoss; // Constant minor power loss in watts when plugged in

    public HeatingAppliance(String name, double powerRating, double standbyLoss) {
        super(name, powerRating);
        setStandbyLoss(standbyLoss);
    }

    public double getStandbyLoss() {
        return m_standbyLoss;
    }

    public void setStandbyLoss(double standbyLoss) {
        this.m_standbyLoss = (standbyLoss < 0) ? 0 : standbyLoss;
    }

    @Override
    public double calculateEnergyConsumption() {
        // Custom calculation logic adding background standby energy usage
        double activeConsumption = super.calculateEnergyConsumption();
        double standbyConsumption = (m_standbyLoss * (24 - getUsageDuration())) / 1000.0;
        return activeConsumption + (standbyConsumption > 0 ? standbyConsumption : 0);
    }
}
