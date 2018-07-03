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
		String line = readLine();
		if (line.endsWith(";")) {
			String commandLine = inputBuffer + line.substring(0, line.length() - 1);
			Command cmd = new Command(commandLine);
			inputBuffer = "";
			return cmd;
		}
		inputBuffer += line;
		return readCommand(":");
	}
	
	private String readLine() {
		return scanner.nextLine().trim();
	}
	
	public void close() {
		scanner.close();
	}
	
	public static void waitEnter() {
		System.out.println("-- wait Enter Key --");
		Console con = new Console(System.in);
		con.readLine();
	}

}
