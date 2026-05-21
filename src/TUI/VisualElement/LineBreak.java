package TUI.VisualElement;

import TUI.TerminalConfig;

public class LineBreak extends VisualElement {
    final char m_separatorChar;

    public LineBreak() {
        m_separatorChar = '=';
    }
    public LineBreak(char seperatorChar) {
        m_separatorChar = seperatorChar;
    }

    @Override
    public void Display() {
        System.out.println();
        for (int i = 0; i < TerminalConfig.COLUMNS; ++i) {
            System.out.print(m_separatorChar);
        }
        System.out.println();
    }
}
