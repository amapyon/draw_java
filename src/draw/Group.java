package draw;

import java.util.ArrayList;

public class Group extends Content {
	
	private ArrayList<Content> contents = null;

	public Group(double x, double y) {
		super(x, y);
		this.contents = new ArrayList<Content>();
	}
	
	public Content addContent(Content content) {
		contents.add(content);
		return content;
	}

	@Override
	public void draw() {
		draw(getX(), getY());
	}

	@Override
	public void draw(double offsetX, double offsetY) {
		contents.forEach((c)->{c.draw(offsetX, offsetY);});
	}

}
