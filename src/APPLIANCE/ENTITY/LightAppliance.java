package APPLIANCE.ENTITY;

public class LightAppliance extends Appliance{
    private int m_quantity;

    public LightAppliance(String name, double powerRating, int quantity) {
        super(name, powerRating);
        setQuantity(quantity);
    }

    public int getQuantity() {
        return m_quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 1) {
            this.m_quantity = 1;
        } else {
            this.m_quantity = quantity;
        }
    }

    @Override
    public double calculateEnergyConsumption() {
        // Polymorphism: Multiplying base consumption by total active bulbs
        return super.calculateEnergyConsumption() * m_quantity;
    }
}
