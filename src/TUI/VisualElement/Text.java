package TUI.VisualElement;

public class Text extends  VisualElement {
    protected final String m_text;

    public Text(String text) {
        m_text = text;
    }

    @Override
    public void Display() {
        System.out.print(m_text);
    }
}
