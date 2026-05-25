import Appliance.Appliance;
import Input.Command;
import MyUtils.Result;
import TUI.VisualElement.CenteredText;
import TUI.VisualElement.LineBreak;
import TUI.VisualElement.Text;
import TUI.VisualElement.Title;
import TUI.Window.Dashboard;
import TUI.Window.Page;
import Units.KiloWattHours;
import Units.Watts;

import java.util.HashMap;
import java.util.Scanner;

public class EnergyTrackerApp {
    private enum PAGE {
        DASHBOARD("DASHBOARD"),
        ADD_APPLIANCE("ADD-APPLIANCE"),
        HELP("HELP");

        private final String m_pageName;

        PAGE(String pageName) {
            m_pageName = pageName;
        }

        @Override
        public String toString() {
            return m_pageName;
        }
    }

    private static final HashMap<String, Command> COMMANDS = new HashMap<>();
    private static final HashMap<PAGE, Page> PAGES = new HashMap<>();

    private static PAGE activePage = null;
    private static PAGE previousePage = null;

    private static final HashMap<String, Appliance> appliances = new HashMap<>();

    private static void SwitchPage(PAGE newPage) {
        previousePage = activePage;
        activePage = newPage;
        PAGES.get(activePage).Refresh();
    }

    public static void main(String[] args) {
        // == Initializing MAPS ===
        COMMANDS.put("HELP",                        new Command("HELP", 0,"Usage HELP: Displays help message"));
        COMMANDS.put("TURN-ON",                     new Command("TURN-ON", 1,"Usage TURN-ON <applianceName>: Turns ON the specified appliance"));
        COMMANDS.put("TURN-OFF",                    new Command("TURN-OFF", 1,"Usage TURN-OFF <applianceName>: Turns OFF the specified appliance"));
        COMMANDS.put("ADD-APPLIANCE",               new Command("ADD-APPLIANCE", 0, "Usage ADD-APPLIANCE: Navigates to add-appliance page"));
        COMMANDS.put("BACK",                        new Command("BACK", 0,"Usage BACK: Navigates to previous page (if any)"));
        COMMANDS.put("HOME",                        new Command("HOME", 0,"Usage HOME: Navigates to home page"));
        COMMANDS.put("DASHBOARD",                   new Command("DASHBOARD", 0,"Usage DASHBOARD: Navigates to dashboard/home page"));
        COMMANDS.put("REFRESH",                     new Command("REFRESH", 0,"Usage REFRESH: Refreshes the current page"));
        COMMANDS.put("EXIT",                        new Command("EXIT", 0,"Usage EXIT: Terminates the Application"));

        PAGES.put(PAGE.DASHBOARD,               new Dashboard(appliances));
        PAGES.put(PAGE.ADD_APPLIANCE,           new Page(true));
        PAGES.put(PAGE.HELP,                    new Page(true));

        PAGES.get(PAGE.ADD_APPLIANCE).AddElement(new Title("Add-Appliance"));
        PAGES.get(PAGE.ADD_APPLIANCE).AddElement(new LineBreak('-'));
        PAGES.get(PAGE.ADD_APPLIANCE).AddElement(new Text("Add Appliance Options: \n"));
        PAGES.get(PAGE.ADD_APPLIANCE).AddElement(new Text("\tAdd Light-Appliance: ADD-LIGHT <ApplianceName> <powerRating> <Whatever additional info>\n"));
        PAGES.get(PAGE.ADD_APPLIANCE).AddElement(new Text("\tAdd Cooling-Appliance: ADD-COOLING <ApplianceName> <powerRating>\n"));
        PAGES.get(PAGE.ADD_APPLIANCE).AddElement(new Text("\tAdd Heating-Appliance: ADD-HEATING <ApplianceName> <powerRating> <Lorem_Ipsum>\n"));
        PAGES.get(PAGE.ADD_APPLIANCE).AddElement(new Text("\tAdd Heating-Appliance: ADD-ENTERTAINMENT <ApplianceName> <powerRating> <Lorem_Ipsum>\n"));
        PAGES.get(PAGE.ADD_APPLIANCE).AddElement(new Text("\tAdd Generic-Appliance: ADD-GENERIC <ApplianceName> <powerRating>"));
        PAGES.get(PAGE.ADD_APPLIANCE).AddElement(new LineBreak('-'));
        PAGES.get(PAGE.ADD_APPLIANCE).AddElement(new Text("Commands: EXIT, HELP, BACK, HOME/DASHBOARD"));

        PAGES.get(PAGE.HELP).AddElement(new Title("Help"));
        PAGES.get(PAGE.HELP).AddElement(new LineBreak('-'));
        PAGES.get(PAGE.HELP).AddElement(new CenteredText("Available Commands"));
        int commandNum = 1;
        for (final Command cmd : COMMANDS.values()) {
            PAGES.get(PAGE.HELP).AddElement(new Text("\n" + (commandNum++) + ". " + cmd.getCommand() + " \t\t— " + cmd.getHelpMsg()));
        }
        PAGES.get(PAGE.HELP).AddElement(new LineBreak('-'));
        PAGES.get(PAGE.HELP).AddElement(new Text("Commands: EXIT, HOME/DASHBOARD, BACK"));

        // === Initializing inputs ===
        Scanner scanner = new Scanner(System.in);
        String input;
        String commandKey;
        Command command;

        appliances.put("LightBulb", new Appliance("LightBulb", new Watts(10.0)));
        appliances.put("Fridge", new Appliance("Fridge", new Watts(10.0)));
        appliances.put("Heater", new Appliance("Heater", new Watts(10.0)));

        SwitchPage(PAGE.DASHBOARD);
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
            else if (commandKey.equals("HELP")) {
                SwitchPage(PAGE.HELP);
            }
            else if (commandKey.equals("HOME") || commandKey.equals("DASHBOARD")) {
                SwitchPage(PAGE.DASHBOARD);
            }
            else if (commandKey.equals("BACK")) {
                SwitchPage(previousePage);
            }
            else if (commandKey.equals("REFRESH")) {
                PAGES.get(activePage).Refresh();
            }
            else if (commandKey.equals("ADD-APPLIANCE")) {
                SwitchPage(PAGE.ADD_APPLIANCE);
            }
            else if (commandKey.contains("TURN-")) {
                String appliance = arguments[1];
                if (!appliances.containsKey(appliance)) {
                    System.out.println("Appliance '" + appliance + "' does not exist");
                    continue;
                }
                if (commandKey.contains("ON")) {
                    appliances.get(appliance).TurnOn();
                    System.out.println("TURNED ON " + appliance);
                }
                else {
                    appliances.get(appliance).TurnOff();
                    System.out.println("TURNED OFF " + appliance);
                }
            }
        }
        while (!commandKey.equals("EXIT"));

        System.out.println("\nThank you for using EnergyTracker. Goodbye!");
    }
}