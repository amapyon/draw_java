package engine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Ignore;
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
	
	
	@DisplayName("キャンバスの範囲外は例外が発生する")
	@Test
	void testExceptionOutOfCanvas() throws Exception {
		RenderingEngine.initialize(100, 100);
		assertThrows(OutOfCanvas.class,
				() -> RenderingEngine.drawImage(-1, 0, 100, 100, null));
		assertThrows(OutOfCanvas.class,
				() -> RenderingEngine.drawImage(0, -1, 100, 100, null));
		assertThrows(OutOfCanvas.class,
				() -> RenderingEngine.drawImage(0, 0, 101, 100, null));
		assertThrows(OutOfCanvas.class,
				() -> RenderingEngine.drawImage(0, 0, 100, 101, null));
		
	}
}
