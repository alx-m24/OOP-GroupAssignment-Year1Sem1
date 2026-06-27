package APPLIANCE.SERVICE;
import java.util.ArrayList;
import java.util.List;

import APPLIANCE.ENTITY.Appliance;
import APPLIANCE.ENTITY.CoolingAppliances;
import APPLIANCE.ENTITY.HeatingAppliance;
import APPLIANCE.ENTITY.LightAppliance;
import Household.Entity.HouseholdEntity;

public class ApplianceService {



    //Business logic

    public List<Appliance> getAppliancesByType(List<Appliance> appliances, String type) {
        List<Appliance> result = new ArrayList<>();
        type=type.toLowerCase();

        for (Appliance app :appliances ) {
            switch (type) {
                case "cooling":
                    if(app instanceof CoolingAppliances)
                    result.add(app);
                break;
                case "heating":
                    if(app instanceof HeatingAppliance)
                        result.add(app);
                break;
                case "lighting":
                    if(app instanceof LightAppliance)
                        result.add(app);
                break;
            }
        }
        return result;
    }








}
