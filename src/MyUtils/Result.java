package MyUtils;

public class Result {
    private final boolean m_succeeded;
    private final String m_error;

    public Result(boolean succeeded, String error) {
        m_succeeded = succeeded;
        m_error = error;
    }

    public boolean OK() {
        return m_succeeded;
    }

    public String getError() {
        return m_error;
    }
}
