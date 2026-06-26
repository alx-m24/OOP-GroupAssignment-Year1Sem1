package APPLIANCE.ENTITY;

public class Appliance {
    private String m_name;
    private double m_powerRating; // in Watts
    private double m_usageDuration; // in Hours

    public Appliance(String name, double powerRating) {
        setName(name);
        setPowerRating(powerRating);
        this.m_usageDuration = 0.0;
    }

    public String getName() {
        return m_name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            this.m_name = "Unknown";
        } else {
            this.m_name = name;
        }
    }

    public double getPowerRating() {
        return m_powerRating;
    }

    public void setPowerRating(double powerRating) {
        if (powerRating < 0) {
            this.m_powerRating = 0.0;
        } else {
            this.m_powerRating = powerRating;
        }
    }

    public double getUsageDuration() {
        return m_usageDuration;
    }

    public void setUsageDuration(double usageDuration) {
        if (usageDuration < 0) {
            this.m_usageDuration = 0.0;
        } else {
            this.m_usageDuration = usageDuration;
        }
    }

    public double calculateEnergyConsumption() {
        return (m_powerRating * m_usageDuration) / 1000.0;
    }


}



