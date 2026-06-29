package Energy.Controller;


import java.util.ArrayList;
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
    public ArrayList<EnergyEntity> getAllReports() {
        return service.getAllReports();
    }


    //  ANALYSIS FUNCTIONS

    // Top 3 appliances for ONE household
    public ArrayList<Appliance> getTop3Household(HouseholdEntity household) {
        return service.getTop3Appliances(household);
    }

    // Top 3 appliances across ALL households
    public ArrayList<Appliance> getTop3All(ArrayList<HouseholdEntity> households) {
        return service.getTop3All(households);
    }



    // ADVICE FUNCTION

    public ArrayList<String> generateAdvice(ArrayList<Appliance> appliances) {
        return service.generateAdvice(appliances);
    }
}