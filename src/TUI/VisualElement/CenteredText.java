package TUI.VisualElement;

import TUI.TerminalConfig;

public class CenteredText extends Text {
    public CenteredText(String text) {
        super(text);
    }

    @Override
    public void Display() {
        final short spaceToAdd = (short) Math.floor((TerminalConfig.COLUMNS - m_text.length()) / 2.0);

        for (short i = 0; i < spaceToAdd; ++i) System.out.print(' ');
        super.Display();
    }
}
