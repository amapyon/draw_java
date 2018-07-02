package util;

import draw.Content;
import draw.Group;
import draw.Image;
import draw.Text;
import ui.Command;

public class ContentFactory {

	public static Content create(Command cmd) {
		Content content = null;
		if (cmd.is("text")) {
			content = new Text(
					cmd.getOptionInt(1),
					cmd.getOptionInt(2),
					cmd.getOptionString(3),
					cmd.getOptionInt(4)
					);
		} else if (cmd.is("image")) {
			content = new Image(
					cmd.getOptionInt(1),
					cmd.getOptionInt(2),
					cmd.getOptionInt(3),
					cmd.getOptionInt(4),
					cmd.getOptionString(5)
					);
		} else if (cmd.is("group")) {
			Group group = new Group(
					cmd.getOptionInt(1),
					cmd.getOptionInt(2)
					);
			Command nextCommand = cmd.getNextCommand();
			while (nextCommand != null) {
				Content nestContent = create(nextCommand);
				group.addContent(nestContent);
				nextCommand = cmd.getNextCommand();
			}
			content = group;
		}
		
		return content;
	}

}
