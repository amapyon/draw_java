package ui;

import java.io.InputStream;
import java.util.Scanner;

public class Console {
	private Scanner scanner;
	private String command;
	
	public Console(InputStream in) {
		scanner = new Scanner(in);
	}
	
	public Command readCommand() {
		String line = scanner.nextLine().trim();
		if (line.endsWith(";")) {
			Command cmd = new Command(command + line);
			command = "";
			return cmd;
		}
		command += line;
		return readCommand();
	}
	
	public void close() {
		
	}
}
