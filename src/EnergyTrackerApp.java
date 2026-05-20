import Appliance.Appliance;
import Input.Command;
import MyUtils.Result;
import Units.KiloWattHours;
import Units.Watts;

import java.util.HashMap;
import java.util.Scanner;

public class EnergyTrackerApp {
    private final double TIME_FACTOR = 10.0; // Used to accelerate time while testing

    private static final HashMap<String, Command> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("HELP",                        new Command("HELP", 0,"Usage HELP: Displays help message"));
        COMMANDS.put("TURN-ON",     new Command("TURN-ON", 1,"Usage TURN-ON <applianceName>: Turns ON the specified appliance"));
        COMMANDS.put("TURN-OFF",    new Command("TURN-OFF", 1,"Usage TURON-OFF <applianceName>: Turns OFF the specified appliance"));
        COMMANDS.put("LIST",                        new Command("LIST", 0,"Usage LIST: Lists all registered appliances and there states"));
        COMMANDS.put("EXIT",                        new Command("EXIT", 0,"Usage EXIT: Terminates the Application"));
    }

    private static void DisplayHelpMessage() {
        int commandNum = 1;
        for (final HashMap.Entry<String, Command> entry : COMMANDS.entrySet()) {
            String command   = entry.getKey();
            String helpMsg = entry.getValue().getHelpMsg();
            System.out.println(commandNum + ". " + command + " — " + helpMsg);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        String commandKey;
        Command command;

        HashMap<String, Appliance> appliances = new HashMap<>();

        appliances.put("Test", new Appliance("Test", new Watts(10.0), new KiloWattHours(0.0)));

        System.out.println("=== WELCOME === ");
        System.out.println("---Commands are NOT Case-Sensitive---");
        do {
            System.out.print("Enter command: ");
            input = scanner.nextLine().trim();
            commandKey = input.split(" ")[0].toUpperCase();

            if (!COMMANDS.containsKey(commandKey)) {
                System.out.println("Unknown command. Type HELP for a list of commands.");
                continue;
            }

            command = COMMANDS.get(commandKey);

            Result validateCommand = command.Validate(input);
            if (!validateCommand.OK()) {
                System.out.printf("Input is invalid: %s\n", validateCommand.getError());
                continue;
            }

            String[] arguments = command.getArguments(input);

            if (commandKey.equals("EXIT")) break;
            if (commandKey.equals("LIST")) {
                int applianceNum = 0;
                for (Appliance appliance : appliances.values()) {
                    ++applianceNum;
                    System.out.println(applianceNum + ". " + appliance.getName() + ": Turned On: " + appliance.isPoweredOn() + ", Current Powered on Time: " + appliance.getCurrentPoweredOnDuration().getSeconds() + "s, Total Powered on time: " + appliance.getTotalPoweredOnDuration().getSeconds() + "s");
                }
            }
            else if (commandKey.equals("HELP")) {
                DisplayHelpMessage();
            }
            else if (commandKey.contains("TURN-")) {
                String appliance = arguments[1];
               if (commandKey.contains("ON")) {
                   appliances.get(appliance).TurnOn();
               }
               else {
                   appliances.get(appliance).TurnOff();
               }
            }

        }
        while (!commandKey.equals("EXIT"));

        System.out.println("Goodbyeeee");
    }
}