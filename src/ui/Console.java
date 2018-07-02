package ui;

import java.io.InputStream;
import java.util.Scanner;

public class Console {
	private Scanner scanner;
	private String inputBuffer = "";
	
	public Console(InputStream in) {
		scanner = new Scanner(in);
	}
	
	public Command readCommand() {
		return readCommand(">");
	}

	private Command readCommand(String prompt) {
		System.out.print(prompt);
		String line = scanner.nextLine().trim();
		if (line.endsWith(";")) {
			String commandLine = inputBuffer + line.substring(0, line.length() - 1);
			Command cmd = new Command(commandLine);
			inputBuffer = "";
			return cmd;
		}
		inputBuffer += line;
		return readCommand(":");
	}
	
	public void close() {
		scanner.close();
	}

}
