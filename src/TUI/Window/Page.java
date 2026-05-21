package TUI.Window;

import TUI.TerminalConfig;
import TUI.VisualElement.LineBreak;
import TUI.VisualElement.VisualElement;

import java.util.ArrayList;

public class Page {
    final private ArrayList<VisualElement> m_elements;
    final boolean m_hasBoard;

    public Page(boolean hasBorder) {
        m_elements = new ArrayList<>();
        m_hasBoard = hasBorder;
    }

    public void AddElement(VisualElement element) {
        m_elements.add(element);
    }

    public void ClearConsole() {
        for (short y = 0; y < TerminalConfig.ROWS; ++y) {
            System.out.println();
        }
    }

    public void Refresh() {
        ClearConsole();
        if (m_hasBoard) new LineBreak().Display();
        for (VisualElement element : m_elements) {
            element.Display();
        }
        if (m_hasBoard) new LineBreak().Display();
    }
}
