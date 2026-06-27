package Energy.Utility;

public class EnergyUtility {

    // CONSTANTS

    public static final int TOP_LIMIT = 3;



    // ADVICE MESSAGE
    public static final String HEATING_ADVICE =
            "Reduce standby energy usage and turn off heating appliances when not needed.";

    public static final String COOLING_ADVICE =
            "Improve efficiency by reducing usage duration or servicing cooling devices.";

    public static final String LIGHTING_ADVICE =
            "Use energy-efficient bulbs and reduce unnecessary lighting usage.";



    // FORMAT METHODS

    // Format energy (2 decimal places)
    public static double formatDouble(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    // Format energy with unit
    public static String formatEnergy(double value) {
        return formatDouble(value) + " kWh";
    }

}
