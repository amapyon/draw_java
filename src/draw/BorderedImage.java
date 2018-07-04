package draw;

import java.io.IOException;
import java.net.MalformedURLException;

import engine.RenderingEngine;

public class BorderedImage extends Image {

	public BorderedImage(double x, double y, double width, double height, String url)
			throws MalformedURLException, IOException {
		super(x, y, width, height, url);
	}

	@Override
	public void draw(double offsetX, double offsetY) {
		super.draw(offsetX, offsetY);
		double x = offsetX + getX();
		double y = offsetY + getY();
		RenderingEngine.drawLine(x, y, x + getWidth(), y);
		RenderingEngine.drawLine(x, y + getHeight(), x + getWidth(), y + getHeight());
		RenderingEngine.drawLine(x, y, x, y + getHeight());
		RenderingEngine.drawLine(x + getWidth(), y, x + getWidth(), y + getHeight());
	}
	
}
