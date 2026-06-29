package Energy.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import APPLIANCE.ENTITY.*;
import Energy.Entity.EnergyEntity;
import Energy.Utility.EnergyUtility;
import Household.Entity.HouseholdEntity;
import APPLIANCE.Utility.ApplianceUtility;

public class EnergyService {

    private ArrayList<EnergyEntity> reports;

    public EnergyService() {
        reports = new ArrayList<>();
    }

    //  Top 3 for ONE household
    public ArrayList<Appliance> getTop3Appliances(HouseholdEntity household) {

        List<Appliance> list = household.getAppliances();

        list.sort(Comparator.comparingDouble(
                Appliance::calculateEnergyConsumption).reversed());

        return new ArrayList<Appliance>(list.subList(0, Math.min(3, list.size())));
    }

    //  Top 3 across ALL households
    public ArrayList<Appliance> getTop3All(ArrayList<HouseholdEntity> households) {

        List<Appliance> all = new ArrayList<>();

        for (HouseholdEntity h : households) {
            all.addAll(h.getAppliances());
        }

        all.sort(Comparator.comparingDouble(
                Appliance::calculateEnergyConsumption).reversed());

        return new ArrayList<Appliance>(all.subList(0, Math.min(3, all.size())));
    }

    // Generate advice
    public ArrayList<String> generateAdvice(ArrayList<Appliance> appliances) {

        ArrayList<String> adviceList = new ArrayList<>();

        for (Appliance app : appliances) {

            if (app instanceof HeatingAppliance) {
                adviceList.add(EnergyUtility.HEATING_ADVICE);

            } else if (app instanceof CoolingAppliances) {
                adviceList.add(EnergyUtility.COOLING_ADVICE);

            } else if (app instanceof LightAppliance) {
                adviceList.add(EnergyUtility.LIGHTING_ADVICE);
            }
        }

        return adviceList;
    }

    //  Generate report
    public EnergyEntity generateReport(String reportId,
                                       String date,
                                       HouseholdEntity household) {

        ArrayList<Appliance> top3 = getTop3Appliances(household);
        ArrayList<String> advice = generateAdvice(top3);

        EnergyEntity report =
                new EnergyEntity(reportId, date, household, top3, advice);

        reports.add(report);

        return report;
    }

    //  Get all reports
    public ArrayList<EnergyEntity> getAllReports() {
        return reports;
    }
}