package engine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ui.Console;

class RenderingEngineTest {
	@AfterAll
	static void tearDownAll() {
		Console.waitEnter();
	}

	@DisplayName("線を引く")
	@Test
	void testDrawLine() throws Exception {
		RenderingEngine.initialize(100, 100);
		RenderingEngine.drawLine(10, 20, 30, 40);
		RenderingEngine.flushWindow();
	}

	@DisplayName("フォントサイズごとの文字列描画領域の大きさ")
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
