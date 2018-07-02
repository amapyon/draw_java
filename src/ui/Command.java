package ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.CommandPatternGenerator;

public class Command {

	private static final String FLUSH_REGEX = CommandPatternGenerator.generate("flush", "S");
	private static final String TEXT_REGEX = CommandPatternGenerator.generate("text", "N,N,S,N") + "\\s*,?";
	private static final String IMAGE_REGEX = CommandPatternGenerator.generate("image", "N,N,N,N,S") + "\\s*,?";
	private static final String GROUP_REGEX = CommandPatternGenerator.generate("group", "N,N,B") + "\\s*";

	private String commandLine;
	private Matcher macher;
	private boolean find = false;

	public Command(String commandLine) {
		if (commandLine != null) {
			this.commandLine = commandLine.trim();
		}
		
		if (is("quit")) {
			
		} else if (is("flush")) {
			find = parse(FLUSH_REGEX);
		} else if (is("text")) {
			find = parse(TEXT_REGEX);
		} else if (is("image")) {
			find = parse(IMAGE_REGEX);
		} else if (is("group")) {
			find = parse(GROUP_REGEX);
			if (find) {
				nextCommand = macher.group(3).trim();
			}
		}
	}
	
	public String toString() {
		return commandLine;
	}
	
	private boolean parse(String pattern) {
		Pattern p = Pattern.compile(pattern);
		macher = p.matcher(commandLine);
		return macher.find();
	}
	
	public boolean is(String command) {
		if (commandLine == null) {
			return false;
		}
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
	
	public String getOptionString(int index) {
		if (find) {
			return macher.group(index);
		}
		return "";
	}

	public int getOptionInt(int index) {
		return Integer.parseInt(getOptionString(index));
	}

	private String nextCommand = "";

	public Command getNextCommand() {
		Command cmd = new Command(nextCommand);
		if (cmd.is("text") || cmd.is("image")) {
			nextCommand = nextCommand.substring(cmd.getOptionString(0).length()).trim();
		} else {
			return null;
		}
		return cmd;
	}

}
