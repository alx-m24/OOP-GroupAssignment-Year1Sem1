package Energy.Entity;

import java.util.List;

import APPLIANCE.ENTITY.Appliance;
import Household.Entity.HouseholdEntity;

public class EnergyEntity {

    private String reportId;
    private String date;
    private HouseholdEntity household;
    private List<Appliance> topAppliances;
    private List<String> advice;

    public EnergyEntity(String reportId, String date,
                        HouseholdEntity household,
                        List<Appliance> topAppliances,
                        List<String> advice) {

        this.reportId = reportId;
        this.date = date;
        this.household = household;
        this.topAppliances = topAppliances;
        this.advice = advice;
    }

    public String getReportId() {
        return reportId;
    }

    public String getDate() {
        return date;
    }

    public HouseholdEntity getHousehold() {
        return household;
    }

    public List<Appliance> getTopAppliances() {
        return topAppliances;
    }

    public List<String> getAdvice() {
        return advice;
    }
}