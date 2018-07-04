package draw;

import engine.RenderingEngine;

public class BorderedText extends Text {

	public BorderedText(double x, double y, String text, int fontSize) {
		super(x, y, text, fontSize);
	}
	
	@Override
	public void draw(double offsetX, double offsetY) {
		super.draw(offsetX, offsetY);
		
		double x = offsetX + getX();
		double y = offsetY + getY();
		double width = RenderingEngine.getStringWidth(getText());
		double height = RenderingEngine.getStringHeight();
		RenderingEngine.drawLine(x, y, x + width, y);
		RenderingEngine.drawLine(x, y + height, x + width, y + height);
		RenderingEngine.drawLine(x, y, x, y + height);
		RenderingEngine.drawLine(x + width, y, x + width, y + height);

	}
	

}
