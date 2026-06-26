package APPLIANCE.SERVICE;
import java.util.ArrayList;
import java.util.List;

import APPLIANCE.ENTITY.Appliance;
import APPLIANCE.ENTITY.CoolingAppliances;
import APPLIANCE.ENTITY.HeatingAppliance;
import APPLIANCE.ENTITY.LightAppliance;

public class ApplianceService {
    private final List<Appliance> applianceList=new ArrayList<>();

    //Create :adding new appliance
    public void addAppliance(Appliance appliance){
        applianceList.add(appliance);
    }
    //Read :read each appliance by id(name)
    public Appliance readAppliance(String name){
        for(Appliance app:applianceList){
            if(app.getName().equalsIgnoreCase(name)){
                return app;
            }
        }
        return null;

    }
    //Read: read whole array
    public List<Appliance> getApplianceList(){
        return new ArrayList<>(applianceList);
    }

    //Update :edit appliance details
    public boolean updateAppliance(
            String name, String newName, double newPowerRating, double newDuration) {
        for (Appliance app : applianceList) {
            if (app.getName().equals(name)) {
                app.setName(newName);
                app.setPowerRating(newPowerRating);
                app.setUsageDuration(newDuration);
                return true;
            }
        }
        return false;

    }


    //Delete:
    public boolean deleteAppliance (String name){
            return applianceList.removeIf(appliance1 -> appliance1.getName().equals(name));

    }
    //Business logic

    public List<Appliance> getAppliancesByType(String type) {
        List<Appliance> result = new ArrayList<>();
        type=type.toLowerCase();

        for (Appliance app : applianceList) {
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
