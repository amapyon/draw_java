package app;

import draw.Document;
import draw.Content;
import ui.Command;
import ui.Console;
import util.ContentFactory;

public class Main {

	public static void main(String[] args) throws Exception {
		Document doc = new Document(210, 297);
		Console con = new Console(System.in);
		while (true) {
			Command cmd = con.readCommand();
			if (cmd.is("quit")) {
				break;
			} else if (cmd.is("flush")) {
				String filename = cmd.getOptionString(1);
				if (!"".equals(filename)) {
					doc.setFilename(filename);
					doc.drawToFile();
				} else {
					doc.drawToWindow();
				}
			} else if (cmd.is("text") || cmd.is("image") || cmd.is("group")) {
				Content content = ContentFactory.create(cmd);
				doc.addContent(content);
			}
		}
		con.close();
		System.exit(0);
	}

}
