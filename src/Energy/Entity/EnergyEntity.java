package Energy.Entity;

import java.util.ArrayList;
import java.util.List;

import APPLIANCE.ENTITY.Appliance;
import Household.Entity.HouseholdEntity;

public class EnergyEntity {

    private String reportId;
    private String date;
    private HouseholdEntity household;
    private ArrayList<Appliance> topAppliances;
    private ArrayList<String> advice;

    public EnergyEntity(String reportId, String date,
                        HouseholdEntity household,
                        ArrayList<Appliance> topAppliances,
                        ArrayList<String> advice) {this.reportId = reportId;
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

    public ArrayList<Appliance> getTopAppliances() {
        return topAppliances;
    }

    public ArrayList<String> getAdvice() {
        return advice;
    }
}