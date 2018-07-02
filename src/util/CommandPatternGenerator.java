package util;

public class CommandPatternGenerator {

	public static String generate(String commandName) {
		return "\\s*" + commandName + "\\s*";
	}

	public static String generate(String commandName, String options) {
		if (options == null || options.equals("")) {
			return generate(commandName); 
		}
		
		StringBuilder pattern = new StringBuilder("\\(");
		options.toUpperCase().chars().forEach((c) -> {
			if (c == 'S') {
				pattern.append("\\s*\\\"(.*)\\\"\\s*"); 
			} else if (c == 'N') {
				pattern.append("\\s*(\\d+)\\s*"); 
			} else if (c == 'B') {
				pattern.append("\\s*(.+)\\s*"); 
			} else {
				pattern.append((char)c);
			}
		});
		
		pattern.append("\\)");

		return generate(commandName) + pattern;
	}

}
