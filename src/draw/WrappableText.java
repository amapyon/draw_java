package draw;

import java.util.Arrays;

import engine.RenderingEngine;

public class WrappableText extends Text {

	public WrappableText(double x, double y, String text, int fontSize) {
		super(x, y, text, fontSize);
	}

	@Override
	public void draw(double offsetX, double offsetY) {
		RenderingEngine.setFontSize(getFontSize());
		double height = RenderingEngine.getStringHeight();
		String[] text = getText().split("\n");
		Arrays.stream(text).reduce(0, (i, s) -> {
			System.out.println(i);
			RenderingEngine.drawString(offsetX + getX(), offsetY + getY() + i * height, s);
			return ++i;
			}, (i1, i2) -> 0);
	}
}
