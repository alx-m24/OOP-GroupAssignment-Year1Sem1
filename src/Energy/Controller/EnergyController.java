package Energy.Controller;


import java.util.List;

import Energy.Service.EnergyService;
import Energy.Entity.EnergyEntity;

import Household.Entity.HouseholdEntity;
import APPLIANCE.ENTITY.Appliance;

public class EnergyController {

    private EnergyService service;

    //  Constructor
    public EnergyController() {
        this.service = new EnergyService();
    }


    //  REPORT FUNCTIONS


    //  Generate report for a household
    public EnergyEntity generateReport(String reportId, String date,
                                       HouseholdEntity household) {
        return service.generateReport(reportId, date, household);
    }

    //  Get all reports
    public List<EnergyEntity> getAllReports() {
        return service.getAllReports();
    }


    //  ANALYSIS FUNCTIONS

    // Top 3 appliances for ONE household
    public List<Appliance> getTop3Household(HouseholdEntity household) {
        return service.getTop3Appliances(household);
    }

    // Top 3 appliances across ALL households
    public List<Appliance> getTop3All(List<HouseholdEntity> households) {
        return service.getTop3All(households);
    }



    // ADVICE FUNCTION

    public List<String> generateAdvice(List<Appliance> appliances) {
        return service.generateAdvice(appliances);
    }
}