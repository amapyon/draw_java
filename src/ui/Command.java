package ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Command {

	private String commandLine;
	private Matcher macher;
	private boolean find = false;

	public Command(String commandLine) {
		this.commandLine = commandLine.trim();
	}
	
	public boolean is(String command) {
		if (commandLine.equals("")) {
			return false;
		}

		int spc = commandLine.indexOf("(");
		if (spc < 0) {
			return command.equals(commandLine);
		}

		return commandLine.substring(0, spc).trim().equals(command);
	}

	public String getOptions() {
		if (commandLine.equals("")) {
			return "";
		}

		int spc = commandLine.indexOf("(");
		if (spc < 0) {
			return "";
		}

		return commandLine.substring(spc).trim();
	}

	private String getOptionString(String pattern, int index) {
		if (macher == null) {
			Pattern p = Pattern.compile(pattern);
			macher = p.matcher(commandLine);
		}
		if (find || macher.find()) {
			find = true;
			return macher.group(index);
		}
		return "";
	}

	private final String TEXT_OPTION_REGEX = "text\\s*\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*\\\"(.*)\\\"\\s*,\\s*(\\d+)\\s*\\)\\s*,?";

	public int getTextOptionInt(int index) {
		return Integer.parseInt(getTextOptionString(index));
	}

	public String getTextOptionString(int index) {
		return getOptionString(TEXT_OPTION_REGEX, index);
	}

	private final String IMAGE_OPTION_REGEX = "image\\s*\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*\\\"(.*)\\\"\\s*\\)\\s*,?";

	public int getImageOptionInt(int index) {
		return Integer.parseInt(getImageOptionString(index));
	}

	public String getImageOptionString(int index) {
		return getOptionString(IMAGE_OPTION_REGEX, index);
	}

	private final String GROUP_OPTION_REGEX = "group\\s*\\(\\s*(.+)\\s*\\)";
	private String nextCommand = "";

	public Command getGroupNextCommand() {
		if (isGroupFirstCommand()) {
			Pattern p = Pattern.compile(GROUP_OPTION_REGEX);
			Matcher m = p.matcher(commandLine);

			if (!m.find()) {
				return null;
			} else {
				find = true;
				nextCommand = m.group(1);
			}
		}
		return getNextCommand();
	}
	
	private Command getNextCommand() {
		Command cmd = new Command(nextCommand);
		if (cmd.is("text")) {
			nextCommand = nextCommand.substring(cmd.getTextOptionString(0).length());
		} else if (cmd.is("image")) {
			nextCommand = nextCommand.substring(cmd.getImageOptionString(0).length());
		}
		return cmd;
	}

	private boolean isGroupFirstCommand() {
		return nextCommand.equals("");
	}

}
