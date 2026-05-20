package Input;

import MyUtils.Result;

public class Command {
    private final String m_command;
    private final int m_argumentNum;
    private final String m_helpMsg;

    private final int m_requiredWordNum;

    public Command(String command, int argumentNum, String helpMsg) {
        m_command = command;
        m_argumentNum = argumentNum;
        m_helpMsg = helpMsg;
        m_requiredWordNum = m_argumentNum + 1;
    }

    public int getArgumentNum() {
        return m_argumentNum;
    }

    public String getHelpMsg() {
        return m_helpMsg;
    }

    public String getCommand() {
        return m_command;
    }

    public Result Validate(String input) {
        if (getArguments(input).length != m_requiredWordNum) {
            return new Result(false, m_command + "expects " + m_argumentNum + " arguments\n Usage: " + m_helpMsg);
        }

        return new Result(true, "");
    }

    // First argument is command name
    public String[] getArguments(String input) {
        return input.split(" ", m_requiredWordNum);
    }
}
