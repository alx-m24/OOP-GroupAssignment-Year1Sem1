package TUI;

public class TerminalConfig {
    public static final int COLUMNS;
    public static final int ROWS;

    static {
        String columnsEnv = System.getenv("COLUMNS");
        COLUMNS = (columnsEnv != null) ? Integer.parseInt(columnsEnv) : 80;

        String rowsEnv = System.getenv("ROWS");
        ROWS = (rowsEnv != null) ? Integer.parseInt(rowsEnv) : 25;
    }
}
