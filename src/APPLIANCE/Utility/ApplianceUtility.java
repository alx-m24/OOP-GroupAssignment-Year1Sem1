package APPLIANCE.Utility;

public class ApplianceUtility {


    //CONSTANTS

    public static final String HEATING = "heating";
    public static final String COOLING = "cooling";
    public static final String LIGHTING = "lighting";


    // STRING VALIDATION

    public static boolean isValidString(String input) {
        return input != null && !input.trim().isEmpty();
    }


    // NUMBER VALIDATION

    public static boolean isNegative(double value) {
        return value <= 0;
    }


    // TYPE VALIDATION

    public static boolean isValidType(String type) {
        if (type == null) return false;

        String t = type.toLowerCase();

        return t.equals(HEATING) ||
                t.equals(COOLING) ||
                t.equals(LIGHTING);
    }


    // EQUAL ZERO
    public  static boolean equalZero(double value) {
        return value == 0;
    }
}

