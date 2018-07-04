package draw;

import engine.RenderingEngine;

public class Text extends Content {
	private String text;
	private int fontSize;

	public Text(double x, double y, String text, int fontSize) {
		super(x, y);
		this.text = text;
		this.fontSize = fontSize;
	}
	
	public String getText() {
		return text;
	}
	
	public int getFontSize() {
		return fontSize;
	}
	
	@Override
	public void draw(double offsetX, double offsetY) {
		RenderingEngine.setFontSize(fontSize);
		RenderingEngine.drawString(offsetX + getX(), offsetY + getY(), text);
	}

}
