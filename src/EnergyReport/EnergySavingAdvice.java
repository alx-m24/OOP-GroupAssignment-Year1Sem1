package EnergyReport;

import Appliances.Appliance;
import Appliances.CoolingAppliance;
import Appliances.HeatingAppliance;
import Appliances.LightAppliance;


public class EnergySavingAdvice {
    HighConsumption highConsumption;

    public EnergySavingAdvice(HighConsumption highConsumption) {
        this.highConsumption = highConsumption;

    }

    public void displayAdvice() {
        System.out.println("==================================================");
        System.out.println("         TAILORED ENERGY SAVING ADVICE            ");
        System.out.println("==================================================");

        int num=1;

        Appliance[] appliances= highConsumption.QueryArray();

        for(Appliance app :appliances) {
            if(app == null)continue;
            System.out.println(num + "." + app.getName() + ":");
            if (app instanceof CoolingAppliance) {
                System.out.println("is a cooling appliance.");
                System.out.println("Ensure that your " + app.getName() + " is between 22" +
                        "\u00B0" + "C" + "and 26" + "\u00B0" + "C to ensure energy efficiency.");
                System.out.println("Keep your temperature steady.");
                System.out.println("Ensure correct airflow and clean apparatus regularly.");

            } else if (app instanceof HeatingAppliance) {
                System.out.println("is a heating appliance.");
                System.out.println("Ensure that your " + app.getName() + " is between 48" +
                        "\u00B0" + "C" + "and 60 " + "\u00B0" + "C to ensure energy efficiency.");
                System.out.println("Close all windows and openings so as to prevent heat loss");
                System.out.println("Properly insulate your room");

            }else if(app instanceof LightAppliance){
                System.out.println("is a light appliance.");
                System.out.println("Use smart timers and sensors to switch off light when needed.");
                System.out.println("Open blinds during the day instead of switchig on overhead fixtures.");

            }
        }
    }
}
