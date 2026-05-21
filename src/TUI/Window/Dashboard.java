package TUI.Window;

import Appliance.Appliance;
import TUI.VisualElement.*;

import java.util.HashMap;

public class Dashboard extends Page {
    private final HashMap<String, Appliance> m_appliances;

    public Dashboard(HashMap<String, Appliance> appliances) {
        super(true);
        m_appliances = appliances;
    }

    @Override
    public void Refresh() {
        ClearConsole();
        if (m_hasBoard) new LineBreak().Display();
        new Title("DashBoard").Display();
        new LineBreak('-').Display();
        new CenteredText("Appliances:").Display();
        int applianceNum = 0;
        for (final Appliance appliance : m_appliances.values()) {
            new Text("\n" + (++applianceNum) + ". " + appliance.getName() + ": Turned On: " + appliance.isPoweredOn() + ", Current Powered on Time: " + appliance.getCurrentPoweredOnDuration().getSeconds() + "s, Total Powered on time: " + appliance.getTotalPoweredOnDuration().getSeconds() + "s").Display();
        }
        new LineBreak('-').Display();
        new Text("Commands: EXIT, HELP, ADD-APPLIANCE, ...").Display();
        if (m_hasBoard) new LineBreak().Display();
    }
}
