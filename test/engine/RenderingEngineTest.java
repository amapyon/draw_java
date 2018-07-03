package engine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ui.Console;

class RenderingEngineTest {

	@Test
	void testDrawLine() throws Exception {
		RenderingEngine.initialize(100, 100);
		RenderingEngine.drawLine(10, 20, 30, 40);
		RenderingEngine.flushWindow();
		Console.waitEnter();
	}

	@Test
	void testFontSize() throws Exception {
		RenderingEngine.initialize(100, 100);
		RenderingEngine.setFontSize(10);
		System.out.println(RenderingEngine.getStringHeight());
		System.out.println(RenderingEngine.getStringWidth("ABC"));
		RenderingEngine.setFontSize(20);
		System.out.println(RenderingEngine.getStringHeight());
		System.out.println(RenderingEngine.getStringWidth("ABC"));
	}
}
